package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.Depo;
import com.s25054_idea.s25054.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepoRepository extends JpaRepository<Depo, Integer> {
    List<Depo> findByKullanici_KullaniciId(int kullaniciId);
    long countByKullanici_Rol(Kullanici.Role rol);
}
