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
    @Column(name = "id_partii")
    private Long idPartii;
    @Column(name = "pom_p")
    private int idPomPocz;
    @Column(name = "pom_d")
    private int idPomDocel;
    @Column(name = "data")
    private Date data;
    @Column(name = "id_pracownik")
    private Long idPracownik;
    @Column(name = "etap")
    private int etap;

    public ModyfikacjaPom(Long idPartii, int idPomPocz, int idPomDocel, Date data, Long idPracownik, int etap) {
        this.idPartii = idPartii;
        this.idPomPocz = idPomPocz;
        this.idPomDocel = idPomDocel;
        this.data = data;
        this.idPracownik = idPracownik;
        this.etap = etap;
    }
}
