package bd.grzyby.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZlecenieForm {
    private Long idKlient;
    public ZlecenieForm(Long idKlient) {
        this.idKlient = idKlient;
    }

    public ZlecenieForm() {
    }
}
