package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Partie")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Partia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_zlecenia")
    private Zlecenie zlecenie;
    @ManyToOne
    @JoinColumn(name = "id_gatunek")
    private Gatunek gatunek;
    @ManyToOne
    @JoinColumn(name = "id_pom")
    private Pomieszczenie pomieszczenie;
    private int etap;
    private Date data;

    public Partia(Zlecenie zlecenie, Gatunek gatunek, Pomieszczenie pomieszczenie, int etap, Date data) {
        this.zlecenie = zlecenie;
        this.gatunek = gatunek;
        this.pomieszczenie = pomieszczenie;
        this.etap = etap;
        this.data = data;
    }
}
