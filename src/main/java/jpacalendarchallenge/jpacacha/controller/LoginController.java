package jpacalendarchallenge.jpacacha.controller;

import jakarta.servlet.http.HttpSession;
import jpacalendarchallenge.jpacacha.Jwt;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {


    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "members/login"; // 로그인 폼 페이지 경로 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam("userid") String userid, @RequestParam("userpw") String userpw, HttpSession session, HttpServletResponse response) {
        Member member = memberService.validateMember(userid, userpw);
        if (member != null) {
            session.setAttribute("member", member); // 로그인 성공, 세션에 회원 정보 저장
            session.setAttribute("memberId", member.getId());
            session.setAttribute("role", member.getRole());

            return "redirect:/"; // 로그인 성공 시 리디렉션할 경로
        } else {
            return "members/login"; // 로그인 실패 시 로그인 폼으로 리디렉션
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈페이지나 로그인 페이지로 리디렉션
    }

}