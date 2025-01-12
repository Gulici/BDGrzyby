package bd.grzyby.repository;

import bd.grzyby.model.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownikRepo extends JpaRepository<Pracownik, Long> {
    Pracownik getPracownikById(Long l);
    List<Pracownik> getPracowniksByEmail(String email);
    Pracownik getPracownikByEmail(String email);
}
