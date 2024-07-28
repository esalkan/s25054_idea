package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "urun_alim_formu")
public class UrunAlimFormu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int formId;

    @ManyToOne
    @JoinColumn(name = "gonderen_tuccar_id")
    private Tuccar gonderenTuccar;

    @ManyToOne
    @JoinColumn(name = "gonderilecek_depo_id")
    private Depo gonderilecekDepo;

    @ManyToOne
    @JoinColumn(name = "uretici_id")
    private Uretici uretici;

    private String urunCinsi;
    private double urunMiktari;
    private String urunBirimi;
    private double teklifFiyati;
    private String aciklama;

    @Convert(converter = FormDurumu.FormDurumuConverter.class)
    private FormDurumu durum;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public Tuccar getGonderenTuccar() {
        return gonderenTuccar;
    }

    public void setGonderenTuccar(Tuccar gonderenTuccar) {
        this.gonderenTuccar = gonderenTuccar;
    }

    public Depo getGonderilecekDepo() {
        return gonderilecekDepo;
    }

    public void setGonderilecekDepo(Depo gonderilecekDepo) {
        this.gonderilecekDepo = gonderilecekDepo;
    }

    public Uretici getUretici() {
        return uretici;
    }

    public void setUretici(Uretici uretici) {
        this.uretici = uretici;
    }

    public String getUrunCinsi() {
        return urunCinsi;
    }

    public void setUrunCinsi(String urunCinsi) {
        this.urunCinsi = urunCinsi;
    }

    public double getUrunMiktari() {
        return urunMiktari;
    }

    public void setUrunMiktari(double urunMiktari) {
        this.urunMiktari = urunMiktari;
    }

    public String getUrunBirimi() {
        return urunBirimi;
    }

    public void setUrunBirimi(String urunBirimi) {
        this.urunBirimi = urunBirimi;
    }

    public double getTeklifFiyati() {
        return teklifFiyati;
    }

    public void setTeklifFiyati(double teklifFiyati) {
        this.teklifFiyati = teklifFiyati;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public FormDurumu getDurum() {
        return durum;
    }

    public void setDurum(FormDurumu durum) {
        this.durum = durum;
    }
}
