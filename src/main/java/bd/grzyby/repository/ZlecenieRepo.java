package bd.grzyby.repository;

import bd.grzyby.model.entity.Zlecenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZlecenieRepo extends JpaRepository<Zlecenie, Long> {
}
