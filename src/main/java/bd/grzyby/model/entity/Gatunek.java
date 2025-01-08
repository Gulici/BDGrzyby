package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "Gatunki")
public class Gatunek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nazwa;
    private Double cena;

    public Gatunek(String nazwa, Double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public Gatunek() {}
}
