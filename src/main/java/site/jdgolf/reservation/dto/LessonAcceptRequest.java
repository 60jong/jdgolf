package site.jdgolf.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LessonAcceptRequest {
    private Integer reservationId;
    private Integer teeId;
    private Boolean accept;
    private String comment;
}
