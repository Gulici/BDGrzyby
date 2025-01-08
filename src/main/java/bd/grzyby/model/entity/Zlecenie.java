package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Zlecenia")
@Getter
@Setter
@ToString
public class Zlecenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_klient")
    private Klient klient;
    private Date data;

    @OneToMany(mappedBy = "zlecenie", cascade = CascadeType.ALL)
    private List<DetaleZlecenia> detaleZlecenia;

    public Zlecenie(Klient klient, Date data) {
        this.klient = klient;
        this.data = data;
        this.detaleZlecenia = new ArrayList<>();
    }

    public Zlecenie() {}

    public List<DetaleZlecenia> getDetaleZlecenia() {
        return detaleZlecenia;
    }

}
