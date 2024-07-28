package com.s25054_idea.s25054.controller;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TuccarDashboardController {

    @Autowired
    private KullaniciService kullaniciService;

    @Autowired
    private TuccarService tuccarService;

    @Autowired
    private UreticiService ureticiService;

    @Autowired
    private DepoService depoService;

    @Autowired
    private UrunAlimFormuService urunAlimFormuService;

    @GetMapping("/tuccar/dashboard")
    public String tuccarDashboard() {
        return "tuccar/tuccar-dashboard";
    }

    @GetMapping("/tuccar/tuccar-depo-islemleri")
    public String depoIslemleri(Model model, Authentication authentication) {
        String eposta = authentication.getName();
        Kullanici kullanici = kullaniciService.findByEposta(eposta);
        List<Depo> depolar = depoService.findByKullaniciId(kullanici.getKullaniciId());
        model.addAttribute("depolar", depolar);
        return "tuccar/tuccar-depo-islemleri";
    }

    @GetMapping("/tuccar/tuccar-depo-ekle")
    public String yeniDepoEkleForm() {
        return "tuccar/tuccar-depo-ekle";
    }

    @PostMapping("/tuccar/tuccar-depo-ekle")
    public String yeniDepoEkle(@RequestParam String depoAdi, @RequestParam String aciklama, Authentication authentication) {
        String eposta = authentication.getName();
        Kullanici kullanici = kullaniciService.findByEposta(eposta);
        Depo depo = new Depo();
        depo.setDepoAdi(depoAdi);
        depo.setAciklama(aciklama);
        depo.setKullanici(kullanici);
        depoService.save(depo);
        return "redirect:/tuccar/tuccar-depo-islemleri";
    }

    @GetMapping("/tuccar/tuccar-depo-urunler/{depoId}")
    public String getTuccarDepoUrunler(@PathVariable int depoId, Model model) {
        List<UrunAlimFormu> urunAlimFormlari = urunAlimFormuService.findByDepoId(depoId);
        model.addAttribute("urunAlimFormlari", urunAlimFormlari);
        return "tuccar/tuccar-depo-urunler";
    }

    @GetMapping("/tuccar/tuccar-uretici-islemleri")
    public String ureticiIslemleri(Model model, Authentication authentication) {
        String eposta = authentication.getName();
        Integer tuccarId = kullaniciService.findTuccarIdByEposta(eposta);
        if (tuccarId != null) {
            List<Uretici> ureticiler = ureticiService.findByTuccarId(tuccarId);
            model.addAttribute("ureticiler", ureticiler);
        } else {
            model.addAttribute("ureticiler", List.of());
        }
        return "tuccar/tuccar-uretici-islemleri";
    }

    @GetMapping("/tuccar/tuccar-yeni-uretici-ekle")
    public String yeniUreticiEkleForm() {
        return "tuccar/tuccar-yeni-uretici-ekle";
    }

    @PostMapping("/tuccar/tuccar-yeni-uretici-ekle")
    public String yeniUreticiEkle(@RequestParam String adiSoyadi, @RequestParam String eposta, @RequestParam String tel,
                                  @RequestParam String sehir, @RequestParam String notlar, Authentication authentication) {
        String tuccarEposta = authentication.getName();
        Integer tuccarId = kullaniciService.findTuccarIdByEposta(tuccarEposta);
        if (tuccarId != null) {
            kullaniciService.createUretici(adiSoyadi, eposta, tel, sehir, notlar, tuccarId);
        } else {
            System.out.println("Tüccar ID bulunamadı: " + tuccarEposta);
        }
        return "redirect:/tuccar/tuccar-uretici-islemleri";
    }

    @GetMapping("/tuccar/tuccar-alim-satim-islemleri")
    public String alimsatimIslemleri() {
        return "tuccar/tuccar-alim-satim-islemleri";
    }

    @GetMapping("/tuccar/tuccar-alim-satim-islem-durumlari")
    public String alimsatimIslemDurumlari(Model model, Authentication authentication) {
        String eposta = authentication.getName();
        int tuccarId = kullaniciService.findTuccarIdByEposta(eposta);
        List<UrunAlimFormu> alimFormlari = urunAlimFormuService.findByTuccarId(tuccarId);

        // Debugging purpose
        System.out.println("Alim Formlari Size: " + alimFormlari.size());
        for(UrunAlimFormu form : alimFormlari) {
            System.out.println("Form ID: " + form.getFormId());
        }

        model.addAttribute("alimFormlari", alimFormlari);
        return "tuccar/tuccar-alim-satim-islem-durumlari";
    }

    @GetMapping("/tuccar/tuccar-yeni-urun-alim-kaydi")
    public String yeniUrunAlimKaydiForm(Model model, Authentication authentication) {
        String eposta = authentication.getName();
        Kullanici kullanici = kullaniciService.findByEposta(eposta);
        Integer tuccarId = kullaniciService.findTuccarIdByEposta(eposta);
        List<Depo> depolar = depoService.findByKullaniciId(kullanici.getKullaniciId());
        List<Uretici> ureticiler = ureticiService.findByTuccarId(tuccarId);
        model.addAttribute("depolar", depolar);
        model.addAttribute("ureticiler", ureticiler);
        return "tuccar/tuccar-yeni-urun-alim-kaydi";
    }

    @PostMapping("/tuccar/tuccar-yeni-urun-alim-kaydi")
    public String yeniUrunAlimKaydiPost(
            @RequestParam int gonderilecekDepoId,
            @RequestParam int ureticiId,
            @RequestParam String urunCinsi,
            @RequestParam double urunMiktari,
            @RequestParam String urunBirimi,
            @RequestParam double teklifFiyati,
            @RequestParam String aciklama,
            Authentication authentication) {

        String eposta = authentication.getName();
        Kullanici kullanici = kullaniciService.findByEposta(eposta);
        Tuccar tuccar = tuccarService.findByKullaniciId(kullanici.getKullaniciId()).orElseThrow(() -> new IllegalArgumentException("Tüccar bulunamadı"));

        UrunAlimFormu form = new UrunAlimFormu();
        form.setGonderenTuccar(tuccar);
        form.setGonderilecekDepo(depoService.findById(gonderilecekDepoId).orElseThrow(() -> new IllegalArgumentException("Depo bulunamadı")));
        form.setUretici(ureticiService.findById(ureticiId).orElseThrow(() -> new IllegalArgumentException("Üretici bulunamadı")));
        form.setUrunCinsi(urunCinsi);
        form.setUrunMiktari(urunMiktari);
        form.setUrunBirimi(urunBirimi);
        form.setTeklifFiyati(teklifFiyati);
        form.setAciklama(aciklama);
        form.setDurum(FormDurumu.ONAY_BEKLIYOR);

        urunAlimFormuService.save(form);

        return "redirect:/tuccar/tuccar-alim-satim-islem-durumlari";
    }
}
