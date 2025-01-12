package bd.grzyby.model.dto;

import lombok.Data;

@Data
public class PracownikForm {
    private String imie;
    private String nazwisko;
    private String email;
    private String password;

    public PracownikForm(String imie, String nazwisko, String email, String password) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.password = password;
    }
}
