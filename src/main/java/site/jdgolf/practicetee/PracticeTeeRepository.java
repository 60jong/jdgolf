package site.jdgolf.practicetee;

import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PracticeTeeRepository {

    private final EntityManager em;

    public PracticeTee findById(Integer teeId) {
        return em.find(PracticeTee.class, teeId);
    }

    public List<PracticeTee> findNotReservedTeesByDateTime(TeeType type, LocalDate date,
        String time) {
        return em.createQuery(
                "select t from PracticeTee t "
                    + "where t.teeType = :type "
                    + "and t.id not in "
                    + "(select r.practiceTee.id from Reservation r "
                    + "where r.reserveDate = :date and r.reserveTime = :time)",
                PracticeTee.class)
            .setParameter("type", type)
            .setParameter("date", date)
            .setParameter("time", time)
            .getResultList();
    }

    public PracticeTee findAvailableTeeById(Integer teeId, LocalDate date, String time) {
        try {
            return em.createQuery(
                    "select t from PracticeTee t "
                        + "where t.id = :teeId "
                        + "and t.id not in "
                        + "(select r.practiceTee.id from Reservation r "
                        + "where r.reserveDate = :date and r.reserveTime = :time)",
                    PracticeTee.class)
                .setParameter("teeId", teeId)
                .setParameter("date", date)
                .setParameter("time", time)
                .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
