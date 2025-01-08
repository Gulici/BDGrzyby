package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Pomieszczenia")
@Getter
@Setter
@RequiredArgsConstructor
public class Pomieszczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @ManyToMany(mappedBy = "pomieszczenia", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Pracownik> pracownicy;

    public Pomieszczenie(Long id) {
        this.id = id;
    }
}
