package bd.grzyby.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
@Controller
public class landingPageController {
    @GetMapping("/")
    public String landing(){
        return "landingPage";
    }

    @RequestMapping("/signin")
    public String handleSigninButton(){
        return "signin";
    }

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
