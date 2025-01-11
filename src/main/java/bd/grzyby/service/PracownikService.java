package bd.grzyby.service;

import bd.grzyby.model.entity.Pracownik;
import bd.grzyby.repository.PracownikRepo;
import org.springframework.stereotype.Service;

@Service
public class PracownikService {
    PracownikRepo pracownikRepo;

    public PracownikService(PracownikRepo pracownikRepo) {
        this.pracownikRepo = pracownikRepo;
    }

    public Pracownik getPracownik(Long id) {
        return pracownikRepo.getPracownikById(id);
    }
}

