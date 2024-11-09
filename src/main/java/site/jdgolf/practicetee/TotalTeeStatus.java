package site.jdgolf.practicetee;

import static java.time.DayOfWeek.*;
import static site.jdgolf.reservation.ReservationType.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TotalTeeStatus {


    private String time;
    private List<PracticeTeeStatus> teeStatuses;


    private static List<DayOfWeek> SHORT_TIME_DAYS = List.of(SATURDAY, SUNDAY);

    public static TotalTeeStatus ofPracticeTee(String time) {
        return new TotalTeeStatus(time, new ArrayList<>(
            List.of(
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE))));
    }

    public static TotalTeeStatus ofAllTee(String time) {
        return new TotalTeeStatus(time, new ArrayList<>(
            List.of(
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(PRACTICE),
                PracticeTeeStatus.empty(SCREEN),
                PracticeTeeStatus.empty(SCREEN))));
    }


    private static List<TotalTeeStatus> ofAllTime() {
        return List.of(
            TotalTeeStatus.ofAllTee("11:00"),
            TotalTeeStatus.ofAllTee("11:30"),
            TotalTeeStatus.ofAllTee("12:00"),
            TotalTeeStatus.ofAllTee("12:30"),
            TotalTeeStatus.ofAllTee("13:00"),
            TotalTeeStatus.ofAllTee("13:30"),
            TotalTeeStatus.ofAllTee("14:00"),
            TotalTeeStatus.ofAllTee("14:30"),
            TotalTeeStatus.ofAllTee("15:00"),
            TotalTeeStatus.ofAllTee("15:30"),
            TotalTeeStatus.ofAllTee("16:00"),
            TotalTeeStatus.ofAllTee("16:30"),
            TotalTeeStatus.ofAllTee("17:00"),
            TotalTeeStatus.ofAllTee("17:30"),
            TotalTeeStatus.ofAllTee("18:00"),
            TotalTeeStatus.ofAllTee("18:30"),
            TotalTeeStatus.ofAllTee("19:00"),
            TotalTeeStatus.ofAllTee("19:30"),
            TotalTeeStatus.ofAllTee("20:00"),
            TotalTeeStatus.ofAllTee("20:30"),
            TotalTeeStatus.ofAllTee("21:00"),
            TotalTeeStatus.ofAllTee("21:30"),
            TotalTeeStatus.ofAllTee("22:00"),
            TotalTeeStatus.ofAllTee("22:30"));
    }

    private static List<TotalTeeStatus> ofShortTime() {
        return List.of(
            TotalTeeStatus.ofAllTee("10:00"),
            TotalTeeStatus.ofAllTee("10:30"),
            TotalTeeStatus.ofAllTee("11:00"),
            TotalTeeStatus.ofAllTee("11:30"),
            TotalTeeStatus.ofAllTee("12:00"),
            TotalTeeStatus.ofAllTee("12:30"),
            TotalTeeStatus.ofAllTee("13:00"),
            TotalTeeStatus.ofAllTee("13:30"),
            TotalTeeStatus.ofAllTee("14:00"),
            TotalTeeStatus.ofAllTee("14:30"),
            TotalTeeStatus.ofAllTee("15:00"),
            TotalTeeStatus.ofAllTee("15:30"),
            TotalTeeStatus.ofAllTee("16:00"),
            TotalTeeStatus.ofAllTee("16:30"),
            TotalTeeStatus.ofAllTee("17:00"),
            TotalTeeStatus.ofAllTee("17:30"),
            TotalTeeStatus.ofAllTee("18:00"),
            TotalTeeStatus.ofAllTee("18:30"),
            TotalTeeStatus.ofAllTee("19:00"),
            TotalTeeStatus.ofAllTee("19:30"));
    }

    public static List<TotalTeeStatus> ofAllTimes(DayOfWeek dayOfWeek) {
        if (SHORT_TIME_DAYS.contains(dayOfWeek)) {
            return ofShortTime();
        }
        return ofAllTime();
    }
}
