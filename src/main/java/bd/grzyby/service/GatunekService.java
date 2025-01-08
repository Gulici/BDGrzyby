package bd.grzyby.service;

import bd.grzyby.model.entity.Gatunek;
import bd.grzyby.repository.GatunekRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatunekService {
    private final GatunekRepo gatunekRepo;

    public GatunekService(GatunekRepo gatunekRepo) {
        this.gatunekRepo = gatunekRepo;
    }

    public void addGatunek(Gatunek gatunek) {
        gatunekRepo.save(gatunek);
    }

    public void removeGatunek(Gatunek gatunek) {
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
