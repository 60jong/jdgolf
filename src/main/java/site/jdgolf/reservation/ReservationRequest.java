package site.jdgolf.reservation;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ReservationRequest {
    private ReservationType type;
    private Integer memberId;
    private LocalDateTime time;
    private int headCount;
}
