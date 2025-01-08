package bd.grzyby.repository;

import bd.grzyby.model.entity.ModyfikacjaPom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModyfikacjaPomRepo extends JpaRepository<ModyfikacjaPom, Long> {
}
