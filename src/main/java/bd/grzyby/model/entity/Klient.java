package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "Klienci")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String firma;
    private String imie;
    private String nazwisko;
    private String email;

    public Klient(String firma, String imie, String nazwisko, String email) {
        this.firma = firma;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
    }
}
