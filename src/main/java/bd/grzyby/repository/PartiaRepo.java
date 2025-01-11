package bd.grzyby.repository;

import bd.grzyby.model.entity.Partia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartiaRepo extends JpaRepository<Partia, Long> {
    Partia findPartiaById(Long id);
}
