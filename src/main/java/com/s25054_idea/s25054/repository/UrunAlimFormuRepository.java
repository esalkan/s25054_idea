package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UrunAlimFormuRepository extends JpaRepository<UrunAlimFormu, Integer> {
    List<UrunAlimFormu> findByGonderenTuccar_TuccarId(int tuccarId);
    List<UrunAlimFormu> findByGonderilecekDepo_DepoId(int depoId);
    List<UrunAlimFormu> findByUretici_UreticiId(int ureticiId);

    @Query("SELECT COUNT(u) FROM UrunAlimFormu u")
    long countAllForms();

    @Query("SELECT COUNT(u) FROM UrunAlimFormu u WHERE u.durum = :durum")
    long countByDurum(@Param("durum") FormDurumu durum);
}
