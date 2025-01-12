package bd.grzyby.repository;

import bd.grzyby.model.entity.Partia;
import bd.grzyby.model.entity.Zlecenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartiaRepo extends JpaRepository<Partia, Long> {
    Partia findPartiaById(Long id);

    List<Partia> getPartiasByZlecenie(Zlecenie zlecenie);
}
