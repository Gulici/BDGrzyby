package bd.grzyby.repository;

import bd.grzyby.model.entity.OcenaPartii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OcenaPartiiRepo extends JpaRepository<OcenaPartii, Long> {
}
