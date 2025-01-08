package bd.grzyby.repository;

import bd.grzyby.model.entity.Pomieszczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PomieszczenieRepo extends JpaRepository<Pomieszczenie, Long> {
}
