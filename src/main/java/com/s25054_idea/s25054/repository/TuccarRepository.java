package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.Tuccar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TuccarRepository extends JpaRepository<Tuccar, Integer> {
    Optional<Tuccar> findByKullaniciId(int kullaniciId);
}
