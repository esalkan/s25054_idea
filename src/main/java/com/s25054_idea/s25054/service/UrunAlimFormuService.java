package com.s25054_idea.s25054.service;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UrunAlimFormuService {

    @Autowired
    private UrunAlimFormuRepository urunAlimFormuRepository;

    @Autowired
    private FormDurumlariRepository formDurumlariRepository;

    public List<UrunAlimFormu> findByTuccarId(int tuccarId) {
        return urunAlimFormuRepository.findByGonderenTuccar_TuccarId(tuccarId);
    }

    public List<UrunAlimFormu> findByDepoId(int depoId) {
        return urunAlimFormuRepository.findByGonderilecekDepo_DepoId(depoId);
    }

    public List<UrunAlimFormu> findByUreticiId(int ureticiId) {
        return urunAlimFormuRepository.findByUretici_UreticiId(ureticiId);
    }

    public Optional<UrunAlimFormu> findById(int formId) {
        return urunAlimFormuRepository.findById(formId);
    }

    @Transactional
    public void save(UrunAlimFormu urunAlimFormu) {
        UrunAlimFormu savedForm = urunAlimFormuRepository.save(urunAlimFormu);

        FormDurumlari formDurumlari = new FormDurumlari();
        formDurumlari.setForm(savedForm);
        formDurumlari.setDurum(FormDurumu.ONAY_BEKLIYOR);
        formDurumlariRepository.save(formDurumlari);
    }

    @Transactional
    public void updateFormDurumu(UrunAlimFormu urunAlimFormu, FormDurumu yeniDurum) {
        urunAlimFormu.setDurum(yeniDurum);
        urunAlimFormuRepository.save(urunAlimFormu);

        FormDurumlari formDurumlari = formDurumlariRepository.findByForm(urunAlimFormu)
                .orElseThrow(() -> new IllegalArgumentException("Hatalı Id:" + urunAlimFormu.getFormId()));
        formDurumlari.setDurum(yeniDurum);
        formDurumlariRepository.save(formDurumlari);
    }

    public long countAllForms() {
        return urunAlimFormuRepository.countAllForms();
    }

    public long countByDurum(FormDurumu durum) {
        return urunAlimFormuRepository.countByDurum(durum);
    }

    public List<UrunAlimFormu> findAll() {
        return urunAlimFormuRepository.findAll();
    }
}
