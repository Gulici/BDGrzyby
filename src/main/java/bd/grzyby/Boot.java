package bd.grzyby;

import bd.grzyby.model.dto.CreatePartiaForm;
import bd.grzyby.model.dto.OcenaPartiiForm;
import bd.grzyby.model.entity.*;
import bd.grzyby.repository.*;
import bd.grzyby.service.GatunekService;
import bd.grzyby.service.ModyfikacjaPomService;
import bd.grzyby.service.PartiaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Boot implements CommandLineRunner {
    private final GatunekService gatunekService;
    private final PomieszczenieRepo pomieszczenieRepo;
    private final KlientRepo klientRepo;
    private final ZlecenieRepo zlecenieRepo;
    private final DetaleZleceniaRepo detaleZleceniaRepo;
    private final PracownikRepo pracownikRepo;
    private final UprawnienieRepo uprawnienieRepo;
    private final PartiaRepo partiaRepo;
    private final OcenaPartiiRepo ocenaPartiiRepo;
    private final DetaleOcenyRepo detaleOcenyRepo;
    private final PartiaService partiaService;
    private final ModyfikacjaPomService modyfikacjaPomService;
    private final ModyfikacjaPomRepo modyfikacjaPomRepo;

    public Boot(GatunekService gatunekService, PomieszczenieRepo pomieszczenieRepo, KlientRepo klientRepo, ZlecenieRepo zlecenieRepo, DetaleZleceniaRepo detaleZleceniaRepo, PracownikRepo pracownikRepo, UprawnienieRepo uprawnienieRepo, PartiaRepo partiaRepo, OcenaPartiiRepo ocenaPartiiRepo, DetaleOcenyRepo detaleOcenyRepo, PartiaService partiaService, ModyfikacjaPomService modyfikacjaPomService, ModyfikacjaPomRepo modyfikacjaPomRepo) {
        this.gatunekService = gatunekService;
        this.pomieszczenieRepo = pomieszczenieRepo;
        this.klientRepo = klientRepo;
        this.zlecenieRepo = zlecenieRepo;
        this.detaleZleceniaRepo = detaleZleceniaRepo;
        this.pracownikRepo = pracownikRepo;
        this.uprawnienieRepo = uprawnienieRepo;
        this.partiaRepo = partiaRepo;
        this.ocenaPartiiRepo = ocenaPartiiRepo;
        this.detaleOcenyRepo = detaleOcenyRepo;
        this.partiaService = partiaService;
        this.modyfikacjaPomService = modyfikacjaPomService;
        this.modyfikacjaPomRepo = modyfikacjaPomRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        dodajGatunki();
        dodajPomieszczenia();
        dodajKlientow();
        dodajZlecenia();
        dodajUprawnienia();
        dodajPracownikow();
        dodajPartie();
        dodajModPom();
        usunPartie();
    }

    private void usunPartie() {
        partiaService.usunPartie(1L);
    }


    private void dodajModPom() {
        if(modyfikacjaPomRepo.findAll().isEmpty()) {
            partiaService.przeniesPartie(1L,3L,1L);
            partiaService.przeniesPartie(2L,2L,1L);
        }
    }

    private void dodajPartie() {
        if(partiaRepo.findAll().isEmpty()) {
            CreatePartiaForm form = new CreatePartiaForm(1L,2L,1L,1L,10);
            partiaService.dodajNowaPartie(form);

            CreatePartiaForm form2 = new CreatePartiaForm(1L,1L,1L,1L,10);
            partiaService.dodajNowaPartie(form2);

            OcenaPartiiForm ocenaPartiiForm = new OcenaPartiiForm(1L,1L,5,"Ocena test");
            partiaService.ocenPartie(ocenaPartiiForm);
        }
    }

    private void dodajUprawnienia() {
        if(uprawnienieRepo.findAll().isEmpty()) {
            uprawnienieRepo.save(new Uprawnienie("PRACOWNIK"));
            uprawnienieRepo.save(new Uprawnienie("KIEROWNIK"));
            uprawnienieRepo.save(new Uprawnienie("MANAGER"));
        }
    }

    private void dodajPracownikow() {
        if(pracownikRepo.findAll().isEmpty()) {
            Pracownik pracownik = new Pracownik("Kamil", "Slimak", "k.s@gmail.com", "pass");
            Uprawnienie u1 = uprawnienieRepo.findByNazwa("PRACOWNIK");
            Uprawnienie u2 = uprawnienieRepo.findByNazwa("KIEROWNIK");

            pracownik.getUprawnienia().add(u1);
            pracownik.getUprawnienia().add(u2);

            pracownikRepo.save(pracownik);
        }
    }

    private void dodajZlecenia() {
        if(zlecenieRepo.findAll().isEmpty()){
            Klient klient;
            klient = klientRepo.getKlientByFirma("Grzybojady");
            Zlecenie zlecenie = new Zlecenie(klient, new Date());

            Gatunek gatunek = gatunekService.getGatunekByName("Pieczarka");

            DetaleZlecenia detaleZlecenia = new DetaleZlecenia(gatunek,zlecenie,10,1);
            zlecenie.getDetaleZlecenia().add(detaleZlecenia);

            zlecenieRepo.save(zlecenie);
        }
    }

    private void dodajKlientow() {
        if(klientRepo.findAll().isEmpty()){
            Klient klient = new Klient("Grzybojady","Jan", "Kowalski","jk@gmail.com");
            klientRepo.save(klient);
        }
    }

    private void dodajPomieszczenia() {
        if(pomieszczenieRepo.findAll().isEmpty()) {
            pomieszczenieRepo.save(new Pomieszczenie());
            pomieszczenieRepo.save(new Pomieszczenie());
            pomieszczenieRepo.save(new Pomieszczenie());
            pomieszczenieRepo.save(new Pomieszczenie());
            pomieszczenieRepo.save(new Pomieszczenie());
        }
    }

    private void dodajGatunki() {
        if(gatunekService.getAllGatunek().isEmpty()) {
            gatunekService.addGatunek(new Gatunek("Pieczarka",5.0));
            gatunekService.addGatunek(new Gatunek("Boczniak",7.5));
        }
    }
}
