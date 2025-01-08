package bd.grzyby.repository;

import bd.grzyby.model.entity.DetaleZlecenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetaleZleceniaRepo extends JpaRepository<DetaleZlecenia, Long> {
}
