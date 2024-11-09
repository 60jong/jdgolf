package site.jdgolf.reservation;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import site.jdgolf.practicetee.PracticeTee;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ReservationRepository {

    private final EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Map<String, String> findReservationStatus(LocalDate date, String time) {
        return null;
    }

    public Map<String, String> findReservationStatusByDateTime(LocalDate date, String time) {
        return null;
    }

    public List<Reservation> findAllReservationByDate(LocalDate date) {
        return em.createQuery("select r from Reservation r where r.reserveDate = :date",
                Reservation.class)
            .setParameter("date", date)
            .getResultList();
    }

    public List<Reservation> findPracticeTeeStatusByDateTime(LocalDate date, String time) {
        return em.createQuery(
                "select r from Reservation r "
                    + "where r.reservationType = site.jdgolf.reservation.ReservationType.PRACTICE "
                    + "and r.reserveDate = :date "
                    + "and r.reserveTime = :time",
                Reservation.class)
            .setParameter("date", date)
            .setParameter("time", time)
            .getResultList();
    }

    public boolean checkLessonExists(LocalDate date, String time) {
        return !em.createQuery(
                "select r from Reservation r "
                    + "where r.reservationType = site.jdgolf.reservation.ReservationType.LESSON "
                    + "and r.reserveDate = :date "
                    + "and r.reserveTime = :time",
                Reservation.class)
            .setParameter("date", date)
            .setParameter("time", time)
            .getResultList()
            .isEmpty();
    }

    public List<PracticeTee> findAvailableTeesByDateTime(LocalDate date, String time) {
        return null;
    }
}
