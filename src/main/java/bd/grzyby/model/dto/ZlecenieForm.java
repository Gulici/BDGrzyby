package bd.grzyby.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZlecenieForm {
    private final Long idKlient;
    private List<DetaleZleceniaForm> detale;

    public ZlecenieForm(Long idKlient) {
        this.idKlient = idKlient;
        detale = new ArrayList<>();
    }
}
