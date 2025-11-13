package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.reservation.model.dao.ReservationMapper;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservationCRUD.service.ReservationCRUDService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieCancleService {

    private final ReservationMapper reservationMapper;
    private final ReservationCRUDService reservationCRUDService;

    public MovieCancleService(ReservationMapper reservationMapper, ReservationCRUDService reservationCRUDService) {
        this.reservationMapper = reservationMapper;
        this.reservationCRUDService = reservationCRUDService;
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                // 1️⃣ 취소 가능한 예매 목록 조회
                case "movie_cancel_step1": {
                    String memberCode = String.valueOf(data.get("memberCode"));

                    if (memberCode == null || memberCode.trim().isEmpty()) {
                        result.put("error", "회원 코드가 유효하지 않습니다.");
                        break;
                    }

                    List<ReservationDTO> reservations = reservationMapper.selectReservationsByMemberCode(memberCode);

                    List<ReservationDTO> cancelableReservations = reservations.stream()
                            .filter(r -> r.getScheduleDTO().isCancelable())
                            .filter(r -> !"취소됨".equals(r.getState())) // ✅ 수정됨
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
                    List<ReservationDTO> all = reservationMapper.selectAllReservations();

                    Optional<ReservationDTO> target = all.stream()
                            .filter(r -> r.getReservationNum().equals(reservationNum))
                            .filter(r -> r.getScheduleDTO().isCancelable())
                            .filter(r -> !"취소됨".equals(r.getState())) // ✅ 수정됨
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

                // 3️⃣ 실제 예매 취소 처리
                case "movie_cancel_step3": {
                    String reservationNum = String.valueOf(data.get("reservationNum"));

                    if (reservationNum == null || reservationNum.trim().isEmpty()) {
                        result.put("error", "예매 번호가 유효하지 않습니다.");
                        break;
                    }

                    boolean success = reservationCRUDService.updateReservationState(reservationNum);

                    if (success) {
                        result.put("message", "✅ 예매가 성공적으로 취소되었습니다.");
                    } else {
                        result.put("message", "⚠️ 예매 취소에 실패했습니다. 예매 번호를 확인해주세요.");
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
