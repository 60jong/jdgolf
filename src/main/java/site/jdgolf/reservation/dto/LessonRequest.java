package site.jdgolf.reservation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonRequest {
    private LocalDate date;
    private String time;
    private Integer teeId;
}
