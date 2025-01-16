package bd.grzyby.service;

import bd.grzyby.model.entity.Gatunek;
import bd.grzyby.model.entity.Partia;
import bd.grzyby.model.entity.Zlecenie;
import bd.grzyby.repository.DetaleZleceniaRepo;
import bd.grzyby.repository.GatunekRepo;
import bd.grzyby.repository.PartiaRepo;
import bd.grzyby.repository.ZlecenieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatunekService {
    private final GatunekRepo gatunekRepo;
    private final PartiaRepo partiaRepo;
    private final DetaleZleceniaRepo detaleZleceniaRepo;

    public GatunekService(GatunekRepo gatunekRepo, PartiaRepo partiaRepo, DetaleZleceniaRepo detaleZleceniaRepo) {
        this.gatunekRepo = gatunekRepo;
        this.partiaRepo = partiaRepo;
        this.detaleZleceniaRepo = detaleZleceniaRepo;
    }

    public void addGatunek(Gatunek gatunek) {
        gatunekRepo.save(gatunek);
    }

    public void removeGatunek(Gatunek gatunek) {
        boolean p = partiaRepo.existsPartiaByGatunek(gatunek);
        boolean z = detaleZleceniaRepo.existsDetaleZleceniaByGatunek(gatunek);
        if(p || z) return;
        gatunekRepo.delete(gatunek);
    }

    public List<Gatunek> getAllGatunek() {
        return gatunekRepo.findAll();
    }

    public Gatunek getGatunekById(Long id) {
        return gatunekRepo.findGatunekById(id);
    }

    public Gatunek getGatunekByName(String name) {
        return gatunekRepo.findGatunekByNazwa(name);
    }
}
