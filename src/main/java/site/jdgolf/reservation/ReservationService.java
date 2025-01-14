package site.jdgolf.reservation;

import static site.jdgolf.reservation.ReservationType.LESSON;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.jdgolf.member.Member;
import site.jdgolf.member.MemberService;
import site.jdgolf.practicetee.PracticeTee;
import site.jdgolf.practicetee.PracticeTeeService;
import site.jdgolf.practicetee.PracticeTeeStatus;
import site.jdgolf.practicetee.TotalTeeStatus;
import site.jdgolf.reservation.dto.LessonAcceptRequest;
import site.jdgolf.reservation.dto.LessonRequest;
import site.jdgolf.reservation.dto.ReservationRequest;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final MemberService memberService;
    private final PracticeTeeService teeService;

    @Transactional
    public boolean registerReservation(ReservationRequest request) {
//        // Check reservable
//        if (checkReserved(request.getTime(),
//            request.getTime().plusMinutes(30 * request.getHeadCount()))) {
//            log.warn("[{}] already reserved.", request.getTime());
//            return false;
//        }
//
//        Member member = memberService.findById(request.getMemberId());
//        Reservation reservation = new Reservation(member, request.getType(), request.getTime(),
//            request.getHeadCount());
//
//        reservationRepository.save(reservation);
//
//        log.info("Reservation [{} / {} ~ {} min / member [{} / {}] / {}] saved.",
//            reservation.getReservationType(), reservation.getReserveTime(),
//            30 * reservation.getHeadCount(),
//            reservation.getReservingMember().getId(),
//            reservation.getReservingMember().getName(), reservation.getAccept());
        return true;
    }

    private boolean checkReserved(LocalDateTime from, LocalDateTime to) {
        // Time && Accept
        return false;
    }

    public TotalTeeStatus getReservationStatusByDateTime(LocalDate date, String time,
        ReservationType type) {
        TotalTeeStatus status = TotalTeeStatus.ofPracticeTee(time);

        List<Reservation> reservations = reservationRepository.findPracticeTeeStatusByDateTime(date,
            time);

        for (Reservation reservation : reservations) {
            Integer teeId = reservation.getPracticeTee().getId();
            PracticeTeeStatus teeStatus = status.getTeeStatuses().get(teeId - 1);
            teeStatus.setReserved(true);
            teeStatus.setReservationType(reservation.getReservationType());
        }
        return status;
    }

    public List<TotalTeeStatus> getReservationStatusByDate(LocalDate date) {
        List<TotalTeeStatus> totalStatus = TotalTeeStatus.ofAllTimes(
            date.getDayOfWeek());

        List<Reservation> reservations = reservationRepository.findAllReservationByDate(
            date);
        System.out.println(reservations.size());

        for (Reservation reservation : reservations) {
            String reserveTime = reservation.getReserveTime();

            totalStatus.stream()
                .filter(s -> s.getTime().equals(reserveTime))
                .forEach(s -> {
                    Integer teeId = reservation.getPracticeTee().getId();
                    String memberName = reservation.getReservingMember().getName();
                    PracticeTeeStatus status = s.getTeeStatuses().get(teeId - 1);
                    status.setReserved(true);
                    status.setReservationType(reservation.getReservationType());
                    status.setMemberName(memberName);
                });
        }
        System.out.println(totalStatus);
        return totalStatus;
    }

    @Transactional
    public boolean registerLessonReservation(Member member, LessonRequest lessonRequest) {
        LocalDate date = lessonRequest.getDate();
        String time = lessonRequest.getTime();
        Integer teeId = lessonRequest.getTeeId();

        // check lesson available
        if (reservationRepository.checkAcceptedLessonExists(date, time)) {
            log.info("{} {}, Lesson already exists.", date, time);
            return false;
        }

        // create lesson request
        Reservation lessonReservation = new Reservation(member, LESSON, date, time, 1);
        reservationRepository.save(lessonReservation);
        log.info("{} {}, Lesson requested.", date, time);
        return true;
    }

    public boolean checkAcceptedLessonExists(LocalDate date, String time) {
        return reservationRepository.checkAcceptedLessonExists(date, time);
    }

    @Transactional
    public boolean acceptReservation(Member member, LessonAcceptRequest acceptRequest) {
        Reservation reservation = findReservationById(acceptRequest.getReservationId());
        if (acceptRequest.getAccept()) {
            PracticeTee tee = teeService.findByTeeId(acceptRequest.getTeeId());
            reservation.acceptedTo(tee);
            log.info("Reservation ({} {}) is accepted.", reservation.getId(), reservation.getReservationType());
        } else {
            reservation.rejected();
            log.info("Reservation ({} {}) is rejected.", reservation.getId(), reservation.getReservationType());
        }

        // TODO: Notice
        return true;
    }

    public Reservation findReservationById(Integer reservationId) {
        return reservationRepository.findReservationById(reservationId);
    }
}
