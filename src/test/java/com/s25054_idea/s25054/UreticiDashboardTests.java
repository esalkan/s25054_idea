package com.s25054_idea.s25054;

import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import java.util.Random;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UreticiDashboardTests {

    static Page page;
    static Random random;

    @BeforeAll
    public static void setup() {
        page = PlaywrightSetup.getPage();
        random = new Random();
        ureticiLogin();
    }

    @BeforeEach
    public void beforeEach() {
        page.navigate("http://localhost:8080/uretici/dashboard");
    }

    @AfterAll
    public static void tearDown() {
        PlaywrightSetup.close();
    }

    private static void ureticiLogin() {
        page.navigate("http://localhost:8080/login");
        page.fill("input[name='username']", "uretici1@test.com");
        page.fill("input[name='password']", "test");
        page.click("button[type='submit']");
        assertThat(page).hasURL("http://localhost:8080/uretici/dashboard");
    }

    @Test
    @Description("Üretici alım satım işlemleri linkine tıklayarak alım satım işlemleri sayfasına gitmeli")
    public void testUreticiDashboardAlimSatimIslemleriLinki() {
        page.click("a[href='/uretici/uretici-alim-satim-islemleri']");
        assertThat(page).hasURL("http://localhost:8080/uretici/uretici-alim-satim-islemleri");
    }

    @Test
    @Description("Üretici rastgele bir alım satım işlemini onaylamalı")
    public void testAlimSatimIsleminiOnayla() {
        page.click("a[href='/uretici/uretici-alim-satim-islemleri']");

        int rowCount = (int) page.locator("table tbody tr").count();
        if (rowCount > 0) {
            int randomRow = random.nextInt(rowCount);
            page.locator("table tbody tr").nth(randomRow).locator("button:has-text('Onay İşlemleri')").click();

            // Onayla butonuna tıklayın
            page.locator("button:has-text('Onayla')").click();
            assertThat(page.locator("table tbody tr").nth(randomRow).locator("button:has-text('Onaylandı')")).isVisible();
        }
    }

    @Test
    @Description("Üretici rastgele bir alım satım işlemini reddetmeli")
    public void testAlimSatimIsleminiReddet() {
        page.click("a[href='/uretici/uretici-alim-satim-islemleri']");

        int rowCount = (int) page.locator("table tbody tr").count();
        if (rowCount > 0) {
            int randomRow = random.nextInt(rowCount);
            page.locator("table tbody tr").nth(randomRow).locator("button:has-text('Onay İşlemleri')").click();

            // Reddet butonuna tıklayın
            page.locator("button:has-text('Reddet')").click();
            assertThat(page.locator("table tbody tr").nth(randomRow).locator("button:has-text('Reddedildi')")).isVisible();
        }
    }
}
