package bd.grzyby.controller;

import bd.grzyby.model.entity.Klient;
import bd.grzyby.service.KlientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/klienci")
public class KlientController {

    private final KlientService klientService;

    public KlientController(KlientService klientService) {
        this.klientService = klientService;
    }

    @GetMapping
    public String showKlienci(Model model) {
        List<Klient> klienci = klientService.findAllKlienci();
        model.addAttribute("klienci", klienci);
        return "klienci";
    }

    @GetMapping("/add")
    public String addKlientForm(Model model) {
        model.addAttribute("klient", new Klient());
        return "addKlient";
    }

    @PostMapping("/add")
    public String addKlient(@ModelAttribute Klient klient) {
        klientService.addKlient(klient);
        return "redirect:/klienci";
    }

    @GetMapping("/edit/{id}")
    public String editKlientForm(@PathVariable Long id, Model model) {
        Klient klient = klientService.findKlientById(id);
        model.addAttribute("klient", klient);
        return "editKlient";
    }

    @PostMapping("/edit/{id}")
    public String editKlient(@PathVariable Long id, @ModelAttribute Klient klient) {
        klientService.updateKlient(id, klient);
        return "redirect:/klienci";
    }

    @GetMapping("/delete/{id}")
    public String deleteKlient(@PathVariable Long id) {
        klientService.deleteKlient(id);
        return "redirect:/klienci";
    }
}
