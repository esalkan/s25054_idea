package com.s25054_idea.s25054.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kullanici")
public class Kullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kullaniciId;

    @Column(nullable = false, unique = true)
    private String eposta;

    @Column(nullable = false)
    private String sifre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role rol;

    public enum Role {
        ADMIN, TUCCAR, URETICI
    }

    public int getKullaniciId() { return kullaniciId; }
    public void setKullaniciId(int kullaniciId) { this.kullaniciId = kullaniciId; }
    public String getEposta() { return eposta; }
    public void setEposta(String eposta) { this.eposta = eposta; }
    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }
    public Role getRol() { return rol; }
    public void setRol(Role rol) { this.rol = rol; }
}
