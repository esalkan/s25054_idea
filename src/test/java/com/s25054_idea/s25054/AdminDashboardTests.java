package com.s25054_idea.s25054;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminDashboardTests {

    static Page page;
    static Random random;

    @BeforeAll
    public static void setup() {
        page = PlaywrightSetup.getPage();
        random = new Random();
        adminLogin();
    }

    @BeforeEach
    public void beforeEach() {
        page.navigate("http://localhost:8080/admin/dashboard");
    }

    @AfterAll
    public static void tearDown() {
        PlaywrightSetup.close();
    }

    private static void adminLogin() {
        page.navigate("http://localhost:8080/login");
        page.fill("input[name='username']", "admin@test.com");
        page.fill("input[name='password']", "test");
        page.click("button[type='submit']");
        assertThat(page).hasURL("http://localhost:8080/admin/dashboard");
    }

    @Test
    @Description("Admin kullanıcı işlemleri linkine tıklayarak kullanıcı işlemleri sayfasına gitmeli")
    public void testAdminDashboardKullaniciIslemleriLinki() {
        page.click("a[href='/admin/kullanici-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/admin/kullanici-islemleri");
    }

    @Test
    @Description("Admin depo işlemleri linkine tıklayarak depo işlemleri sayfasına gitmeli")
    public void testAdminDashboardDepoIslemleriLinki() {
        page.click("a[href='/admin/depo-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/admin/depo-islemleri");
    }

    @Test
    @Description("Admin ürün alım işlemleri linkine tıklayarak ürün alım işlemleri sayfasına gitmeli")
    public void testAdminDashboardUrunAlimIslemleriLinki() {
        page.click("a[href='/admin/urun-alim-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/admin/urun-alim-islemleri");
    }

    @Test
    @Description("Admin tüm depoları görüntüleyebilmeli")
    public void testTumDepolarGoruntule() {
        page.click("a[href='/admin/depo-islemleri']");
        page.click("a[href='/admin/depolar']");
        assertThat(page.locator("table")).isVisible();
        assertThat(page).hasURL("http://localhost:8080/admin/depolar");
    }

    @Test
    @Description("Admin tüm kullanıcıları görüntüleyebilmeli")
    public void testTumKullanicilarGoruntule() {
        page.click("a[href='/admin/kullanici-islemleri']");
        page.click("a[href='/admin/tum-kullanicilar']");
        assertThat(page.locator("table")).isVisible();
        assertThat(page).hasURL("http://localhost:8080/admin/tum-kullanicilar");
    }

    @Test
    @Description("Admin yeni depo ekleyebilmeli")
    public void testYeniDepoEkle() {
        page.click("a[href='/admin/depo-islemleri']");
        page.click("a[href='/admin/depo-ekle']");
        assertThat(page).hasURL("http://localhost:8080/admin/depo-ekle");

        String depoAdi = "Depo " + random.nextInt(1000);
        page.fill("input[name='depoAdi']", depoAdi);
        page.fill("textarea[name='aciklama']", "Bu bir test deposudur.");

        page.selectOption("select[name='kullaniciId']", page.locator("select[name='kullaniciId'] option").nth(random.nextInt((int) page.locator("select[name='kullaniciId'] option").count())).getAttribute("value"));

        page.click("button:has-text('Kaydet')");

        assertThat(page).hasURL("http://localhost:8080/admin/depolar");
    }

    @Test
    @Description("Admin yeni tüccar ekleyebilmeli")
    public void testYeniTuccarEkle() {
        page.click("a[href='/admin/kullanici-islemleri']");
        page.click("a[href='/admin/yeni-kullanici-ekle']");
        assertThat(page).hasURL("http://localhost:8080/admin/yeni-kullanici-ekle");

        String kullaniciEposta = "tuccar" + random.nextInt(1000) + "@example.com";
        String kullaniciSifre = "password";

        page.fill("input[name='eposta']", kullaniciEposta);
        page.fill("input[name='sifre']", kullaniciSifre);
        page.selectOption("select[name='rol']", "TUCCAR");

        page.waitForSelector("input[name='firmaAdi']");

        String firmaAdi = "Firma " + random.nextInt(1000);
        String firmaYetkilisi = "Yetkili " + random.nextInt(1000);
        String tel = "1234567890";
        String sehir = "Ankara";
        String notlar = "Bu bir test tüccarıdır.";

        page.fill("input[name='firmaAdi']", firmaAdi);
        page.fill("input[name='firmaYetkilisi']", firmaYetkilisi);
        page.fill("input[name='tel']", tel);
        page.fill("input[name='sehir']", sehir);
        page.fill("textarea[name='notlar']", notlar);

        page.click("button:has-text('Kaydet')");

        assertThat(page).hasURL("http://localhost:8080/admin/tum-kullanicilar");
    }

    /*
    @Test
    @Description("Admin yeni üretici ekleyebilmeli")
    public void testYeniUreticiEkle() {
        page.click("a[href='/admin/kullanici-islemleri']");
        page.click("a[href='/admin/yeni-kullanici-ekle']");
        assertThat(page).hasURL("http://localhost:8080/admin/yeni-kullanici-ekle");

        String kullaniciEposta = "uretici" + random.nextInt(1000) + "@example.com";
        String kullaniciSifre = "password";

        page.fill("input[name='eposta']", kullaniciEposta);
        page.fill("input[name='sifre']", kullaniciSifre);
        page.selectOption("select[name='rol']", "URETICI");

        page.waitForSelector("input[name='adiSoyadi']");

        String adiSoyadi = "Üretici " + random.nextInt(1000);
        String tel = "1234567890";
        String sehir = "İstanbul";
        String notlar = "Bu bir test üreticisidir.";

        page.fill("input[name='adiSoyadi']", adiSoyadi);
        page.fill("input[name='tel']", tel);
        page.fill("input[name='sehir']", sehir);
        page.fill("textarea[name='notlar']", notlar);

        page.click("button:has-text('Kaydet')");

        assertThat(page).hasURL("http://localhost:8080/admin/tum-kullanicilar");
    }
    */
}
