package bd.grzyby.controller;

import bd.grzyby.model.entity.*;
import bd.grzyby.repository.*;
import bd.grzyby.service.ZlecenieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    private final PracownikRepo pracownikRepo;
    private final ZlecenieService zlecenieService;
    private final ZlecenieRepo zlecenieRepo;
    private final KlientRepo klientRepo;
    private final GatunekRepo gatunekRepo;
    private final PartiaRepo partiaRepo;

    public TestController(PracownikRepo pracownikRepo, ZlecenieService zlecenieService, ZlecenieRepo zlecenieRepo, KlientRepo klientRepo, GatunekRepo gatunekRepo, PartiaRepo partiaRepo) {
        this.pracownikRepo = pracownikRepo;
        this.zlecenieService = zlecenieService;
        this.zlecenieRepo = zlecenieRepo;
        this.klientRepo = klientRepo;
        this.gatunekRepo = gatunekRepo;
        this.partiaRepo = partiaRepo;
    }

    @GetMapping
    public String test(Model model) {

        List<Pracownik> pracownicy = pracownikRepo.findAll();
        model.addAttribute("pracownicy", pracownicy);
        List<Zlecenie> zlecenia = zlecenieRepo.findAll();
        model.addAttribute("zlecenia", zlecenia);
        List<Klient> klienci = klientRepo.findAll();
        model.addAttribute("klienci", klienci);
        List<Gatunek> gatunki = gatunekRepo.findAll();
        model.addAttribute("gatunki", gatunki);
        List<Partia> partie = partiaRepo.findAll();
        model.addAttribute("partie", partie);

        return "test";
    }
}
