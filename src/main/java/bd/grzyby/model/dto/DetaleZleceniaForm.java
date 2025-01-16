package bd.grzyby.model.dto;

import lombok.Data;

@Data
public class DetaleZleceniaForm {
    private Long idGatunek;
    private int ilosc;
    private RodzajEnum rodzaj;

    public DetaleZleceniaForm(Long idGatunek, int ilosc, RodzajEnum rodzaj) {
        this.idGatunek = idGatunek;
        this.ilosc = ilosc;
        this.rodzaj = rodzaj;
    }

    public DetaleZleceniaForm() {
    }
}
