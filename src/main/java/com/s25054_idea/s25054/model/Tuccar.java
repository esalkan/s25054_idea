package com.s25054_idea.s25054.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tuccar")
public class Tuccar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tuccarId;

    @Column(nullable = false)
    private int kullaniciId;

    @Column(nullable = false)
    private String firmaAdi;

    @Column(nullable = false)
    private String firmaYetkilisi;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String sehir;

    @Column(nullable = true)
    private String notlar;

    public int getTuccarId() {
        return tuccarId;
    }

    public void setTuccarId(int tuccarId) {
        this.tuccarId = tuccarId;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getFirmaYetkilisi() {
        return firmaYetkilisi;
    }

    public void setFirmaYetkilisi(String firmaYetkilisi) {
        this.firmaYetkilisi = firmaYetkilisi;
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
