package bd.grzyby.repository;

import bd.grzyby.model.entity.DetaleOceny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetaleOcenyRepo extends JpaRepository<DetaleOceny, Long> {
}
