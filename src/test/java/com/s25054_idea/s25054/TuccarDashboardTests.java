package com.s25054_idea.s25054;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TuccarDashboardTests {

    static Page page;
    static Random random;

    @BeforeAll
    public static void setup() {
        page = PlaywrightSetup.getPage();
        random = new Random();
        tuccarLogin();
    }

    @BeforeEach
    public void beforeEach() {
        page.navigate("http://localhost:8080/tuccar/dashboard");
    }

    @AfterAll
    public static void tearDown() {
        PlaywrightSetup.close();
    }

    private static void tuccarLogin() {
        page.navigate("http://localhost:8080/login");
        page.fill("input[name='username']", "tuccar1@test.com");
        page.fill("input[name='password']", "test");
        page.click("button[type='submit']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/dashboard");
    }

    @Test
    @Description("Tüccar depo işlemleri linkine tıklayarak depo işlemleri sayfasına gitmeli")
    public void testTuccarDashboardDepoIslemleriLinki() {
        page.click("a[href='/tuccar/tuccar-depo-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-depo-islemleri");
    }

    @Test
    @Description("Tüccar üretici işlemleri linkine tıklayarak üretici işlemleri sayfasına gitmeli")
    public void testTuccarDashboardUreticiIslemleriLinki() {
        page.click("a[href='/tuccar/tuccar-uretici-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-uretici-islemleri");
    }

    @Test
    @Description("Tüccar alım satım işlemleri linkine tıklayarak alım satım işlemleri sayfasına gitmeli")
    public void testTuccarDashboardAlimSatimIslemleriLinki() {
        page.click("a[href='/tuccar/tuccar-alim-satim-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-alim-satim-islemleri");
    }

    @Test
    @Description("Tüccar tüm depoları görüntüleyebilmeli")
    public void testTuccarTumDepolarGoruntule() {
        page.click("a[href='/tuccar/tuccar-depo-islemleri']");
        assertThat(page.locator("table")).isVisible();
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-depo-islemleri");
    }

    @Test
    @Description("Tüccar tüm alım satım işlemlerini görüntüleyebilmeli")
    public void testTuccarTumAlimSatimIslemleriGoruntule() {
        page.click("a[href='/tuccar/tuccar-alim-satim-islemleri']");
        page.click("a[href='/tuccar/tuccar-alim-satim-islem-durumlari']");
        assertThat(page.locator("table")).isVisible();
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-alim-satim-islem-durumlari");
    }

    @Test
    @Description("Tüccar yeni depo ekleyebilmeli")
    public void testYeniDepoEkle() {
        page.click("a[href='/tuccar/tuccar-depo-islemleri']");
        page.click("a[href='/tuccar/tuccar-depo-ekle']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-depo-ekle");

        String depoAdi = "Depo " + random.nextInt(1000);
        page.fill("input[name='depoAdi']", depoAdi);
        page.fill("textarea[name='aciklama']", "Bu bir test deposudur.");

        page.click("button:has-text('Kaydet')");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-depo-islemleri");
    }

    @Test
    @Description("Tüccar yeni ürün alım kaydı ekleyebilmeli")
    public void testYeniUrunAlimKaydiEkle() {
        page.click("a[href='/tuccar/tuccar-alim-satim-islemleri']");
        page.click("a[href='/tuccar/tuccar-yeni-urun-alim-kaydi']");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-yeni-urun-alim-kaydi");

        String urunCinsi = "Ürün " + random.nextInt(1000);
        page.selectOption("select[name='gonderilecekDepoId']", page.locator("select[name='gonderilecekDepoId'] option").nth(random.nextInt((int) page.locator("select[name='gonderilecekDepoId'] option").count())).getAttribute("value"));
        page.selectOption("select[name='ureticiId']", page.locator("select[name='ureticiId'] option").nth(random.nextInt((int) page.locator("select[name='ureticiId'] option").count())).getAttribute("value"));
        page.fill("input[name='urunCinsi']", urunCinsi);
        page.fill("input[name='urunMiktari']", "100");
        page.fill("input[name='urunBirimi']", "kg");
        page.fill("input[name='teklifFiyati']", "1000");
        page.fill("textarea[name='aciklama']", "Bu bir test ürün alımıdır.");

        page.click("button:has-text('Kaydet')");
        assertThat(page).hasURL("http://localhost:8080/tuccar/tuccar-alim-satim-islem-durumlari");
    }

    @Test
    @Description("Tüccar depo ürünlerini görüntüleyebilmeli")
    public void testDepoUrunleriGoruntule() {
        page.click("a[href='/tuccar/tuccar-depo-islemleri']");
        page.click("a:has-text('Ürünleri Görüntüle')");
        assertThat(page.locator("table")).isVisible();
        assertTrue(page.url().contains("http://localhost:8080/tuccar/tuccar-depo-urunler"));
    }
}
