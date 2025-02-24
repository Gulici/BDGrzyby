package bd.grzyby.service;

import bd.grzyby.model.dto.PracownikForm;
import bd.grzyby.model.entity.OcenaPartii;
import bd.grzyby.model.entity.Pracownik;
import bd.grzyby.model.entity.Uprawnienie;
import bd.grzyby.repository.OcenaPartiiRepo;
import bd.grzyby.repository.PracownikRepo;
import bd.grzyby.repository.UprawnienieRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PracownikService {
    PracownikRepo pracownikRepo;
    UprawnienieRepo uprawnienieRepo;
    OcenaPartiiRepo ocenaPartiiRepo;
    PasswordEncoder passwordEncoder;

    public PracownikService(PracownikRepo pracownikRepo, UprawnienieRepo uprawnienieRepo,OcenaPartiiRepo ocenaPartiiRepo) {
        this.pracownikRepo = pracownikRepo;
        this.uprawnienieRepo = uprawnienieRepo;
        this.ocenaPartiiRepo = ocenaPartiiRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Pracownik getPracownik(Long id) {
        return pracownikRepo.getPracownikById(id);
    }

    public Pracownik getPracownik(String email) {
        return pracownikRepo.getPracownikByEmail(email);
    }

    public List<Pracownik> getPracownicy() {
        return pracownikRepo.findAll();
    }

    public void nadajUprawnieniaKierownika(Long idPracownik) {
        Pracownik pracownik = getPracownik(idPracownik);
        Uprawnienie uprawnienie = uprawnienieRepo.getUprawnienieById(2L);
        pracownik.getUprawnienia().add(uprawnienie);
        pracownikRepo.save(pracownik);
    }

    public boolean dodajPracownik(PracownikForm form) {
        List<Pracownik> list = pracownikRepo.getPracowniksByEmail(form.getEmail());
        if(list.isEmpty()) {
            Pracownik pracownik = new Pracownik(form.getImie(), form.getNazwisko(),
                    form.getEmail(), passwordEncoder.encode(form.getPassword()));
            Uprawnienie u = uprawnienieRepo.getUprawnienieById(1L);
            pracownik.getUprawnienia().add(u);
            pracownikRepo.save(pracownik);
            return true;
        }
        else return false;
    }

    public boolean usunUprawnieniaKierownika(Long idPracownik) {
        Pracownik pracownik = getPracownik(idPracownik);
        Uprawnienie uprawnienie = uprawnienieRepo.getUprawnienieById(2L);
        Set<Uprawnienie> uprawnienieSet = pracownik.getUprawnienia();

        if (uprawnienieSet.contains(uprawnienie) && uprawnienieSet.size() == 2){
            uprawnienieSet.remove(uprawnienie);
            pracownikRepo.save(pracownik);
            return true;
        }
        else return false;
    }

    public void nadajUprawnieniaManagera(Long idPracownik) {
        Pracownik pracownik = getPracownik(idPracownik);
        Uprawnienie kierownik = uprawnienieRepo.getUprawnienieById(2L);
        Uprawnienie manager = uprawnienieRepo.getUprawnienieById(3L);
        pracownik.getUprawnienia().addAll(List.of(kierownik, manager));
        pracownikRepo.save(pracownik);
    }

    public boolean usunUprawnieniaManagera(Long idPracownik) {
        Pracownik pracownik = getPracownik(idPracownik);
        Uprawnienie uprawnienie = uprawnienieRepo.getUprawnienieById(3L);
        Set<Uprawnienie> uprawnienieSet = pracownik.getUprawnienia();

        if (uprawnienieSet.contains(uprawnienie)){
            uprawnienieSet.remove(uprawnienie);
            pracownikRepo.save(pracownik);
            return true;
        }
        else return false;
    }

    public boolean usunPracownik(Long idPracownik) {
        Pracownik pracownik = getPracownik(idPracownik);
        List<OcenaPartii> list = ocenaPartiiRepo.getOcenaPartiisByPracownik(pracownik);

        // nie mozna usunac pracownika ktory ma przypisane oceny partii
        if(list.isEmpty()) {
            pracownikRepo.delete(pracownik);
            return true;
        }
        else return false;
    }

    public boolean updatePracownik(Pracownik updatedPracownik) {
        Pracownik existingPracownik = pracownikRepo.findById(updatedPracownik.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee ID"));
        existingPracownik.setImie(updatedPracownik.getImie());
        existingPracownik.setNazwisko(updatedPracownik.getNazwisko());
        existingPracownik.setEmail(updatedPracownik.getEmail());
        // Optionally handle password update here
        pracownikRepo.save(existingPracownik);
        return true;
    }
}

