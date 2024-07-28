package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.Uretici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UreticiRepository extends JpaRepository<Uretici, Integer> {

    @Query("SELECT u FROM Uretici u JOIN UreticiTuccar ut ON u.ureticiId = ut.ureticiId WHERE ut.tuccarId = :tuccarId")
    List<Uretici> findByTuccarId(@Param("tuccarId") int tuccarId);
    Optional<Uretici> findByKullaniciId(int kullaniciId);
}
