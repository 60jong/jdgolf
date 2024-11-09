package site.jdgolf.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ReservationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // JSESSIONID 쿠키가 있는지 확인
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName()) && request.getSession(false) != null) {
                    // JSESSIONID 쿠키가 있고 세션이 유효할 때 접근 허용
                    return true;
                }
            }
        }
        // JSESSIONID가 없거나 세션이 유효하지 않은 경우 로그인 페이지로 리다이렉트
        response.sendRedirect("/members/login");
        return false;
    }
}