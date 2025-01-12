package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Uprawnienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nazwa;

    @ManyToMany(mappedBy = "uprawnienia",
    fetch = FetchType.EAGER,
    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Pracownik> pracownicy;

    public Uprawnienie(String nazwa) {
        this.nazwa = nazwa;
    }

    public Uprawnienie() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uprawnienie that = (Uprawnienie) o;
        return Objects.equals(nazwa, that.nazwa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nazwa);
    }
}
