package com.s25054_idea.s25054;

import com.s25054_idea.s25054.model.*;
import com.s25054_idea.s25054.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private TuccarRepository tuccarRepository;

    @Autowired
    private UreticiRepository ureticiRepository;

    @Autowired
    private UreticiTuccarRepository ureticiTuccarRepository;

    @Autowired
    private DepoRepository depoRepository;

    @Autowired
    private UrunAlimFormuRepository urunAlimFormuRepository;

    @Autowired
    private FormDurumlariRepository formDurumlariRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        formDurumlariRepository.deleteAll();
        urunAlimFormuRepository.deleteAll();
        depoRepository.deleteAll();
        ureticiTuccarRepository.deleteAll();
        ureticiRepository.deleteAll();
        tuccarRepository.deleteAll();
        kullaniciRepository.deleteAll();

        Kullanici admin = new Kullanici();
        admin.setEposta("admin@test.com");
        admin.setSifre(passwordEncoder.encode("test"));
        admin.setRol(Kullanici.Role.ADMIN);
        kullaniciRepository.save(admin);

        List<Tuccar> tuccars = new ArrayList<>();
        String[][] tuccarData = {
                {"tuccar1@test.com", "ABC Gıda", "Ahmet Yılmaz", "05555550001", "Istanbul", "Gıda sektörü"},
                {"tuccar2@test.com", "DEF Fındık", "Mehmet Kaya", "05555550002", "Ankara", "Tekstil ürünleri"},
                {"tuccar3@test.com", "GHI Tarım", "Ayşe Demir", "05555550003", "Izmir", "İnşaat malzemeleri"},
                {"tuccar4@test.com", "JKL Otomotiv", "Fatma Şahin", "05555550004", "Bursa", "Otomotiv yedek parça"},
                {"tuccar5@test.com", "MNO Elektronik", "Ali Çelik", "05555550005", "Antalya", "Elektronik ürünler"}
        };

        for (String[] data : tuccarData) {
            Kullanici tuccarKullanici = new Kullanici();
            tuccarKullanici.setEposta(data[0]);
            tuccarKullanici.setSifre(passwordEncoder.encode("test"));
            tuccarKullanici.setRol(Kullanici.Role.TUCCAR);
            kullaniciRepository.save(tuccarKullanici);

            Tuccar tuccar = new Tuccar();
            tuccar.setKullaniciId(tuccarKullanici.getKullaniciId());
            tuccar.setFirmaAdi(data[1]);
            tuccar.setFirmaYetkilisi(data[2]);
            tuccar.setTel(data[3]);
            tuccar.setSehir(data[4]);
            tuccar.setNotlar(data[5]);
            tuccarRepository.save(tuccar);

            tuccars.add(tuccar);
        }

        List<Uretici> ureticiler = new ArrayList<>();
        String[][] ureticiData = {
                {"uretici1@test.com", "Hüseyin Çoban", "05555550101", "Istanbul", "Zeytin üretimi"},
                {"uretici2@test.com", "Emine Kara", "05555550102", "Ankara", "Bal üretimi"},
                {"uretici3@test.com", "Hasan Akın", "05555550103", "Izmir", "Peynir üretimi"},
                {"uretici4@test.com", "Zeynep Kılıç", "05555550104", "Bursa", "Sebze üretimi"},
                {"uretici5@test.com", "Murat Koç", "05555550105", "Antalya", "Meyve üretimi"},
                {"uretici6@test.com", "Ayşegül Arslan", "05555550106", "Adana", "Pamuk üretimi"},
                {"uretici7@test.com", "Mustafa Uçar", "05555550107", "Mersin", "Narenciye üretimi"},
                {"uretici8@test.com", "Fatma Polat", "05555550108", "Eskişehir", "Tahıl üretimi"},
                {"uretici9@test.com", "Kemal Özdemir", "05555550109", "Samsun", "Fındık üretimi"},
                {"uretici10@test.com", "Elif Aydın", "05555550110", "Trabzon", "Çay üretimi"},
                {"uretici11@example.com", "Ahmet Bulut", "05555550111", "Kayseri", "Yumurta üretimi"},
                {"uretici12@example.com", "Havva Yıldız", "05555550112", "Gaziantep", "Fıstık üretimi"},
                {"uretici13@example.com", "Mehmet Taş", "05555550113", "Konya", "Buğday üretimi"},
                {"uretici14@example.com", "Derya Güler", "05555550114", "Kocaeli", "Süt üretimi"},
                {"uretici15@example.com", "İsmail Acar", "05555550115", "Sakarya", "Kivi üretimi"},
                {"uretici16@example.com", "Seda Yılmaz", "05555550116", "Denizli", "Kurutulmuş meyve üretimi"},
                {"uretici17@example.com", "Ferhat Öztürk", "05555550117", "Malatya", "Kayısı üretimi"},
                {"uretici18@example.com", "Melike Özkan", "05555550118", "Manisa", "Üzüm üretimi"},
                {"uretici19@example.com", "Okan Karaca", "05555550119", "Kahramanmaraş", "Biber üretimi"},
                {"uretici20@example.com", "Buse Erdem", "05555550120", "Elazığ", "Domates üretimi"},
                {"uretici21@example.com", "Furkan Demir", "05555550121", "Tekirdağ", "Ayçiçeği üretimi"},
                {"uretici22@example.com", "Zeynep Ekinci", "05555550122", "Muğla", "Kekik üretimi"},
                {"uretici23@example.com", "Efe Bozkurt", "05555550123", "Kırıkkale", "Soğan üretimi"},
                {"uretici24@example.com", "Sevda Aksoy", "05555550124", "Ordu", "Çikolata üretimi"},
                {"uretici25@example.com", "Rıdvan Demir", "05555550125", "Balıkesir", "Balık üretimi"}
        };

        for (String[] data : ureticiData) {
            Kullanici ureticiKullanici = new Kullanici();
            ureticiKullanici.setEposta(data[0]);
            ureticiKullanici.setSifre(passwordEncoder.encode("test"));
            ureticiKullanici.setRol(Kullanici.Role.URETICI);
            kullaniciRepository.save(ureticiKullanici);

            Uretici uretici = new Uretici();
            uretici.setKullaniciId(ureticiKullanici.getKullaniciId());
            uretici.setAdiSoyadi(data[1]);
            uretici.setTel(data[2]);
            uretici.setSehir(data[3]);
            uretici.setNotlar(data[4]);
            ureticiRepository.save(uretici);

            ureticiler.add(uretici);

            UreticiTuccar ureticiTuccar = new UreticiTuccar();
            ureticiTuccar.setUreticiId(uretici.getUreticiId());
            ureticiTuccar.setTuccarId(tuccars.get((uretici.getUreticiId() - 1) % tuccars.size()).getTuccarId());
            ureticiTuccarRepository.save(ureticiTuccar);
        }

        String[] depoAdlari = {
                "Merkez Depo", "Istanbul Depo", "Ankara Depo", "Izmir Depo", "Bursa Depo"
        };

        List<Depo> depolar = new ArrayList<>();
        for (int i = 0; i < tuccars.size(); i++) {
            Depo depo = new Depo();
            depo.setDepoAdi(depoAdlari[i]);
            depo.setAciklama(depoAdlari[i] + " için açıklama");
            Kullanici tuccarKullanici = kullaniciRepository.findById(tuccars.get(i).getKullaniciId()).orElse(null);
            depo.setKullanici(tuccarKullanici);
            depoRepository.save(depo);
            depolar.add(depo);
        }

        for (int i = 0; i < 50; i++) {
            UrunAlimFormu form = new UrunAlimFormu();
            form.setGonderenTuccar(tuccars.get(i % tuccars.size()));
            form.setGonderilecekDepo(depolar.get(i % depolar.size()));
            form.setUretici(ureticiler.get(i % ureticiler.size()));
            form.setUrunCinsi("Ürün " + (i + 1));
            form.setUrunMiktari(100 + (i * 10));
            form.setUrunBirimi("kg");
            form.setTeklifFiyati(1000 + (i * 100));
            form.setAciklama("Açıklama " + (i + 1));
            form.setDurum(FormDurumu.ONAY_BEKLIYOR);

            urunAlimFormuRepository.save(form);

            FormDurumlari formDurum = new FormDurumlari();
            formDurum.setForm(form);
            formDurum.setDurum(FormDurumu.ONAY_BEKLIYOR);
            formDurumlariRepository.save(formDurum);
        }
    }
}