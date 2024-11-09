package site.jdgolf.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("logInRequest", new LogInRequest());
        return "loginForm";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute LogInRequest logInRequest,
        HttpServletRequest request) {
        String phoneNumber = logInRequest.getPhoneNumber();
        Integer memberId = logInRequest.getMemberId();

        // 사용자 검증 로직
        if (isValidUser(phoneNumber, memberId)) {
            // 세션에 사용자 이름을 저장 (예: "aaa"로 하드코딩)
            String username = "aaa";
            HttpSession session = request.getSession();
            session.setAttribute("memberId", memberId);

            // 로그인 성공 시 홈 페이지로 리다이렉트
            return "redirect:/";
        } else {
            // 로그인 실패 시 에러 메시지와 함께 로그인 페이지로 이동
            return "redirect:/members/login";
        }
    }

    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<MemberResponse> checkMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return ResponseEntity.status(401).build();
        }

        Integer memberId = (Integer) session.getAttribute("memberId");
        Member member = memberService.findById(memberId);
        return ResponseEntity.ok(new MemberResponse(member.getName(), memberId));
    }

    // 사용자 검증 메서드 (예시)
    private boolean isValidUser(String phoneNumber, Integer memberId) {
        // 실제 검증 로직을 구현하세요 (예: DB 조회)
        return "01012345678".equals(phoneNumber) && memberId == 1234;
    }

    @GetMapping("/register")
    public String memberRegisterForm(Model model) {
        model.addAttribute("memberRequest", new MemberRequest());
        return "memberRegisterForm";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute MemberRequest memberRequest) {
        memberService.join(memberRequest);
        return "redirect:/";
    }


}
