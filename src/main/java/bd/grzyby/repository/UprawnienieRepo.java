package bd.grzyby.repository;

import bd.grzyby.model.entity.Uprawnienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UprawnienieRepo extends JpaRepository<Uprawnienie, Long> {
    Uprawnienie findByNazwa(String pracownik);

    Uprawnienie getUprawnienieById(Long l);
}
