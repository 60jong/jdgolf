package site.jdgolf.reservation;

import static site.jdgolf.reservation.ReservationType.PRACTICE;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.jdgolf.member.Member;
import site.jdgolf.member.MemberService;
import site.jdgolf.practicetee.TotalTeeStatus;

@RequiredArgsConstructor
@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final MemberService memberService;

    @PostMapping("/new")
    public String requestReservation(ReservationRequest request) {
        boolean reserved = reservationService.registerReservation(request);
        return "redirect:/";
    }

    //= Lesson =//
    @GetMapping("/lessons/new")
    public String newReservation() {
        return "newLessonReservationForm";
    }


    @GetMapping("/check/lesson")
    @ResponseBody
    public boolean checkLesson(@RequestParam("date") LocalDate date,
        @RequestParam("time") String time) {
        return reservationService.checkLessonExists(date, time);
    }

    @GetMapping("/check/practicetee")
    @ResponseBody
    public TotalTeeStatus getTotalPracticeTeeStatusByDateTime(
        @RequestParam("date") LocalDate date,
        @RequestParam("time") String time) {
        return reservationService.getReservationStatusByDateTime(date, time, PRACTICE);
    }

    @GetMapping("/schedule")
    public String showReservationHistory() {
        return "schedule";
    }

    @GetMapping("/check/schedule")
    @ResponseBody
    public List<TotalTeeStatus> getAllTotalTeeStatusByDate(
        @RequestParam("date") LocalDate date) {
        return reservationService.getReservationStatusByDate(date);
    }

    @PostMapping("/lesson")
    @ResponseBody
    public Boolean createLessonReservation(@RequestBody LessonRequest lessonRequest,
        HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Integer memberId = (Integer) session.getAttribute("memberId");
        Member member = memberService.findById(memberId);

        return reservationService.registerLessonReservation(member, lessonRequest);
    }
}
