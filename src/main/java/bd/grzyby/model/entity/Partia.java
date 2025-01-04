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
    @Column(name = "id_zlecenia")
    private Long zlecenieId;
    @Column(name = "id_gatunku")
    private Long gatunekId;
    @Column(name = "id_pom")
    private Long pomieszczenieId;
    private int etap;
    private Date data;

    public Partia(Long zlecenieId, Long gatunekId, Long pomieszczenieId, int etap, Date data) {
        this.zlecenieId = zlecenieId;
        this.gatunekId = gatunekId;
        this.pomieszczenieId = pomieszczenieId;
        this.etap = etap;
        this.data = data;
    }
}
