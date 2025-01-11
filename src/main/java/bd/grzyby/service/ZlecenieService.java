package bd.grzyby.service;

import bd.grzyby.model.entity.Zlecenie;
import bd.grzyby.repository.ZlecenieRepo;
import org.springframework.stereotype.Service;

@Service
public class ZlecenieService {


    private final ZlecenieRepo zlecenieRepo;

    public ZlecenieService(ZlecenieRepo zlecenieRepo) {
        this.zlecenieRepo = zlecenieRepo;
    }

    public void dodajZlecenie(Zlecenie zlecenie) {}

    public void edytujZlecenie(Zlecenie zlecenie) {}

    public void usunZlecenie(Zlecenie zlecenie) {}

    public Zlecenie getZlecenieById(Long idZlecenie) {
        return zlecenieRepo.getZlecenieById(idZlecenie);
    }
}
