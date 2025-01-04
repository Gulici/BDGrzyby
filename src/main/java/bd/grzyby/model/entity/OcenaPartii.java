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
@Table(name = "Oceny_partii")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OcenaPartii {
    @Id
    @Column(nullable = false)
    private Long id;
    private Long partiaId;
    private Long detaleId;
    private Long pracownikId;
    private int ocena;
    private int etap;
    private Date data;

    public OcenaPartii(Long partiaId, Long detaleId, Long pracownikId, int ocena, int etap, Date data) {
        this.partiaId = partiaId;
        this.detaleId = detaleId;
        this.pracownikId = pracownikId;
        this.ocena = ocena;
        this.etap = etap;
        this.data = data;
    }
}
