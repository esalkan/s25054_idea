package com.s25054_idea.s25054.service;

import com.s25054_idea.s25054.model.Depo;
import com.s25054_idea.s25054.repository.DepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepoService {

    @Autowired
    private DepoRepository depoRepository;

    public List<Depo> findByKullaniciId(int kullaniciId) {
        return depoRepository.findByKullanici_KullaniciId(kullaniciId);
    }

    public Optional<Depo> findById(int depoId) {
        return depoRepository.findById(depoId);
    }

    public void save(Depo depo) {
        depoRepository.save(depo);
    }
}
