package bd.grzyby.controller;

import bd.grzyby.model.dto.PracownikForm;
import bd.grzyby.model.entity.Pracownik;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import bd.grzyby.service.PracownikService;

import java.util.List;
import java.util.Objects;

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
        return "redirect:/Pracownicy";
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

    @GetMapping("/pracownicy/{id}/kier")
    public String nadajUprawnieniaKier(@PathVariable Long id) {
        pracownikService.nadajUprawnieniaKierownika(id);
        return "redirect:/pracownicy/edit/" + id;
    }

    @GetMapping("/pracownicy/{id}/men")
    public String nadajUprawnieniaMen(@PathVariable Long id) {
        pracownikService.nadajUprawnieniaManagera(id);
        return "redirect:/pracownicy/edit/" + id;
    }

    @GetMapping("/pracownicy/{id}/delete/{idUpr}")
    public String usunUprawnienie(@PathVariable Long id,@PathVariable Long idUpr, @AuthenticationPrincipal UserDetails userDetails) {
        Long idCur = pracownikService.getPracownik(userDetails.getUsername()).getId();
        if(Objects.equals(idCur, id)) {
            return "redirect:/pracownicy/edit/" + id;
        }

        if(idUpr == 2) {
            pracownikService.usunUprawnieniaKierownika(id);
        }
        if(idUpr == 3) {
            pracownikService.usunUprawnieniaManagera(id);
        }
        return "redirect:/pracownicy/edit/" + id;
    }

    @GetMapping("/pracownicy/delete/{id}")
    public String deletePracownik(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        Long idCur = pracownikService.getPracownik(userDetails.getUsername()).getId();
        if(!Objects.equals(idCur, id)) {
            pracownikService.usunPracownik(id);
        }
        return "redirect:/Pracownicy";
    }
}
