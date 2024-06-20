package jpacalendarchallenge.jpacacha.controller;

import jakarta.servlet.http.HttpSession;
import jpacalendarchallenge.jpacacha.domain.Member;
import jpacalendarchallenge.jpacacha.domain.Roulette.Roulette;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RouletteController {

    private final Roulette roulette;

    public RouletteController() {
        String[] prizes = {"100원", "200원", "300원", "400원", "500원"};
        int[] probabilities = {35, 25, 20, 15, 5}; // 각 확률을 설정
        this.roulette = new Roulette(prizes, probabilities);
    }

    @GetMapping("/roulette/{id}")
    public String showRoulettePage(@PathVariable("id") Long id, HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (member == null || !member.getId().equals(id)) {
            model.addAttribute("alertMessage", "회원전용 이벤트입니다");
            return "redirect:/";
        }
        model.addAttribute("memberId", id);
        return "Roulette/PlayRoulette";
    }

    @GetMapping("/spin")
    @ResponseBody
    public Map<String, String> spinRoulette() {
        String result = roulette.spin();
        Map<String, String> response = new HashMap<>();
        response.put("result", result);
        return response;
    }
}

