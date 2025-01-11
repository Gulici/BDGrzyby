package bd.grzyby.service;

import bd.grzyby.model.dto.CreatePartiaForm;
import bd.grzyby.model.dto.OcenaPartiiForm;
import bd.grzyby.model.entity.*;
import bd.grzyby.repository.OcenaPartiiRepo;
import bd.grzyby.repository.PartiaRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PartiaService {
    private final PartiaRepo partiaRepo;

    private final ZlecenieService zlecenieService;
    private final GatunekService gatunekService;
    private final PomieszczenieService pomieszczenieService;
    private final PracownikService pracownikService;
    private final ModyfikacjaPomService modyfikacjaPomService;

    public PartiaService(PartiaRepo partiaRepo, ZlecenieService zlecenieService, GatunekService gatunekService, PomieszczenieService pomieszczenieService, PracownikService pracownikService, ModyfikacjaPomService modyfikacjaPomService) {
        this.partiaRepo = partiaRepo;
        this.zlecenieService = zlecenieService;
        this.gatunekService = gatunekService;
        this.pomieszczenieService = pomieszczenieService;
        this.pracownikService = pracownikService;
        this.modyfikacjaPomService = modyfikacjaPomService;
    }

    public void dodajNowaPartie(CreatePartiaForm form) {
        Pracownik pracownik = pracownikService.getPracownik(form.getIdPracownik());
        Zlecenie zlecenie = zlecenieService.getZlecenieById(form.getIdZlecenie());
        Gatunek gatunek = gatunekService.getGatunekById(form.getIdGatunek());
        Pomieszczenie pomieszczenie = pomieszczenieService.getPomById(form.getIdPomieszczenie());

        Date data = new Date();
        Partia newPartia = new Partia(zlecenie,gatunek,pomieszczenie,1,data);

        DetaleOceny detaleOceny = new DetaleOceny("Utworzono");
        OcenaPartii ocenaPartii = new OcenaPartii(newPartia,detaleOceny,pracownik,form.getOcena(),1,data);
        newPartia.getOcenyPartii().add(ocenaPartii);

        partiaRepo.save(newPartia);
    }

    public void ocenPartie(OcenaPartiiForm form){
        Partia partia = partiaRepo.findPartiaById(form.getIdPartia());
        Pracownik pracownik = pracownikService.getPracownik(form.getIdPracownik());
        DetaleOceny detaleOceny = new DetaleOceny(form.getOpis());
        OcenaPartii ocenaPartii = new OcenaPartii(partia,detaleOceny,pracownik,form.getOcena(),partia.getEtap(),new Date());
        partia.getOcenyPartii().add(ocenaPartii);
        partiaRepo.save(partia);
    }

    public void przeniesPartie(Long idPartia, Long idPom, Long idPracownik) {
        Pracownik pracownik = pracownikService.getPracownik(idPracownik);
        Partia partia = partiaRepo.findPartiaById(idPartia);
        int idPomP = partia.getPomieszczenie().getId().intValue();
        Pomieszczenie pom = pomieszczenieService.getPomById(idPom);

        partia.setPomieszczenie(pom);
        partia.setEtap(idPom.intValue());

        ModyfikacjaPom mod = new ModyfikacjaPom(partia, idPomP, idPom.intValue(),new Date(),pracownik,partia.getEtap());
        modyfikacjaPomService.dodajMod(mod);
        partiaRepo.save(partia);
    }

    public void usunPartie(Long idPartia) {
        modyfikacjaPomService.usunModyfikacje(idPartia);
        partiaRepo.delete(partiaRepo.findPartiaById(idPartia));
    }
}
