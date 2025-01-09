package bd.grzyby.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Partie")
@ToString
public class Partia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_zlecenia")
    private Zlecenie zlecenie;
    @ManyToOne
    @JoinColumn(name = "id_gatunek")
    private Gatunek gatunek;
    @ManyToOne
    @JoinColumn(name = "id_pom")
    private Pomieszczenie pomieszczenie;
    private int etap;
    private Date data;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partia")
    private List<OcenaPartii> ocenyPartii;

    public Partia(Zlecenie zlecenie, Gatunek gatunek, Pomieszczenie pomieszczenie, int etap, Date data) {
        this.zlecenie = zlecenie;
        this.gatunek = gatunek;
        this.pomieszczenie = pomieszczenie;
        this.etap = etap;
        this.data = data;
        this.ocenyPartii = new ArrayList<>();
    }

    public Partia() {
        this.ocenyPartii = new ArrayList<>();
    }

    public Zlecenie getZlecenie() {
        return zlecenie;
    }

    public void setZlecenie(Zlecenie zlecenie) {
        this.zlecenie = zlecenie;
    }

    public Gatunek getGatunek() {
        return gatunek;
    }

    public void setGatunek(Gatunek gatunek) {
        this.gatunek = gatunek;
    }

    public Pomieszczenie getPomieszczenie() {
        return pomieszczenie;
    }

    public void setPomieszczenie(Pomieszczenie pomieszczenie) {
        this.pomieszczenie = pomieszczenie;
    }

    public int getEtap() {
        return etap;
    }

    public void setEtap(int etap) {
        this.etap = etap;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<OcenaPartii> getOcenyPartii() {
        return ocenyPartii;
    }

    public void setOcenyPartii(List<OcenaPartii> ocenyPartii) {
        this.ocenyPartii = ocenyPartii;
    }
}
