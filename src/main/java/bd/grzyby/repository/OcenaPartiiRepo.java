package bd.grzyby.repository;

import bd.grzyby.model.entity.OcenaPartii;
import bd.grzyby.model.entity.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcenaPartiiRepo extends JpaRepository<OcenaPartii, Long> {
    List<OcenaPartii> getOcenaPartiisByPracownik(Pracownik pracownik);
    @Query(value = "select * from oceny_partii where id_pracownik = :pracownikid", nativeQuery = true)
    List<OcenaPartii> getListaOcenaPartiiByPracownik(@Param("pracownikid") Long pracownikid);
}
