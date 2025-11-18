package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservationCRUD.service.ReservationCRUDService;
import com.novacinema.schedule.model.dao.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieCancelService {

    private final ReservationMapper reservationMapper;
    private final ReservationCRUDService reservationCRUDService;
    private final ScheduleMapper scheduleMapper;   // ⭐ 추가됨

    public MovieCancelService(
            ReservationMapper reservationMapper,
            ReservationCRUDService reservationCRUDService,
            ScheduleMapper scheduleMapper   // ⭐ 추가됨
    ) {
        this.reservationMapper = reservationMapper;
        this.reservationCRUDService = reservationCRUDService;
        this.scheduleMapper = scheduleMapper;      // ⭐ 추가됨
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                // 1️⃣ 취소 가능한 예매 목록 조회
                case "movie_cancel_step1": {
                    String phoneNumber = String.valueOf(data.get("phoneNumber"));

                    if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                        result.put("error", "전화번호가 유효하지 않습니다.");
                        break;
                    }

                    List<ReservationDTO> reservations = reservationMapper.selectReservationsByPhoneNumber(phoneNumber);

                    List<ReservationDTO> cancelableReservations = reservations.stream()
                            .filter(r -> r.getScheduleDTO() != null && r.getScheduleDTO().isCancelable())
                            .filter(r -> !"취소됨".equals(r.getState()))
                            .collect(Collectors.toList());

                    if (cancelableReservations.isEmpty()) {
                        result.put("message", "📭 취소 가능한 예매 내역이 없습니다.");
                    } else {
                        List<Map<String, Object>> reservationList = cancelableReservations.stream().map(r -> {
                            Map<String, Object> m = new HashMap<>();
                            m.put("reservationNum", r.getReservationNum());
                            m.put("movieTitle", r.getScheduleDTO().getMovieInfo().getMovieTitle());
                            m.put("screeningDate", r.getScheduleDTO().getScreeningDate());
                            m.put("branchName", r.getScheduleDTO().getTheaterInfo().getCinemaFranchisedto().getBranchName());
                            m.put("seat", r.getSeatDTO().getRowLabel() + r.getSeatDTO().getColNum());
                            return m;
                        }).collect(Collectors.toList());

                        result.put("reservations", reservationList);
                    }
                    break;
                }

                // 2️⃣ 예매 선택 확인
                case "movie_cancel_step2": {
                    String reservationNum = String.valueOf(data.get("reservationNum"));

                    if (reservationNum == null || reservationNum.trim().isEmpty()) {
                        result.put("message", "❌ 예매 번호가 유효하지 않습니다.");
                        break;
                    }

                    List<ReservationDTO> all = reservationMapper.selectAllReservations();

                    Optional<ReservationDTO> target = all.stream()
                            .filter(r -> r.getReservationNum().equals(reservationNum))
                            .filter(r -> r.getScheduleDTO() != null && r.getScheduleDTO().isCancelable())
                            .filter(r -> !"취소됨".equals(r.getState()))
                            .findFirst();

                    if (target.isEmpty()) {
                        result.put("message", "❌ 해당 예매는 존재하지 않거나 취소할 수 없습니다.");
                    } else {
                        ReservationDTO r = target.get();
                        result.put("message", String.format(
                                "🔎 선택하신 예매 정보:\n🎟️ %s / %s / %s / 좌석 %s\n\n✅ 이 예매를 취소하시겠습니까?",
                                r.getScheduleDTO().getMovieInfo().getMovieTitle(),
                                r.getScheduleDTO().getScreeningDate(),
                                r.getScheduleDTO().getTheaterInfo().getCinemaFranchisedto().getBranchName(),
                                r.getSeatDTO().getRowLabel() + r.getSeatDTO().getColNum()
                        ));
                    }
                    break;
                }

                // 3️⃣ 실제 예매 취소 처리 + 관리자 서버 취소트랜잭션 INSERT
                case "movie_cancel_step3": {
                    String reservationNum = String.valueOf(data.get("reservationNum"));

                    if (reservationNum == null || reservationNum.trim().isEmpty()) {
                        result.put("error", "예매 번호가 유효하지 않습니다.");
                        break;
                    }

                    // 1️⃣ 예매 상세 정보 가져오기
                    ReservationDTO reservation = reservationMapper.selectReservationByNum(reservationNum);

                    if (reservation == null) {
                        result.put("message", "❌ 해당 예매 정보를 찾을 수 없습니다.");
                        break;
                    }

                    String phoneNumber = reservation.getPhoneNumber();
                    int scheduleNum = reservation.getScheduleNum();

                    // ⭐ 예매 금액 seat 테이블에서 가져옴
                    int amountUsed = reservation.getSeatDTO().getPrice();

                    // ⭐ schedule → theater → branch → merchantCode 조회
                    String merchantCode = scheduleMapper.findMerchantCodeByScheduleNum(scheduleNum);

                    // 2️⃣ 예매 상태 업데이트 + 좌석 해제
                    boolean success = reservationCRUDService.updateReservationState(reservationNum);

                    if (!success) {
                        result.put("message", "⚠️ 예매 취소 상태 업데이트 실패");
                        break;
                    }

                    // 3️⃣ 관리자 서버로 취소 트랜잭션 INSERT
                    try {
                        RestTemplate restTemplate = new RestTemplate();

                        String url = "http://localhost:8000/api/transactions/add";

                        Map<String, Object> payload = new HashMap<>();
                        payload.put("phoneNum", phoneNumber);
                        payload.put("merchantCode", merchantCode);
                        payload.put("amountUsed", amountUsed);
                        payload.put("status", "R"); // 취소 코드
                        payload.put("startDate", null);
                        payload.put("endDate", null);

                        restTemplate.postForObject(url, payload, String.class);

                        result.put("message", "🟢 예매 취소 및 거래 취소 기록 완료!");

                    } catch (Exception e) {
                        result.put("message",
                                "⚠️ 예매는 취소되었지만 관리자 서버 기록 실패: " + e.getMessage());
                    }

                    break;
                }

                default:
                    result.put("error", "알 수 없는 intent입니다: " + intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "예매 취소 처리 중 오류 발생: " + e.getMessage());
        }

        return result;
    }
}
