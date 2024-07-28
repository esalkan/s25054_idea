package com.s25054_idea.s25054.controller;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.DepoRepository;
import com.s25054_idea.s25054.repository.KullaniciRepository;
import com.s25054_idea.s25054.repository.TuccarRepository;
import com.s25054_idea.s25054.repository.UreticiRepository;
import com.s25054_idea.s25054.repository.UreticiTuccarRepository;
import com.s25054_idea.s25054.service.KullaniciService;
import com.s25054_idea.s25054.service.UrunAlimFormuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminDashboardController {

    @Autowired
    private DepoRepository depoRepository;

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private TuccarRepository tuccarRepository;

    @Autowired
    private UreticiRepository ureticiRepository;

    @Autowired
    private UreticiTuccarRepository ureticiTuccarRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private KullaniciService kullaniciService;

    @Autowired
    private UrunAlimFormuService urunAlimFormuService;

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("tuccarCount", kullaniciService.getTuccarCount());
        model.addAttribute("ureticiCount", kullaniciService.getUreticiCount());
        model.addAttribute("tuccarDepoCount", kullaniciService.getTuccarDepoCount());
        model.addAttribute("ureticiDepoCount", kullaniciService.getUreticiDepoCount());

        model.addAttribute("totalFormCount", urunAlimFormuService.countAllForms());
        model.addAttribute("onayBekliyorCount", urunAlimFormuService.countByDurum(FormDurumu.ONAY_BEKLIYOR));
        model.addAttribute("onaylandiCount", urunAlimFormuService.countByDurum(FormDurumu.ONAYLANDI));
        model.addAttribute("reddedildiCount", urunAlimFormuService.countByDurum(FormDurumu.REDDEDILDI));

        return "admin/admin-dashboard";
    }

    @GetMapping("/admin/depo-islemleri")
    public String depoIslemleri() {
        return "admin/depo-islemleri";
    }

    @GetMapping("/admin/depolar")
    public String tumDepolar(Model model) {
        List<Depo> depolar = depoRepository.findAll();
        model.addAttribute("depolar", depolar);
        return "admin/tum-depolar";
    }

    @GetMapping("/admin/depo-ekle")
    public String depoEkle(Model model) {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        model.addAttribute("kullanicilar", kullanicilar);
        return "admin/depo-ekle";
    }

    @PostMapping("/admin/depo-ekle")
    public String depoEklePost(@RequestParam String depoAdi, @RequestParam String aciklama, @RequestParam int kullaniciId) {
        Kullanici kullanici = kullaniciRepository.findById(kullaniciId).orElseThrow(() -> new IllegalArgumentException("Geçersiz Kullanıcı ID: " + kullaniciId));
        Depo depo = new Depo();
        depo.setDepoAdi(depoAdi);
        depo.setAciklama(aciklama);
        depo.setKullanici(kullanici);
        depoRepository.save(depo);
        return "redirect:/admin/depolar";
    }

    @PostMapping("/admin/depo-guncelle")
    public String depoGuncelle(@RequestParam int depoId, @RequestParam String depoAdi, @RequestParam String aciklama) {
        Depo depo = depoRepository.findById(depoId).orElseThrow(() -> new IllegalArgumentException("Geçersiz Depo ID: " + depoId));
        depo.setDepoAdi(depoAdi);
        depo.setAciklama(aciklama);
        depoRepository.save(depo);
        return "redirect:/admin/depolar";
    }

    @PostMapping("/admin/depo-sil")
    public String depoSil(@RequestParam int depoId) {
        depoRepository.deleteById(depoId);
        return "redirect:/admin/depolar";
    }

    @GetMapping("/admin/kullanici-islemleri")
    public String kullaniciIslemleri() {
        return "admin/kullanici-islemleri";
    }

    @GetMapping("/admin/tum-kullanicilar")
    public String tumKullanicilar(Model model) {
        model.addAttribute("kullanicilar", kullaniciRepository.findAll());
        return "admin/tum-kullanicilar";
    }

    @GetMapping("/admin/kullanici-detay/{id}")
    public ResponseEntity<Kullanici> getKullaniciDetay(@PathVariable int id) {
        Optional<Kullanici> kullaniciOpt = kullaniciRepository.findById(id);
        if (kullaniciOpt.isPresent()) {
            return ResponseEntity.ok(kullaniciOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/yeni-kullanici-ekle")
    public String yeniKullaniciEkle(Model model) {
        model.addAttribute("tuccarlar", tuccarRepository.findAll());
        return "admin/yeni-kullanici-ekle";
    }

    @PostMapping("/admin/yeni-kullanici-ekle")
    public String yeniKullaniciEklePost(@RequestParam String eposta, @RequestParam String sifre, @RequestParam String rol,
                                        @RequestParam(required = false) String firmaAdi, @RequestParam(required = false) String firmaYetkilisi,
                                        @RequestParam(required = false) String tel, @RequestParam(required = false) String sehir,
                                        @RequestParam(required = false) String notlar, @RequestParam(required = false) String adiSoyadi,
                                        @RequestParam(required = false) Integer tuccarId) {
        Kullanici kullanici = new Kullanici();
        kullanici.setEposta(eposta);
        kullanici.setSifre(passwordEncoder.encode(sifre));
        kullanici.setRol(Kullanici.Role.valueOf(rol));
        kullaniciRepository.save(kullanici);

        if ("TUCCAR".equals(rol)) {
            Tuccar tuccar = new Tuccar();
            tuccar.setKullaniciId(kullanici.getKullaniciId());
            tuccar.setFirmaAdi(firmaAdi);
            tuccar.setFirmaYetkilisi(firmaYetkilisi);
            tuccar.setTel(tel);
            tuccar.setSehir(sehir);
            tuccar.setNotlar(notlar);
            tuccarRepository.save(tuccar);
        } else if ("URETICI".equals(rol)) {
            Uretici uretici = new Uretici();
            uretici.setKullaniciId(kullanici.getKullaniciId());
            uretici.setAdiSoyadi(adiSoyadi != null ? adiSoyadi.trim() : null);
            uretici.setTel(tel != null ? tel.trim() : null);
            uretici.setSehir(sehir != null ? sehir.trim() : null);
            uretici.setNotlar(notlar != null ? notlar.trim() : null);
            ureticiRepository.save(uretici);

            if (tuccarId != null) {
                ureticiRepository.findById(uretici.getUreticiId()).ifPresent(u -> {
                    Tuccar tuccar = tuccarRepository.findById(tuccarId).orElseThrow(() -> new IllegalArgumentException("Geçersiz Tüccar ID: " + tuccarId));
                    UreticiTuccar ureticiTuccar = new UreticiTuccar();
                    ureticiTuccar.setUreticiId(u.getUreticiId());
                    ureticiTuccar.setTuccarId(tuccar.getTuccarId());
                    ureticiTuccarRepository.save(ureticiTuccar);
                });
            }
        }

        return "redirect:/admin/tum-kullanicilar";
    }

    @GetMapping("/admin/urun-alim-islemleri")
    public String urunAlimIslemleri(Model model) {
        List<UrunAlimFormu> urunAlimFormlari = urunAlimFormuService.findAll();
        model.addAttribute("urunAlimFormlari", urunAlimFormlari);
        return "admin/urun-alim-islemleri";
    }
}
