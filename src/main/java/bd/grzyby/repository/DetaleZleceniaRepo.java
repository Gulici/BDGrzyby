package bd.grzyby.repository;

import bd.grzyby.model.entity.DetaleZlecenia;
import bd.grzyby.model.entity.Gatunek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetaleZleceniaRepo extends JpaRepository<DetaleZlecenia, Long> {
    DetaleZlecenia getDetaleZleceniaById(Long id);

    void deleteById(Long id);

    boolean existsDetaleZleceniaByGatunek(Gatunek gatunek);
}
