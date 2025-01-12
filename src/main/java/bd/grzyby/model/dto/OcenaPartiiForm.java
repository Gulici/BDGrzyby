package bd.grzyby.model.dto;

import lombok.Data;

@Data
public class OcenaPartiiForm {
    Long idPartia;
    Long idPracownik;
    int ocena;
    String opis;

    public OcenaPartiiForm(Long idPartia, Long idPracownik, int ocena, String opis) {
        this.idPartia = idPartia;
        this.idPracownik = idPracownik;
        this.ocena = ocena;
        this.opis = opis;
    }
}
