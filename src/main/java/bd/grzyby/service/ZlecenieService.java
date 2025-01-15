package bd.grzyby.service;

import bd.grzyby.model.dto.DetaleZleceniaForm;
import bd.grzyby.model.dto.ZlecenieForm;
import bd.grzyby.model.entity.*;
import bd.grzyby.repository.DetaleZleceniaRepo;
import bd.grzyby.repository.KlientRepo;
import bd.grzyby.repository.PartiaRepo;
import bd.grzyby.repository.ZlecenieRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ZlecenieService {

    private final ZlecenieRepo zlecenieRepo;
    private final KlientRepo klientRepo;
    private final GatunekService gatunekService;
    private final PartiaRepo partiaRepo;
    private final DetaleZleceniaRepo detaleZleceniaRepo;

    public ZlecenieService(ZlecenieRepo zlecenieRepo, KlientRepo klientRepo, GatunekService gatunekService, PartiaRepo partiaRepo, DetaleZleceniaRepo detaleZleceniaRepo) {
        this.zlecenieRepo = zlecenieRepo;
        this.klientRepo = klientRepo;
        this.gatunekService = gatunekService;
        this.partiaRepo = partiaRepo;
        this.detaleZleceniaRepo = detaleZleceniaRepo;
    }

    public void dodajZlecenie(ZlecenieForm form) {
        Klient klient = klientRepo.getKlientById(form.getIdKlient());
        Date data = new Date();
        Zlecenie zlecenie = new Zlecenie(klient,data);
        zlecenieRepo.save(zlecenie);
    }

    public void dodajDetale(DetaleZleceniaForm form, Long idZlecenia) {
        Zlecenie zlecenie = zlecenieRepo.getZlecenieById(idZlecenia);
        Gatunek g = gatunekService.getGatunekById(form.getIdGatunek());

        DetaleZlecenia detaleZlecenia = new DetaleZlecenia(g,zlecenie,form.getIlosc(),form.getRodzaj());
        zlecenie.getDetaleZlecenia().add(detaleZlecenia);
        zlecenieRepo.save(zlecenie);
    }

    public void usunDetale(Long id, Long idDet) {
        Zlecenie zlecenie = zlecenieRepo.getZlecenieById(id);
        DetaleZlecenia detaleZlecenia = detaleZleceniaRepo.getDetaleZleceniaById(idDet);
        zlecenie.getDetaleZlecenia().remove(detaleZlecenia);
        detaleZleceniaRepo.deleteById(idDet);
        zlecenieRepo.save(zlecenie);
    }

    public void edytujDetale(DetaleZleceniaForm form, Long idZlecenia, Long idDetale) {
        Zlecenie zlecenie = zlecenieRepo.getZlecenieById(idZlecenia);
        Gatunek g = gatunekService.getGatunekById(form.getIdGatunek());

        for(DetaleZlecenia d : zlecenie.getDetaleZlecenia()){
            if(Objects.equals(d.getId(), idDetale)){
                d.setGatunek(g);
                d.setIlosc(form.getIlosc());
                d.setRodzaj(form.getRodzaj());
                break;
            }
        }

        zlecenieRepo.save(zlecenie);
    }

    public DetaleZlecenia getDetaleZleceniaById(Long id) {
        return  detaleZleceniaRepo.getDetaleZleceniaById(id);
    }

    public boolean usunZlecenie(Long idZlecenie) {
        Zlecenie zlecenie = zlecenieRepo.getZlecenieById(idZlecenie);
        List<Partia> partie = partiaRepo.getPartiasByZlecenie(zlecenie);

        if(partie.isEmpty()){
            zlecenieRepo.delete(zlecenie);
            return true;
        }
        else return false;
    }

    public Zlecenie getZlecenieById(Long idZlecenie) {
        return zlecenieRepo.getZlecenieById(idZlecenie);
    }

    public List<Zlecenie> getAllZlecenia() {
        return zlecenieRepo.findAll();
    }
}
