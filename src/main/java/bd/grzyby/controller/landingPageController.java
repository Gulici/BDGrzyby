package bd.grzyby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class landingPageController {
    @GetMapping("/landingPage")
    public String landing(){
        return "landingPage";
    }


}
