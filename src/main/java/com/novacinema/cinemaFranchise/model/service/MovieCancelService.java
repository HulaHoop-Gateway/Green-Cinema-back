package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.GroupedReservationDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservation.model.service.ReservationService;
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
    private final ScheduleMapper scheduleMapper; // ⭐ 추가됨
    private final ReservationService reservationService; // ⭐ 그룹화용

    public MovieCancelService(
            ReservationMapper reservationMapper,
            ReservationCRUDService reservationCRUDService,
            ScheduleMapper scheduleMapper, // ⭐ 추가됨
            ReservationService reservationService // ⭐ 그룹화용
    ) {
        this.reservationMapper = reservationMapper;
        this.reservationCRUDService = reservationCRUDService;
        this.scheduleMapper = scheduleMapper; // ⭐ 추가됨
        this.reservationService = reservationService; // ⭐ 그룹화용
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                // 1️⃣ 취소 가능한 예매 목록 조회 (그룹화)
                case "movie_cancel_step1": {
                    String phoneNumber = String.valueOf(data.get("phoneNumber"));

                    if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                        result.put("error", "전화번호가 유효하지 않습니다.");
                        break;
                    }

                    // 그룹화된 예약 조회
                    List<GroupedReservationDTO> groupedReservations = reservationService
                            .getGroupedReservationsByPhoneNumber(phoneNumber);

                    // 취소 가능한 예약만 필터링
                    List<GroupedReservationDTO> cancelableReservations = groupedReservations.stream()
                            .filter(r -> r.getScheduleDTO() != null && r.getScheduleDTO().isCancelable())
                            .filter(r -> !"취소됨".equals(r.getState()))
                            .collect(Collectors.toList());

                    if (cancelableReservations.isEmpty()) {
                        result.put("message", "📭 취소 가능한 예매 내역이 없습니다.");
                    } else {
                        List<Map<String, Object>> reservationList = cancelableReservations.stream().map(r -> {
                            Map<String, Object> m = new HashMap<>();
                            m.put("reservationNum", r.getFirstReservationNum());
                            m.put("bookingGroupId", r.getBookingGroupId());
                            m.put("movieTitle", r.getScheduleDTO().getMovieInfo().getMovieTitle());
                            m.put("screeningDate", r.getScheduleDTO().getScreeningDate());
                            m.put("branchName",
                                    r.getScheduleDTO().getTheaterInfo().getCinemaFranchisedto().getBranchName());
                            m.put("seatLabels", r.getSeatLabels()); // ✅ 그룹화된 좌석 리스트
                            m.put("seatCodes", r.getSeatCodes());
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
                                r.getSeatDTO().getRowLabel() + r.getSeatDTO().getColNum()));
                    }
                    break;
                }

                // 3️⃣ 실제 예매 취소 처리 + 관리자 서버 취소트랜잭션 INSERT (그룹 단위)
                case "movie_cancel": // ✅ Admin 서버 요청 (Gateway)
                case "movie_cancel_step3": {
                    ReservationDTO reservation = null;

                    // 1. transactionNum으로 조회 (Blue-back 요청)
                    if (data.containsKey("transactionNum")) {
                        Long transactionNum = Long.parseLong(String.valueOf(data.get("transactionNum")));
                        reservation = reservationMapper.findByTransactionNum(transactionNum);
                    }
                    // 2. reservationNum으로 조회 (Chatbot 요청)
                    else if (data.containsKey("reservationNum")) {
                        String reservationNum = String.valueOf(data.get("reservationNum"));
                        reservation = reservationMapper.selectReservationByNum(reservationNum);
                    }

                    if (reservation == null) {
                        result.put("message", "❌ 해당 예매 정보를 찾을 수 없습니다.");
                        break;
                    }

                    String phoneNumber = reservation.getPhoneNumber();
                    int scheduleNum = reservation.getScheduleNum();
                    String bookingGroupId = reservation.getBookingGroupId();
                    Long originalTransactionNum = reservation.getTransactionNum(); // ✅ 원본 트랜잭션 번호

                    // 2️⃣ 같은 그룹의 모든 예약 찾기
                    List<ReservationDTO> groupReservations = new ArrayList<>();
                    if (bookingGroupId != null && !bookingGroupId.isEmpty()) {
                        // 그룹 예약: 같은 booking_group_id의 모든 예약 찾기
                        groupReservations = reservationMapper.selectReservationsByPhoneNumber(phoneNumber).stream()
                                .filter(r -> bookingGroupId.equals(r.getBookingGroupId()))
                                .collect(Collectors.toList());
                    } else {
                        // 단일 예약
                        groupReservations.add(reservation);
                    }

                    // ⭐ schedule → theater → branch → merchantCode 조회
                    String merchantCode = scheduleMapper.findMerchantCodeByScheduleNum(scheduleNum);

                    int totalAmount = 0;
                    int successCount = 0;

                    // 3️⃣ 그룹의 모든 예약 취소
                    for (ReservationDTO r : groupReservations) {
                        boolean success = reservationCRUDService.updateReservationState(r.getReservationNum());
                        if (success) {
                            successCount++;
                            totalAmount += r.getSeatDTO().getPrice();
                        }
                    }

                    if (successCount == 0) {
                        result.put("message", "⚠️ 예매 취소 상태 업데이트 실패");
                        break;
                    }

                    // 4️⃣ 관리자 서버로 취소 트랜잭션 INSERT
                    try {
                        RestTemplate restTemplate = new RestTemplate();

                        String url = "http://localhost:8000/api/transactions/add";

                        // ⭐ 스케줄 정보 조회 (취소 내역 날짜용)
                        com.novacinema.schedule.model.dto.ScheduleDTO schedule = scheduleMapper
                                .selectScheduleByNum(scheduleNum);
                        java.time.LocalDateTime startDate = null;
                        java.time.LocalDateTime endDate = null;

                        if (schedule != null) {
                            startDate = schedule.getScreeningDate();
                            if (schedule.getMovieInfo() != null) {
                                int runningTime = schedule.getMovieInfo().getRunningTime();
                                if (startDate != null) {
                                    endDate = startDate.plusMinutes(runningTime);
                                }
                            }
                        }

                        Map<String, Object> payload = new HashMap<>();
                        payload.put("phoneNum", phoneNumber);
                        payload.put("merchantCode", merchantCode);
                        payload.put("amountUsed", totalAmount); // ✅ 전체 금액
                        payload.put("status", "R"); // 취소 코드
                        payload.put("originalTransactionNum", originalTransactionNum); // ✅ 원본 트랜잭션 번호 추가
                        // ✅ LocalDateTime -> String 변환 (JSON 직렬화 오류 방지)
                        payload.put("startDate", startDate != null ? startDate.toString() : null);
                        payload.put("endDate", endDate != null ? endDate.toString() : null);

                        restTemplate.postForObject(url, payload, String.class);

                        result.put("message", String.format("🟢 총 %d개의 예매가 취소되었습니다! (환불액: %s원)",
                                successCount, String.format("%,d", totalAmount)));

                    } catch (Exception e) {
                        result.put("message",
                                String.format("⚠️ 예매는 취소되었지만 관리자 서버 기록 실패 (취소된 좌석: %d개): %s",
                                        successCount, e.getMessage()));
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
