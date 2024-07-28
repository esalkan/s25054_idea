package com.s25054_idea.s25054.repository;

import com.s25054_idea.s25054.model.FormDurumlari;
import com.s25054_idea.s25054.model.UrunAlimFormu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormDurumlariRepository extends JpaRepository<FormDurumlari, Long> {
    Optional<FormDurumlari> findByForm(UrunAlimFormu form);
}
