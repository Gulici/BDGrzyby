package bd.grzyby.repository;

import bd.grzyby.model.entity.Gatunek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatunekRepo extends JpaRepository<Gatunek, Long> {
    Gatunek findGatunekById(Long id);

    Gatunek findGatunekByNazwa(String name);
}
