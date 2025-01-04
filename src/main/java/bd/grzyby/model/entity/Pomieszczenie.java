package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Pomieszczenia")
@Getter
@Setter
@RequiredArgsConstructor
public class Pomieszczenie {
    @Id
    @Column(nullable = false)
    private Long id;

    public Pomieszczenie(Long id) {
        this.id = id;
    }
}
