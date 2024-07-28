package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "uretici")
public class Uretici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ureticiId;

    @Column(nullable = false)
    private int kullaniciId;

    @Column(nullable = false)
    private String adiSoyadi;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String sehir;

    @Column(nullable = true)
    private String notlar;

    public int getUreticiId() {
        return ureticiId;
    }

    public void setUreticiId(int ureticiId) {
        this.ureticiId = ureticiId;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getNotlar() {
        return notlar;
    }

    public void setNotlar(String notlar) {
        this.notlar = notlar;
    }
}
