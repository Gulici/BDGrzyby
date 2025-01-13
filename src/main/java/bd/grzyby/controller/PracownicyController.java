package bd.grzyby.controller;

import bd.grzyby.model.entity.Pracownik;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bd.grzyby.service.PracownikService;

import java.util.List;

@Controller
public class PracownicyController {

    private final PracownikService pracownikService;

    public PracownicyController(PracownikService pracownikService) {
        this.pracownikService = pracownikService;
    }

    @GetMapping("/Pracownicy")
    public String showPracownicy(Model model) {
        List<Pracownik> pracownicy = pracownikService.findAllPracownicy();
        model.addAttribute("Pracownicy", pracownicy);
        return "Pracownicy";
    }
}
