package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "depo")
public class Depo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int depoId;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

    @Column(name = "depo_adi", nullable = false)
    private String depoAdi;

    @Column(name = "aciklama")
    private String aciklama;

    public int getDepoId() {
        return depoId;
    }

    public void setDepoId(int depoId) {
        this.depoId = depoId;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public String getDepoAdi() {
        return depoAdi;
    }

    public void setDepoAdi(String depoAdi) {
        this.depoAdi = depoAdi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
