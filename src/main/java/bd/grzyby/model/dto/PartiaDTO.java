package bd.grzyby.model.dto;

import bd.grzyby.model.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PartiaDTO {
    private Zlecenie zlecenie;
    private Gatunek gatunek;
    private Pomieszczenie pomieszczenie;
    private int etap;
    private Date data;
    private List<OcenaPartii> ocenyPartii;
    private Pracownik pracownik;

    public PartiaDTO(Zlecenie zlecenie, Gatunek gatunek, Pomieszczenie pomieszczenie, int etap, Date data, Pracownik pracownik) {
        this.zlecenie = zlecenie;
        this.gatunek = gatunek;
        this.pomieszczenie = pomieszczenie;
        this.etap = etap;
        this.data = data;
        ocenyPartii = new ArrayList<OcenaPartii>();
        this.pracownik = pracownik;
    }
}
