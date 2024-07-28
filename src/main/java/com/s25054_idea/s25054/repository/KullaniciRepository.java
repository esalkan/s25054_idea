package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
    Optional<Kullanici> findByEposta(String eposta);
    long countByRol(Kullanici.Role rol);
}
