package bd.grzyby.controller;

import bd.grzyby.model.dto.CreatePartiaForm;
import bd.grzyby.model.entity.Partia;
import bd.grzyby.service.PartiaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/partie")
public class PartiaController {

    private final PartiaService partiaService;

    public PartiaController(PartiaService partiaService) {
        this.partiaService = partiaService;
    }

    @GetMapping
    public String showPartie(Model model) {
        List<Partia> partie = partiaService.getPartie();
        model.addAttribute("partie", partie);
        return "partie";
    }

    // @GetMapping("/create")
    // public String showCreateForm(Model model) {
    //     model.addAttribute("createPartiaForm", new CreatePartiaForm());
    //     return "createPartia";
    // }

    // @PostMapping("/create")
    // public String createPartia(@ModelAttribute CreatePartiaForm form) {
    //     partiaService.dodajNowaPartie(form);
    //     return "redirect:/partie";
    // }

    @GetMapping("/delete/{id}")
    public String deletePartia(@PathVariable Long id) {
        partiaService.usunPartie(id);
        return "redirect:/partie";
    }

    @GetMapping("/transfer/{id}")
    public String showTransferForm(@PathVariable Long id, Model model) {
        model.addAttribute("partiaId", id);
        return "transferPartia";
    }

    @PostMapping("/transfer")
    public String transferPartia(@RequestParam Long idPartia, @RequestParam Long idPom, @RequestParam Long idPracownik) {
        partiaService.przeniesPartie(idPartia, idPom, idPracownik);
        return "redirect:/partie";
    }
}
