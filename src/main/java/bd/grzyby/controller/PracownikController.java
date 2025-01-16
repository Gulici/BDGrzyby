package bd.grzyby.controller;

import bd.grzyby.model.dto.PracownikForm;
import bd.grzyby.model.entity.Pracownik;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bd.grzyby.service.PracownikService;

import java.util.List;
import org.slf4j.Logger;

@Controller
public class PracownikController {

    private static final Logger logger = LoggerFactory.getLogger(PracownikController.class);
    private final PracownikService pracownikService;

    public PracownikController(PracownikService pracownikService) {
        this.pracownikService = pracownikService;
    }

    @GetMapping("/Pracownicy")
    public String showPracownicy(Model model) {
        List<Pracownik> pracownicy = pracownikService.getPracownicy();
        model.addAttribute("pracownicy", pracownicy);
        return "pracownicy";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "addPracownik";
    }

    @PostMapping("/addPracownik")
    public String addPracownik(PracownikForm form) {
        pracownikService.dodajPracownik(form);
        return "redirect:/Pracownik";
    }

    @GetMapping("/pracownicy/edit/{id}")
    public String editPracownikForm(@PathVariable Long id, Model model) {
        Pracownik pracownik = pracownikService.getPracownik(id);
        model.addAttribute("pracownik", pracownik);
        return "editPracownik";
    }

    @PostMapping("/pracownicy/edit/{id}")
    public String updatePracownik(@PathVariable Long id, @ModelAttribute Pracownik pracownik) {
        pracownik.setId(id); // Ensure the ID is maintained.
        pracownikService.updatePracownik(pracownik);
        return "redirect:/Pracownicy";
    }

    @GetMapping("/pracownicy/delete/{id}")
    public String deletePracownik(@PathVariable Long id) {
        pracownikService.usunPracownik(id);
        return "redirect:/Pracownicy";
    }
}
