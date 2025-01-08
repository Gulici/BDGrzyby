package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Zlecenia")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Zlecenie {
    @Id
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_klient")
    private Klient klient;
    private Date data;

    @OneToMany(mappedBy = "zlecenie")
    private List<DetaleZlecenia> detaleZlecenia;

    public Zlecenie(Klient klient, Date data) {
        this.klient = klient;
        this.data = data;
    }
}
