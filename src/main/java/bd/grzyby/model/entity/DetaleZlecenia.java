package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "Detale_zlec")
public class DetaleZlecenia {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idGatunek;
    private Long idZlecenie;
    private int ilosc;
    private int rodzaj;

    public DetaleZlecenia(Long idGatunek, Long idZlecenie, int ilosc, int rodzaj) {
        this.idGatunek = idGatunek;
        this.idZlecenie = idZlecenie;
        this.ilosc = ilosc;
        this.rodzaj = rodzaj;
    }
}
