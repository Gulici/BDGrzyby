package bd.grzyby.model.dto;

import lombok.Data;

@Data
public class DetaleZleceniaForm {
    private Long idGatunek;
    private int ilosc;
    private int rodzaj;

    public DetaleZleceniaForm(Long idGatunek, int ilosc, int rodzaj) {
        this.idGatunek = idGatunek;
        this.ilosc = ilosc;
        this.rodzaj = rodzaj;
    }
}
