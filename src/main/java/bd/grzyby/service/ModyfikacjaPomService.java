package bd.grzyby.service;

import bd.grzyby.model.entity.ModyfikacjaPom;
import bd.grzyby.repository.ModyfikacjaPomRepo;
import org.springframework.stereotype.Service;

@Service
public class ModyfikacjaPomService {

    private final ModyfikacjaPomRepo repo;

    public ModyfikacjaPomService(ModyfikacjaPomRepo repo) {
        this.repo = repo;
    }

    ModyfikacjaPom getModyfikacja(Long id) {
        return repo.getModyfikacjaPomById(id);
    }

    public void dodajMod(ModyfikacjaPom modyfikacja) {
        repo.save(modyfikacja);
    }

    public void usunModyfikacje(Long idPartia) {
        repo.deleteAll(repo.findAllByPartiaId(idPartia));
    }
}
