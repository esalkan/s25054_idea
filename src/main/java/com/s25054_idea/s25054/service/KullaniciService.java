package com.s25054_idea.s25054.service;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KullaniciService implements UserDetailsService {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private DepoRepository depoRepository;

    @Autowired
    private UreticiRepository ureticiRepository;

    @Autowired
    private TuccarRepository tuccarRepository;

    @Autowired
    private UreticiTuccarRepository ureticiTuccarRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String eposta) throws UsernameNotFoundException {
        Kullanici kullanici = kullaniciRepository.findByEposta(eposta)
                .orElseThrow(() -> new UsernameNotFoundException("Eposta ile kullanıcı bulunamadı " + eposta));
        return new org.springframework.security.core.userdetails.User(kullanici.getEposta(), kullanici.getSifre(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + kullanici.getRol())));
    }

    public Kullanici findByEposta(String eposta) {
        return kullaniciRepository.findByEposta(eposta)
                .orElseThrow(() -> new UsernameNotFoundException("Eposta ile kullanıcı bulunamadı: " + eposta));
    }

    public int findTuccarIdByEposta(String eposta) {
        Kullanici kullanici = findByEposta(eposta);
        Tuccar tuccar = tuccarRepository.findByKullaniciId(kullanici.getKullaniciId())
                .orElseThrow(() -> new UsernameNotFoundException("kullaniciId ie Tüccar bulunamadı : " + kullanici.getKullaniciId()));
        return tuccar.getTuccarId();
    }

    public long getTuccarCount() {
        return kullaniciRepository.countByRol(Kullanici.Role.TUCCAR);
    }

    public long getUreticiCount() {
        return kullaniciRepository.countByRol(Kullanici.Role.URETICI);
    }

    public long getTuccarDepoCount() {
        return depoRepository.countByKullanici_Rol(Kullanici.Role.TUCCAR);
    }

    public long getUreticiDepoCount() {
        return depoRepository.countByKullanici_Rol(Kullanici.Role.URETICI);
    }

    public void createUretici(String adiSoyadi, String eposta, String tel, String sehir, String notlar, int tuccarId) {
        Kullanici kullanici = new Kullanici();
        kullanici.setEposta(eposta);
        kullanici.setSifre(passwordEncoder.encode("test"));
        kullanici.setRol(Kullanici.Role.URETICI);
        kullaniciRepository.save(kullanici);

        Uretici uretici = new Uretici();
        uretici.setAdiSoyadi(adiSoyadi);
        uretici.setTel(tel);
        uretici.setSehir(sehir);
        uretici.setNotlar(notlar);
        uretici.setKullaniciId(kullanici.getKullaniciId());
        ureticiRepository.save(uretici);

        UreticiTuccar ureticiTuccar = new UreticiTuccar();
        ureticiTuccar.setUreticiId(uretici.getUreticiId());
        ureticiTuccar.setTuccarId(tuccarId);
        ureticiTuccarRepository.save(ureticiTuccar);

        System.out.println("UreticiTuccar ilişkilendirmesi yapıldı: " + ureticiTuccar);
    }
}
