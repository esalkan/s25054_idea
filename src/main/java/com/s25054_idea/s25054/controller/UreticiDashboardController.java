package com.s25054_idea.s25054.controller;

import com.s25054_idea.s25054.model.UrunAlimFormu;
import com.s25054_idea.s25054.model.Kullanici;
import com.s25054_idea.s25054.model.Uretici;
import com.s25054_idea.s25054.model.FormDurumu;
import com.s25054_idea.s25054.service.KullaniciService;
import com.s25054_idea.s25054.service.UrunAlimFormuService;
import com.s25054_idea.s25054.service.UreticiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UreticiDashboardController {

    @Autowired
    private UrunAlimFormuService urunAlimFormuService;

    @Autowired
    private KullaniciService kullaniciService;

    @Autowired
    private UreticiService ureticiService;

    @GetMapping("/uretici/dashboard")
    public String ureticiDashboard() {
        return "uretici/uretici-dashboard";
    }

    @GetMapping("/uretici/uretici-alim-satim-islemleri")
    public String ureticiAlimSatimIslemleri(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Kullanici kullanici = kullaniciService.findByEposta(auth.getName());
        Uretici uretici = ureticiService.findByKullaniciId(kullanici.getKullaniciId()).orElse(null);
        if (uretici != null) {
            List<UrunAlimFormu> urunAlimFormlari = urunAlimFormuService.findByUreticiId(uretici.getUreticiId());
            model.addAttribute("urunAlimFormlari", urunAlimFormlari);
        }
        return "uretici/uretici-alim-satim-islemleri";
    }

    @PostMapping("/uretici/onayla")
    public String onayla(@RequestParam("formId") int formId) {
        UrunAlimFormu form = urunAlimFormuService.findById(formId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid form Id:" + formId));
        urunAlimFormuService.updateFormDurumu(form, FormDurumu.ONAYLANDI);
        return "redirect:/uretici/uretici-alim-satim-islemleri";
    }

    @PostMapping("/uretici/reddet")
    public String reddet(@RequestParam("formId") int formId) {
        UrunAlimFormu form = urunAlimFormuService.findById(formId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid form Id:" + formId));
        urunAlimFormuService.updateFormDurumu(form, FormDurumu.REDDEDILDI);
        return "redirect:/uretici/uretici-alim-satim-islemleri";
    }
}
