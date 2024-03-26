package jpacalendarchallenge.jpacacha.controller;


import jakarta.validation.Valid;
import jpacalendarchallenge.jpacacha.domain.Address;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping(value = "/members/new") // GetMapping : "/members/new" 에 GET 요청이 오면 해당 HTML을 띄운다.
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new") // PostMapping : "/members/new" 주소로 POST 요청 ( 입력한 데이터를 DB에 전송 )
    public String create(@Valid MemberForm form, BindingResult result,@RequestParam(name = "action") String action) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        if ("save".equals(action)){
            Address address = new Address(form.getUserid(), form.getUserpw());
            Member member = new Member();
            member.setName(form.getName());
            member.setAddress(address);
            memberService.join(member);
        }
        return "redirect:/"; // 첫번째 페이지로 넘어간다.
    }

//    @GetMapping(value = "/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }

//    @GetMapping(value = "/mandalart")
//    public String mandalart(Model model) {
//        model.addAttribute("mandalartForm", new MandalartForm()); // MandalartForm은 적절한 폼 객체로 교체하세요.
//        return "mandalart";
//    }

    // 모델이라는 객체를 통해 화면에 보여줍니다.
}
// @Valid : MemberForm 에 있는 NotEmpty 기능 사용