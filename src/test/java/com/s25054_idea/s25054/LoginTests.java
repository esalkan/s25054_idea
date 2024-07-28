package com.s25054_idea.s25054;

import com.microsoft.playwright.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTests {
    Page page;

    @BeforeAll
    static void setUp() {
    }

    @AfterAll
    static void tearDown() {
        PlaywrightSetup.close();
    }

    @BeforeEach
    void createContextAndPage() {
        page = PlaywrightSetup.getPage();
    }

    @AfterEach
    void closeContext() {
        page.context().close();
    }

    @Test
    @Description("Pozitif Senaryo: Admin kullanıcı adı ve şifre ile giriş yapma")
    public void adminKullaniciBilgileriIleGirisBasariliOlmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("admin@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("admin");
    }

    @Test
    @Description("Pozitif Senaryo: Tüccar kullanıcı adı ve şifre ile giriş yapma")
    public void tuccarKullaniciBilgileriIleGirisBasariliOlmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("tuccar1@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("tuccar");
    }

    @Test
    @Description("Pozitif Senaryo: Üretici kullanıcı adı ve şifre ile giriş yapma")
    public void ureticiKullaniciBilgileriIleGirisBasariliOlmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("uretici1@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("uretici");
    }

    @Test
    @Description("Negatif Senaryo: Yanlış kullanıcı adı veya şifre ile giriş yapma")
    public void hataliKullaniciBilgileriIleGirisBasarisizOlmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("hatali@kullanici.com", "test");
        girisButonunaBas();
        hataliGirisiDogrula();
    }

    @Test
    @Description("Admin kullanıcı için çıkış yapma")
    public void adminKullaniciCikisYapmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("admin@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("admin");
        cikisYap();
        cikisDogrula();
    }

    @Test
    @Description("Tüccar kullanıcı için çıkış yapma")
    public void tuccarKullaniciCikisYapmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("tuccar1@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("tuccar");
        cikisYap();
        cikisDogrula();
    }

    @Test
    @Description("Üretici kullanıcı için çıkış yapma")
    public void ureticiKullaniciCikisYapmali() {
        loginSayfasinaGit();
        kullaniciBilgileriniGir("uretici1@test.com", "test");
        girisButonunaBas();
        basariliGirisiDogrula("uretici");
        cikisYap();
        cikisDogrula();
    }

    @Step("Login sayfasına git")
    void loginSayfasinaGit() {
        page.navigate("http://localhost:8080/login");
    }

    @Step("Kullanıcı adı: {0} ve şifre: {1} bilgilerini gir")
    void kullaniciBilgileriniGir(String username, String password) {
        page.fill("input[name='username']", username);
        page.fill("input[name='password']", password);
    }

    @Step("Giriş formunu gönder")
    void girisButonunaBas() {
        page.click("button[type='submit']");
    }

    @Step("Başarılı giriş doğrulaması")
    void basariliGirisiDogrula(String role) {
        assertThat(page).hasURL("http://localhost:8080/" + role + "/dashboard");
        assertThat(page.locator("text=Hoş Geldin " + role)).isVisible();
    }

    @Step("Başarısız giriş doğrulaması")
    void hataliGirisiDogrula() {
        assertThat(page).hasURL("http://localhost:8080/login?error=true");
    }

    @Step("Çıkış yap")
    void cikisYap() {
        page.click("form[action='/logout'] button[type='submit']");
    }

    @Step("Çıkış doğrulaması")
    void cikisDogrula() {
        assertThat(page).hasURL("http://localhost:8080/login?logout");
    }
}
