package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    @ManyToOne
    @JoinColumn(name = "id_gatunek")
    private Gatunek gatunek;
    @ManyToOne
    @JoinColumn(name = "id_zlecenie")
    private Zlecenie zlecenie;
    private int ilosc;
    private int rodzaj;

    public DetaleZlecenia(Gatunek gatunek, Zlecenie zlecenie, int ilosc, int rodzaj) {
        this.gatunek = gatunek;
        this.zlecenie = zlecenie;
        this.ilosc = ilosc;
        this.rodzaj = rodzaj;
    }
}
