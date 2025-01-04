package bd.grzyby.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
    private Long klientId;
    private Date data;

    public Zlecenie(Long klientId, Date data) {
        this.klientId = klientId;
        this.data = data;
    }
}
