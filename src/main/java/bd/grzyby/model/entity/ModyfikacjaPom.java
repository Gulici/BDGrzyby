package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Modyfikacje_pom")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class ModyfikacjaPom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_partii")
    private Partia partia;
    @Column(name = "pom_p")
    private int idPomPocz;
    @Column(name = "pom_d")
    private int idPomDocel;
    @Column(name = "data")
    private Date data;
    @ManyToOne
    @JoinColumn(name = "id_pracownik")
    private Pracownik pracownik;
    @Column(name = "etap")
    private int etap;

    public ModyfikacjaPom(Partia partia, int idPomPocz, int idPomDocel, Date data, Pracownik pracownik, int etap) {
        this.partia = partia;
        this.idPomPocz = idPomPocz;
        this.idPomDocel = idPomDocel;
        this.data = data;
        this.pracownik = pracownik;
        this.etap = etap;
    }
}
