package bd.grzyby.controller;

import bd.grzyby.model.dto.ZlecenieForm;
import bd.grzyby.model.entity.Zlecenie;
import bd.grzyby.service.ZlecenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/zlecenie")
public class ZlecenieController {

    private final ZlecenieService zlecenieService;

    @Autowired
    public ZlecenieController(ZlecenieService zlecenieService) {
        this.zlecenieService = zlecenieService;
    }

    // Display all orders
    @GetMapping
    public String getZlecenia(Model model) {
        List<Zlecenie> zlecenia = zlecenieService.getAllZlecenia();
        model.addAttribute("zlecenia", zlecenia);
        return "zlecenie"; // Matches the zlecenia.html template
    }

    // Show form to add a new order
    @GetMapping("/add")
    public String addZlecenieForm(Model model) {
        model.addAttribute("zlecenie", new Zlecenie());
        return "addZlecenie"; // You need a template for adding an order
    }

    // Handle form submission for adding a new order
    @PostMapping("/add")
    public String addZlecenie(@ModelAttribute("zlecenie") ZlecenieForm form) {
        zlecenieService.dodajZlecenie(form);
        return "redirect:/zlecenie"; // Redirect back to the orders page
    }

    // Show form to edit an existing order
    @GetMapping("/edit/{id}")
    public String editZlecenieForm(@PathVariable Long id, Model model) {
        Zlecenie zlecenie = zlecenieService.getZlecenieById(id);
        model.addAttribute("zlecenie", zlecenie);
        return "editZlecenie"; // You need a template for editing an order
    }

    // // Handle form submission for editing an order
    // @PostMapping("/edit/{id}")
    // public String editZlecenie(@PathVariable Long id, @ModelAttribute("zlecenie") Zlecenie zlecenie) {
    //     Zlecenie existingZlecenie = zlecenieService.getZlecenieById(id);
    //     existingZlecenie.setData(zlecenie.getData());
    //     existingZlecenie.setKlient(zlecenie.getKlient());
    //     existingZlecenie.setStatus(zlecenie.getStatus());
    //     zlecenieService.saveZlecenie(existingZlecenie);
    //     return "redirect:/zlecenia";
    // }

    // // Delete an order
    // @GetMapping("/delete/{id}")
    // public String deleteZlecenie(@PathVariable Long id) {
    //     zlecenieService.deleteZlecenie(id);
    //     return "redirect:/zlecenia";
    // }
}
