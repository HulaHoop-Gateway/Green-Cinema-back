package com.novacinema.cinemaFranchise.model.service;

import com.novacinema.SeatReservationId.model.dto.SeatReservationDTO;
import com.novacinema.SeatReservationId.model.service.SeatReservationService;
import com.novacinema.cinemaFranchise.model.dao.CinemaFranchiseMapper;
import com.novacinema.cinemaFranchise.model.dto.CinemaFranchiseDTO;
import com.novacinema.reservation.model.dto.ReservationDTO;
import com.novacinema.reservationCRUD.service.ReservationCRUDService;
import com.novacinema.schedule.model.dao.ScheduleMapper;
import com.novacinema.schedule.model.dto.ScheduleDTO;
import com.novacinema.seat.model.dto.SeatDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieBookingService {

    private final CinemaFranchiseMapper cinemaFranchiseMapper;
    private final ScheduleMapper scheduleMapper;
    private final SeatReservationService seatReservationService;
    private final ReservationCRUDService reservationCRUDService;

    public MovieBookingService(CinemaFranchiseMapper cinemaFranchiseMapper,
                               ScheduleMapper scheduleMapper,
                               SeatReservationService seatReservationService,
                               ReservationCRUDService reservationCRUDService) {
        this.cinemaFranchiseMapper = cinemaFranchiseMapper;
        this.scheduleMapper = scheduleMapper;
        this.seatReservationService = seatReservationService;
        this.reservationCRUDService = reservationCRUDService;
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
                                LocalDateTime.now().toLocalDate().toString()
                        );
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

                    ReservationDTO reservationDTO = new ReservationDTO();
                    reservationDTO.setScheduleNum(scheduleNum);
                    reservationDTO.setPhoneNumber(phoneNumber);
                    reservationDTO.setSeatNumber(seatCode);
                    reservationDTO.setState("예매완료");
                    reservationDTO.setPaymentTime(LocalDateTime.now());

                    SeatReservationDTO seatReservationDTO = new SeatReservationDTO();
                    seatReservationDTO.setScheduleNum(scheduleNum);
                    seatReservationDTO.setSeatCode(seatCode);
                    seatReservationDTO.setReserved(true);
                    seatReservationDTO.setReservedAt(new Timestamp(System.currentTimeMillis()));

                    try {
                        reservationCRUDService.reserveSeatAndInsertReservation(reservationDTO, seatReservationDTO);
                        sendTransactionToAdminServer(phoneNumber, scheduleNum);
                        result.put("message", "🎉 예매가 완료되었습니다!");
                    } catch (RuntimeException e) {
                        result.put("error", "예매 실패: " + e.getMessage());
                    } catch (Exception e) {
                        result.put("error", "예매 처리 중 오류 발생: " + e.getMessage());
                    }

                    break;
                }

                default:
                    result.put("error", "Unknown intent: " + intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "Server Error: " + e.getMessage());
        }

        return result;
    }

    // 관리자 서버로 거래 기록 전송
    private void sendTransactionToAdminServer(String phoneNumber, int scheduleNum) {

        try {
            org.springframework.web.client.RestTemplate restTemplate =
                    new org.springframework.web.client.RestTemplate();

            String url = "http://localhost:8000/api/transactions/add";

            // ⭐ scheduleNum → merchant_code 조회
            String merchantCode = scheduleMapper.findMerchantCodeByScheduleNum(scheduleNum);

            // ⭐ 관리자 서버 JSON 규칙에 맞게 camelCase 사용
            Map<String, Object> payload = new HashMap<>();
            payload.put("phoneNum", phoneNumber);
            payload.put("merchantCode", merchantCode);
            payload.put("amountUsed", 12000);
            payload.put("status", "S");
            payload.put("startDate", null);
            payload.put("endDate", null);

            restTemplate.postForObject(url, payload, String.class);

            System.out.println("🎉 관리자 서버로 거래 기록 전송 완료!");

        } catch (Exception e) {
            System.out.println("❌ 관리자 서버 거래 전송 실패: " + e.getMessage());
        }
    }
}
