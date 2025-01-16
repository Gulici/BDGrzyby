package bd.grzyby.service;

import bd.grzyby.model.entity.Klient;
import bd.grzyby.model.entity.Zlecenie;
import bd.grzyby.repository.KlientRepo;
import bd.grzyby.repository.ZlecenieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientService {

    private final KlientRepo klientRepo;
    private final ZlecenieRepo zlecenieRepo;

    public KlientService(KlientRepo klientRepo, ZlecenieRepo zlecenieRepo) {
        this.klientRepo = klientRepo;
        this.zlecenieRepo = zlecenieRepo;
    }

    // Retrieve all clients
    public List<Klient> findAllKlienci() {
        return klientRepo.findAll();
    }

    // Find a specific client by ID
    public Klient findKlientById(Long id) {
        return klientRepo.findById(id).orElse(null);
    }

    // Add a new client
    public void addKlient(Klient klient) {
        klientRepo.save(klient);
    }

    // Update an existing client
    public void updateKlient(Long id, Klient updatedKlient) {
        Klient existingKlient = findKlientById(id);
        if (existingKlient != null) {
            existingKlient.setFirma(updatedKlient.getFirma());
            existingKlient.setImie(updatedKlient.getImie());
            existingKlient.setNazwisko(updatedKlient.getNazwisko());
            existingKlient.setEmail(updatedKlient.getEmail());
            klientRepo.save(existingKlient);
        }
    }

    // Delete a client by ID
    public void deleteKlient(Long id) {
        Klient k  = klientRepo.getKlientById(id);
        if(!zlecenieRepo.existsZlecenieByKlient(k)) {
            klientRepo.deleteById(id);
        }
    }
}
