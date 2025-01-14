package bd.grzyby.controller;

import bd.grzyby.model.entity.Gatunek;
import bd.grzyby.service.GatunekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gatunki")
public class GatunekController {

    private final GatunekService gatunekService;

    @Autowired
    public GatunekController(GatunekService gatunekService) {
        this.gatunekService = gatunekService;
    }

    // Display all records
    @GetMapping
    public String getAllGatunki(Model model) {
        List<Gatunek> gatunki = gatunekService.getAllGatunek();
        model.addAttribute("gatunki", gatunki);
        return "gatunki"; // Matches Gatunki.html template
    }

    // Show form to add a new record
    @GetMapping("/add")
    public String addGatunekForm(Model model) {
        model.addAttribute("gatunek", new Gatunek());
        return "addGatunek"; // Template for adding a record
    }

    // Handle form submission for adding a new record
    @PostMapping("/add")
    public String addGatunek(@ModelAttribute("gatunek") Gatunek gatunek) {
        gatunekService.addGatunek(gatunek);
        return "redirect:/gatunki";
    }

    // Show form to edit an existing record
    @GetMapping("/edit/{id}")
    public String editGatunekForm(@PathVariable Long id, Model model) {
        Gatunek gatunek = gatunekService.getGatunekById(id);
        model.addAttribute("gatunek", gatunek);
        return "editGatunek"; // Template for editing a record
    }

    // Handle form submission for editing a record
    @PostMapping("/edit/{id}")
    public String editGatunek(@PathVariable Long id, @ModelAttribute("gatunek") Gatunek updatedGatunek) {
        Gatunek existingGatunek = gatunekService.getGatunekById(id);
        existingGatunek.setNazwa(updatedGatunek.getNazwa());
        existingGatunek.setCena(updatedGatunek.getCena());
        gatunekService.addGatunek(existingGatunek);
        return "redirect:/gatunki";
    }

    // Delete a record
    @GetMapping("/delete/{id}")
    public String deleteGatunek(@PathVariable Long id) {
        Gatunek gatunek = gatunekService.getGatunekById(id);
        gatunekService.removeGatunek(gatunek);
        return "redirect:/gatunki";
    }
}
