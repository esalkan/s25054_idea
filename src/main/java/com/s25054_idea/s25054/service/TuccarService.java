package com.s25054_idea.s25054.service;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TuccarService {

    @Autowired
    private TuccarRepository tuccarRepository;

    public Optional<Tuccar> findByKullaniciId(int kullaniciId) {
        return Optional.ofNullable(tuccarRepository.findByKullaniciId(kullaniciId).orElse(null));
    }
}
