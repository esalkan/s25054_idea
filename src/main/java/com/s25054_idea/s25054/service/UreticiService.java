package com.s25054_idea.s25054.service;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UreticiService {

    @Autowired
    private UreticiRepository ureticiRepository;

    public List<Uretici> findByTuccarId(int tuccarId) {
        return ureticiRepository.findByTuccarId(tuccarId);
    }

    public Optional<Uretici> findById(int ureticiId) {
        return ureticiRepository.findById(ureticiId);
    }

    public void save(Uretici uretici) {
        ureticiRepository.save(uretici);
    }

    public Optional<Uretici> findByKullaniciId(int kullaniciId) {
        return ureticiRepository.findByKullaniciId(kullaniciId);
    }
}
