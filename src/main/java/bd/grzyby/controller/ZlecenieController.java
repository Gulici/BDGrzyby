package bd.grzyby.controller;

import bd.grzyby.model.dto.DetaleZleceniaForm;
import bd.grzyby.model.dto.RodzajEnum;
import bd.grzyby.model.dto.ZlecenieForm;
import bd.grzyby.model.entity.DetaleZlecenia;
import bd.grzyby.model.entity.Zlecenie;
import bd.grzyby.service.GatunekService;
import bd.grzyby.service.ZlecenieService;
import bd.grzyby.service.KlientService;
import bd.grzyby.model.entity.Klient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/zlecenie")
public class ZlecenieController {

    private final ZlecenieService zlecenieService;

    private final KlientService klientService;
    private final GatunekService gatunekService;

    @Autowired
    public ZlecenieController(ZlecenieService zlecenieService, KlientService klientService, GatunekService gatunekService) {
        this.zlecenieService = zlecenieService;
        this.klientService = klientService;
        this.gatunekService = gatunekService;
    }

    @GetMapping
    public String getZlecenia(Model model) {
        List<Zlecenie> zlecenia = zlecenieService.getAllZlecenia();
        model.addAttribute("zlecenia", zlecenia);
        return "zlecenie";
    }

    @GetMapping("/add")
    public String addZlecenieForm(Model model) {
        model.addAttribute("zlecenie", new ZlecenieForm());
        model.addAttribute("klienci", klientService.findAllKlienci());
        model.addAttribute("gatunki", gatunekService.getAllGatunek());
        return "addZlecenie";
    }

    @PostMapping("/add")
    public String addZlecenie(@ModelAttribute("zlecenie") ZlecenieForm form) {
        zlecenieService.dodajZlecenie(form);
        return "redirect:/zlecenie"; 
    }

    @GetMapping("/edit/{id}")
    public String editZlecenieForm(@PathVariable Long id, Model model) {
        Zlecenie zlecenie = zlecenieService.getZlecenieById(id);
        model.addAttribute("zlecenie", zlecenie);
        model.addAttribute("gatunki", gatunekService.getAllGatunek());
        return "editZlecenie"; 
    }

    @GetMapping("/edit/{id}/detal")
    public String addDetalForm(@PathVariable Long id, Model model) {
        Zlecenie zlecenie = zlecenieService.getZlecenieById(id);
        DetaleZleceniaForm form = new DetaleZleceniaForm();
        model.addAttribute("form", form);
        model.addAttribute("zlecenie", zlecenie);
        model.addAttribute("gatunki", gatunekService.getAllGatunek());
        model.addAttribute("rodzaje", RodzajEnum.values());
        return "addDetalZlec";
    }

    @PostMapping("/edit/{id}/detal")
    public String addDetal(@PathVariable Long id, @ModelAttribute("form") DetaleZleceniaForm form) {
        if (form.getIlosc()<1) return "redirect:/zlecenie/edit/{id}";
        Zlecenie zlecenie = zlecenieService.getZlecenieById(id);
        zlecenieService.dodajDetale(form, zlecenie.getId());
        return "redirect:/zlecenie/edit/{id}";
    }

    @GetMapping("/edit/{id}/{idDet}")
    public String editZlecenieDetaleForm(@PathVariable Long id, @PathVariable Long idDet, Model model) {
        Zlecenie zlecenie = zlecenieService.getZlecenieById(id);
        DetaleZlecenia detaleZlecenia = zlecenieService.getDetaleZleceniaById(idDet);
        DetaleZleceniaForm form = new DetaleZleceniaForm(
                detaleZlecenia.getGatunek().getId(),
                detaleZlecenia.getIlosc(),
                detaleZlecenia.getRodzaj());
        model.addAttribute("rodzaje", RodzajEnum.values());
        model.addAttribute("form", form);
        model.addAttribute("detal", detaleZlecenia);
        model.addAttribute("zlecenie", zlecenie);
        model.addAttribute("gatunki", gatunekService.getAllGatunek());
        return "editDetaleZlec";
    }

    @PostMapping("/edit/{id}/{idDet}")
    public String editZlecenieDetale(@PathVariable Long id, @ModelAttribute("form") DetaleZleceniaForm form, @PathVariable Long idDet) {
        if(form.getIlosc()<1) return "redirect:/zlecenie/edit/{id}";
        zlecenieService.edytujDetale(form,id,idDet);
        return "redirect:/zlecenie/edit/{id}";
    }

    @GetMapping("/edit/{id}/{idDet}/remove")
    public String deleteZlecenieDetale(@PathVariable Long id,  @PathVariable Long idDet) {
        zlecenieService.usunDetale(id,idDet);
        return "redirect:/zlecenie/edit/{id}";
    }

     @GetMapping("/delete/{id}")
     public String deleteZlecenie(@PathVariable Long id) {
         zlecenieService.usunZlecenie(id);
         return "redirect:/zlecenie";
     }
}
