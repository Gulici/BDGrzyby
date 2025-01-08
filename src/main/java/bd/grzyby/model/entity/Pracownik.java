package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "Pracownicy")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Pracownik {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String imie;
    @Column(nullable = false)
    private String nazwisko;
    @Column(nullable = false, unique = true)
    private String email;
    @ToString.Exclude
    @Column(nullable = false, unique = true)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Pomieszczenia_Pracownicy",
            joinColumns = @JoinColumn(name = "id_pracownik"),
            inverseJoinColumns = @JoinColumn(name = "id_pom")
    )
    private Set<Pomieszczenie> pomieszczenia;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "pracownik_uprawnienie",
            joinColumns = @JoinColumn(name = "id_pracownik"),
            inverseJoinColumns = @JoinColumn(name = "id_pomieszczenie")
    )
    private Set<Uprawnienie> uprawnienia;

    public Pracownik(String imie, String nazwisko, String email, String password) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.password = password;
    }
}
