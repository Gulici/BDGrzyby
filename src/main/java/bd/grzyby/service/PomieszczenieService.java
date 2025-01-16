package bd.grzyby.service;

import bd.grzyby.model.entity.Pomieszczenie;
import bd.grzyby.repository.PomieszczenieRepo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PomieszczenieService {
    private final PomieszczenieRepo pomieszczenieRepo;

    public PomieszczenieService(PomieszczenieRepo pomieszczenieRepo) {
        this.pomieszczenieRepo = pomieszczenieRepo;
    }

    public Pomieszczenie getPomById(Long idPomieszczenie) {
        return pomieszczenieRepo.getPomieszczenieById(idPomieszczenie);
    }

    public List<Pomieszczenie> getAllPomieszczenie() {
        return pomieszczenieRepo.findAll();
    }
}
