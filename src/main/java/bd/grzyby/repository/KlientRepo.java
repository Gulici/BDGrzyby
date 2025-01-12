package bd.grzyby.repository;

import bd.grzyby.model.entity.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepo extends JpaRepository<Klient, Long> {
    Klient getKlientByFirma(String firma);

    Klient getKlientById(Long idKlient);
}
