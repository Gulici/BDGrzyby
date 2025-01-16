package bd.grzyby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/")
public class landingPageController {
    @GetMapping("/landingPage")
    public String landing(){
        return "landingPage";
    }

    @GetMapping("/signin")
    public String handleSigninButton(){
        return "signin";
    }

    // @PostMapping("/signin")
    // public String handleSigninButtonPost(){
    //     return "Pracownicy";
    // }

    @RequestMapping("/authors")
    public String authors(){
        return "authors";
    }

    @RequestMapping("/aboutproject")
    public String aboutProject(){
        return "aboutproject";
    }
    @RequestMapping("/Pracownicy")
    public String Pracownicy(){
        return "Pracownicy";
    }
}
