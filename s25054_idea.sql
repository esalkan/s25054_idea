use s25054;

-- kullanici tablosu
CREATE TABLE kullanici (
    kullanici_id INT PRIMARY KEY AUTO_INCREMENT,
    eposta VARCHAR(255) NOT NULL UNIQUE,
    sifre VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'TUCCAR', 'URETICI') NOT NULL
);

-- tuccar tablosu
CREATE TABLE tuccar (
    tuccar_id INT PRIMARY KEY AUTO_INCREMENT,
    kullanici_id INT,
    firma_adi VARCHAR(255),
    firma_yetkilisi VARCHAR(255),
    tel VARCHAR(20),
    sehir VARCHAR(100),
    notlar TEXT,
    FOREIGN KEY (kullanici_id) REFERENCES kullanici(kullanici_id)
);

-- uretici tablosu
CREATE TABLE uretici (
    uretici_id INT PRIMARY KEY AUTO_INCREMENT,
    kullanici_id INT,
    adi_soyadi VARCHAR(255),
    tel VARCHAR(20),
    sehir VARCHAR(100),
    notlar TEXT,
    FOREIGN KEY (kullanici_id) REFERENCES kullanici(kullanici_id)
);


    -- uretici_tuccar table with ON DELETE CASCADE
CREATE TABLE uretici_tuccar (
    id INT PRIMARY KEY AUTO_INCREMENT,
    uretici_id INT,
    tuccar_id INT,
    FOREIGN KEY (uretici_id) REFERENCES uretici(uretici_id),
    FOREIGN KEY (tuccar_id) REFERENCES tuccar(tuccar_id) ON DELETE CASCADE
);


-- depo tablosu
CREATE TABLE depo (
    depo_id INT PRIMARY KEY AUTO_INCREMENT,
    kullanici_id INT,
    depo_adi VARCHAR(255),
    aciklama TEXT,
    FOREIGN KEY (kullanici_id) REFERENCES kullanici(kullanici_id)
);

-- urun alim formlari tablosu
CREATE TABLE urun_alim_formu (
    form_id INT PRIMARY KEY AUTO_INCREMENT,
    gonderen_tuccar_id INT,
    gonderilecek_depo_id INT,
    uretici_id INT,
    urun_cinsi VARCHAR(255),
    urun_miktari DECIMAL(10, 2),
    urun_birimi VARCHAR(50),
    teklif_fiyati DECIMAL(10, 2),
    aciklama TEXT,
    durum ENUM('onay bekliyor', 'onaylandi', 'reddedildi') DEFAULT 'onay bekliyor',
    FOREIGN KEY (gonderen_tuccar_id) REFERENCES tuccar(tuccar_id),
    FOREIGN KEY (gonderilecek_depo_id) REFERENCES depo(depo_id),
    FOREIGN KEY (uretici_id) REFERENCES uretici(uretici_id)
);

-- form durumlari tablosu
CREATE TABLE form_durumlari (
    durum_id INT PRIMARY KEY AUTO_INCREMENT,
    form_id INT,
    durum ENUM('onay bekliyor', 'onaylandi', 'reddedildi'),
    FOREIGN KEY (form_id) REFERENCES urun_alim_formu(form_id)
);

