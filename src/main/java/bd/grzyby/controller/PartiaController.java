package bd.grzyby.controller;

import bd.grzyby.model.dto.CreatePartiaForm;
import bd.grzyby.model.entity.*;
import bd.grzyby.service.PartiaService;
import bd.grzyby.service.GatunekService;
import bd.grzyby.service.PomieszczenieService;
import bd.grzyby.service.ZlecenieService;
import bd.grzyby.service.PracownikService;
import bd.grzyby.model.dto.CreatePartiaForm;
import bd.grzyby.model.dto.OcenaPartiiForm;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/partie")
public class PartiaController {

    private final PartiaService partiaService;
    private final ZlecenieService zlecenieService;
    private final GatunekService gatunekService;
    private final PomieszczenieService pomieszczenieService;
    private final PracownikService pracownikService;

    public PartiaController(PartiaService partiaService, ZlecenieService zlecenieService, GatunekService gatunekService, PomieszczenieService pomieszczenieService, PracownikService pracownikService) {
        this.partiaService = partiaService;
        this.zlecenieService = zlecenieService;
        this.gatunekService = gatunekService;
        this.pomieszczenieService = pomieszczenieService;
        this.pracownikService = pracownikService;
    }

    @GetMapping
    public String showPartie(Model model) {
        List<Partia> partie = partiaService.getPartie();
        model.addAttribute("partie", partie);
        return "partie";
    }

    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("partia", new CreatePartiaForm());
        model.addAttribute("zlecenia", zlecenieService.getAllZlecenia());
        model.addAttribute("gatunki", gatunekService.getAllGatunek());
        model.addAttribute("pomieszczenia", pomieszczenieService.getAllPomieszczenie());
        return "addPartia";
    }

    @PostMapping("/addPartia")
    public String createPartia(@ModelAttribute("partia") CreatePartiaForm form) {
        try {
            partiaService.dodajNowaPartie(form);
        } catch (Exception e) {
            // Log the error (optional)
            return "redirect:/partie/add?error=true"; // Redirect to the form with an error message
        }
        return "redirect:/partie"; // Redirect back to the list of parties
    }

    @GetMapping("/delete/{id}")
    public String deletePartia(@PathVariable Long id) {
        partiaService.usunPartie(id);
        return "redirect:/partie";
    }

    @GetMapping("/transfer/{id}")
    public String showTransferForm(Model model, @PathVariable Long id) {
        List<Pomieszczenie> pomieszczenia = pomieszczenieService.getAllPomieszczenie();
        model.addAttribute("pomieszczenia", pomieszczenia);
        model.addAttribute("id", id);
        return "transferPartia";
    }

    @PostMapping("/transfer/{id}")
    public String transferPartia(@RequestParam Long idPom, @AuthenticationPrincipal UserDetails userDetails, @PathVariable String id) {
        Long idPartia = Long.parseLong(id);
        Pracownik pracownik = pracownikService.getPracownik(userDetails.getUsername());
        partiaService.przeniesPartie(idPartia, idPom, pracownik.getId());
        return "redirect:/partie";
    }

    @GetMapping("/rate/{id}")
    public String showRateForm(Model model, @PathVariable Long id) {
        model.addAttribute("id", id);
        return "ratePartia";
    }

    @PostMapping("/rate/{id}")
    public String ratePartia(@PathVariable Long id, @RequestParam int ocena, @RequestParam String opis, @AuthenticationPrincipal UserDetails userDetails) {
        Long idPracownik = pracownikService.getPracownik(userDetails.getUsername()).getId();
        partiaService.ocenPartie(new OcenaPartiiForm(id, idPracownik, ocena, opis));
        return "redirect:/partie";
    }
}
