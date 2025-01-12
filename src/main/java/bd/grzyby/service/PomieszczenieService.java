package bd.grzyby.service;

import bd.grzyby.model.entity.Pomieszczenie;
import bd.grzyby.repository.PomieszczenieRepo;
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
}
