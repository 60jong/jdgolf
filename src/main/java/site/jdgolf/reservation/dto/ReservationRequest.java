package site.jdgolf.reservation.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import site.jdgolf.reservation.ReservationType;

@Getter
public class ReservationRequest {
    private ReservationType type;
    private Integer memberId;
    private LocalDateTime time;
    private int headCount;
}
