package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Oceny_partii")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OcenaPartii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_partii")
    private Partia partia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detale")
    private DetaleOceny detale;
    @ManyToOne
    @JoinColumn(name = "id_pracownik")
    private Pracownik pracownik;
    private int ocena;
    private int etap;
    private Date data;

    public OcenaPartii(Partia partia, DetaleOceny detale, Pracownik pracownik, int ocena, int etap, Date data) {
        this.partia = partia;
        this.detale = detale;
        this.pracownik = pracownik;
        this.ocena = ocena;
        this.etap = etap;
        this.data = data;
    }
}
