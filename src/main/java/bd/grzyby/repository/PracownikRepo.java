package bd.grzyby.repository;

import bd.grzyby.model.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownikRepo extends JpaRepository<Pracownik, Long> {
    Pracownik getPracownikById(Long l);
}
