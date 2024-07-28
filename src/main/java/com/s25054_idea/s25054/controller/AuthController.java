package com.s25054_idea.s25054.controller;

import com.s25054_idea.s25054.model.Kullanici;
import com.s25054_idea.s25054.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Kullanıcı adı ya da şifre yanlış.");
        }
        if (logout != null) {
            model.addAttribute("message", "Başarıyla çıkış yaptınız.");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("kullanici", new Kullanici());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Kullanici kullanici) {
        kullanici.setSifre(passwordEncoder.encode(kullanici.getSifre()));
        kullaniciRepository.save(kullanici);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {
        Kullanici kullanici = kullaniciRepository.findByEposta(authentication.getName()).orElseThrow();
        switch (kullanici.getRol()) {
            case ADMIN:
                return "redirect:/admin/dashboard";
            case TUCCAR:
                return "redirect:/tuccar/dashboard";
            case URETICI:
                return "redirect:/uretici/dashboard";
            default:
                throw new IllegalStateException("Unexpected value: " + kullanici.getRol());
        }
    }
}
