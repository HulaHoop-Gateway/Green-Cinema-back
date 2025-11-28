package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.SeatReservationId.model.service.SeatReservationService;
import com.novacinema.cinemaFranchise.model.dao.CinemaFranchiseMapper;
import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservationCRUD.service.ReservationCRUDService;
import com.novacinema.schedule.model.dao.ScheduleMapper;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.user.model.dao.UserMapper;
import com.novacinema.user.model.dto.UserDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieBookingService {

    private static final Logger log = LoggerFactory.getLogger(MovieBookingService.class);

    private final CinemaFranchiseMapper cinemaFranchiseMapper;
    private final ScheduleMapper scheduleMapper;
    private final SeatReservationService seatReservationService;
    private final ReservationCRUDService reservationCRUDService;
    private final UserMapper userMapper;

    public MovieBookingService(CinemaFranchiseMapper cinemaFranchiseMapper,
            ScheduleMapper scheduleMapper,
            SeatReservationService seatReservationService,
            ReservationCRUDService reservationCRUDService,
            UserMapper userMapper) {
        this.cinemaFranchiseMapper = cinemaFranchiseMapper;
        this.scheduleMapper = scheduleMapper;
        this.seatReservationService = seatReservationService;
        this.reservationCRUDService = reservationCRUDService;
        this.userMapper = userMapper;
    }

    public Map<String, Object> processIntent(String intent, Map<String, Object> data) {

        Map<String, Object> result = new HashMap<>();

        try {
            switch (intent) {

                case "movie_booking_step1": {

                    List<CinemaFranchiseDTO> nearest = cinemaFranchiseMapper.findAllCinemaFranchises();

                    List<Map<String, Object>> cinemaMaps = nearest.stream()
                            .map(dto -> {
                                Map<String, Object> m = new HashMap<>();
                                m.put("branch_num", dto.getBranchNum());
                                m.put("branch_name", dto.getBranchName());
                                m.put("address", dto.getAddress());
                                return m;
                            })
                            .toList();

                    result.put("cinemas", cinemaMaps);
                    break;
                }

                case "movie_booking_step2": {

                    String branchNum = String.valueOf(data.get("branchNum"));
                    String date = (String) data.get("dateFilter");

                    List<ScheduleDTO> schedules;

                    if (date == null || date.isBlank()) {
                        schedules = scheduleMapper.findSchedulesByBranchNumAndDate(
                                branchNum,
                                LocalDateTime.now().toLocalDate().toString());
                    } else {
                        schedules = scheduleMapper.findSchedulesByBranchNumAndDate(branchNum, date);
                    }

                    List<Map<String, Object>> scheduleMaps = schedules.stream().map(s -> {
                        Map<String, Object> m = new HashMap<>();
                        m.put("scheduleNum", s.getScheduleNum());
                        m.put("screeningDate", s.getScreeningDate());
                        m.put("screeningNumber", s.getTheaterInfo().getScreeningNumber());
                        m.put("branchName", s.getTheaterInfo().getCinemaFranchisedto().getBranchName());
                        m.put("movieTitle", s.getMovieInfo().getMovieTitle());
                        return m;
                    }).toList();

                    result.put("movies", scheduleMaps);
                    break;
                }

                case "movie_booking_step3": {

                    int scheduleNum = Integer.parseInt(String.valueOf(data.get("scheduleNum")));

                    List<SeatDTO> allSeats = seatReservationService.getAllSeatsByScheduleNum(scheduleNum);
                    List<SeatDTO> availableSeats = seatReservationService.getAvailableSeats(scheduleNum);

                    Set<Integer> availableSeatCodes = availableSeats.stream()
                            .map(SeatDTO::getSeatCode)
                            .collect(Collectors.toSet());

                    List<Map<String, Object>> seatMaps = allSeats.stream().map(seat -> {
                        Map<String, Object> m = new HashMap<>();
                        m.put("seat_code", seat.getSeatCode());
                        m.put("row_label", seat.getRowLabel());
                        m.put("col_num", seat.getColNum());
                        m.put("is_aisle", seat.getIsAisle());
                        m.put("reserved", !availableSeatCodes.contains(seat.getSeatCode()));
                        return m;
                    }).toList();

                    result.put("seats", seatMaps);
                    break;
                }

                case "movie_booking_step4": {

                    int scheduleNum = Integer.parseInt(String.valueOf(data.get("scheduleNum")));
                    int seatCode = Integer.parseInt(String.valueOf(data.get("seatCode")));
                    String phoneNumber = String.valueOf(data.get("phoneNumber"));
                    String bookingGroupId = String.valueOf(data.get("bookingGroupId")); // ✅ 그룹ID 추출

                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setScheduleNum(scheduleNum);
                    reservationDTO.setPhoneNumber(phoneNumber);
                    reservationDTO.setSeatNumber(seatCode);
                    reservationDTO.setState("예매완료");
                    reservationDTO.setPaymentTime(LocalDateTime.now());
                    reservationDTO.setBookingGroupId(bookingGroupId); // ✅ 그룹ID 설정

                    SeatReservationDTO seatReservationDTO = new SeatReservationDTO();
                    seatReservationDTO.setScheduleNum(scheduleNum);
                    seatReservationDTO.setSeatCode(seatCode);
                    seatReservationDTO.setReserved(true);
                    seatReservationDTO.setReservedAt(new Timestamp(System.currentTimeMillis()));

                    try {
                        reservationCRUDService.reserveSeatAndInsertReservation(reservationDTO, seatReservationDTO);
                        // ❌ 개별 좌석 예약 시에는 관리자 서버 전송 안 함 (일괄 전송으로 변경)
                        // sendTransactionToAdminServer(phoneNumber, scheduleNum, seatCode);
                        result.put("message", "🎉 예매가 완료되었습니다!");
                    } catch (RuntimeException e) {
                        result.put("error", "예매 처리 중 오류 발생: " + e.getMessage());
                    } catch (Exception e) {
                        result.put("error", "예매 처리 중 오류 발생: " + e.getMessage());
                    }

                    break;
                }

                // ⭐ 5️⃣ 예매 확정 후 관리자 서버 전송 (일괄 처리)
                case "movie_booking_finalize": {
                    int scheduleNum = Integer.parseInt(String.valueOf(data.get("scheduleNum")));
                    String phoneNumber = String.valueOf(data.get("phoneNumber"));
                    int totalAmount = Integer.parseInt(String.valueOf(data.get("totalAmount")));

                    // 관리자 서버로 전송 (총 금액)
                    sendTransactionToAdminServer(phoneNumber, scheduleNum, totalAmount);
                    result.put("message", "관리자 서버 전송 완료");
                    break;
                }

                // 회원 확인
                case "member_check": {
                    String phoneNumber = String.valueOf(data.get("phone"));
                    UserDTO user = userMapper.findByPhoneNumber(phoneNumber);
                    result.put("exists", user != null);
                    break;
                }

                default:
                    result.put("error", "알 수 없는 intent입니다: " + intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "처리 중 오류 발생: " + e.getMessage());
        }

        return result;
    }

    // 관리자 서버로 거래 기록 전송
    private void sendTransactionToAdminServer(String phoneNumber, int scheduleNum, int totalAmount) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "http://localhost:8000/api/transactions/add";

            // ⭐ scheduleNum → merchant_code 조회
            String merchantCode = scheduleMapper.findMerchantCodeByScheduleNum(scheduleNum);

            if (merchantCode == null || merchantCode.isEmpty()) {
                log.error("❌ merchantCode 조회 실패: scheduleNum={}", scheduleNum);
                return;
            }

            // ⭐ 스케줄 정보 조회 (종료 시간 계산용)
            ScheduleDTO schedule = scheduleMapper.selectScheduleByNum(scheduleNum);
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;

            if (schedule != null) {
                startDate = schedule.getScreeningDate();
                if (schedule.getMovieInfo() != null) {
                    int runningTime = schedule.getMovieInfo().getRunningTime();
                    if (startDate != null) {
                        endDate = startDate.plusMinutes(runningTime);
                    }
                }
            }

            // ⭐ 날짜 포맷팅 (yyyy-MM-dd HH:mm:ss)
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss");
            String startDateStr = startDate != null ? startDate.format(formatter) : null;
            String endDateStr = endDate != null ? endDate.format(formatter) : null;

            // ⭐ 관리자 서버 JSON 규칙에 맞게 camelCase 사용
            Map<String, Object> payload = new HashMap<>();
            payload.put("phoneNum", phoneNumber);
            payload.put("merchantCode", merchantCode);
            payload.put("amountUsed", totalAmount); // 총 금액
            payload.put("status", "P");
            payload.put("startDate", startDateStr);
            payload.put("endDate", endDateStr);

            log.info("📤 관리자 서버로 거래 기록 전송 시도: url={}, payload={}", url, payload);

            ResponseEntity<String> response = restTemplate.postForEntity(url, payload, String.class);
            log.info("✅ 관리자 서버 응답: {}", response.getBody());

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("❌ 관리자 서버 거래 전송 실패 (HTTP {}): status={}, response={}",
                    e.getStatusCode(), e.getStatusCode().value(), e.getResponseBodyAsString(), e);
        } catch (RestClientException e) {
            log.error("❌ 관리자 서버 거래 전송 실패 (네트워크 오류): message={}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("❌ 관리자 서버 거래 전송 실패 (예상치 못한 오류): message={}", e.getMessage(), e);
        }
    }
}
