package jpacalendarchallenge.jpacacha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // lombok에서 log 사용할수있게
public class HomeController {
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home"; // home.html 을 띄운다
    }
}
