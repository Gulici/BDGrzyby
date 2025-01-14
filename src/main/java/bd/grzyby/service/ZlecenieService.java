package bd.grzyby.service;

import bd.grzyby.model.dto.DetaleZleceniaForm;
import bd.grzyby.model.dto.ZlecenieForm;
import bd.grzyby.model.entity.*;
import bd.grzyby.repository.KlientRepo;
import bd.grzyby.repository.PartiaRepo;
import bd.grzyby.repository.ZlecenieRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ZlecenieService {

    private final ZlecenieRepo zlecenieRepo;
    private final KlientRepo klientRepo;
    private final GatunekService gatunekService;
    private final PartiaRepo partiaRepo;

    public ZlecenieService(ZlecenieRepo zlecenieRepo, KlientRepo klientRepo, GatunekService gatunekService, PartiaRepo partiaRepo) {
        this.zlecenieRepo = zlecenieRepo;
        this.klientRepo = klientRepo;
        this.gatunekService = gatunekService;
        this.partiaRepo = partiaRepo;
    }

    public void dodajZlecenie(ZlecenieForm form) {
        Klient klient = klientRepo.getKlientById(form.getIdKlient());
        Date data = new Date();
        Zlecenie zlecenie = new Zlecenie(klient,data);
        List<DetaleZlecenia> detale = zlecenie.getDetaleZlecenia();
        List<DetaleZleceniaForm> detaleForm = form.getDetale();

        for(DetaleZleceniaForm d : detaleForm){
            Gatunek g = gatunekService.getGatunekById(d.getIdGatunek());
            DetaleZlecenia detaleZlecenia = new DetaleZlecenia(g,zlecenie,d.getIlosc(),d.getRodzaj());
            detale.add(detaleZlecenia);
        }
        zlecenieRepo.save(zlecenie);
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
