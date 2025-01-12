package bd.grzyby.model.dto;

import lombok.Data;

@Data
public class CreatePartiaForm {
    private Long idZlecenie;
    private Long idGatunek;
    private Long idPomieszczenie;
    private Long idPracownik;
    private int ocena;

    public CreatePartiaForm(Long idZlecenie, Long idGatunek, Long idPomieszczenie, Long idPracownik, int ocena) {
        this.idZlecenie = idZlecenie;
        this.idGatunek = idGatunek;
        this.idPomieszczenie = idPomieszczenie;
        this.idPracownik = idPracownik;
        this.ocena = ocena;
    }
}
