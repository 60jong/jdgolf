package site.jdgolf.practicetee;

import static site.jdgolf.reservation.ReservationType.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.jdgolf.reservation.ReservationType;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PracticeTeeStatus {

    private boolean reserved;
    private ReservationType reservationType;
    private String memberName;

    public static PracticeTeeStatus of(ReservationType reservationType, String memberName) {
        return new PracticeTeeStatus(true, reservationType, memberName);
    }

    public static PracticeTeeStatus empty(ReservationType reservationType) {
        return new PracticeTeeStatus(false, reservationType, "");
    }
}
