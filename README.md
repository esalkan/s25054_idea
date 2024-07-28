<h1 align="center">Yazılım Geliştirme Projesi - Software Development Project</h1>
<p align="center">Bilgi Teknolojileri Sertifika Programı - Information Technologies Certificate Program</p>
<p align="center">IDEA: ODTÜ Sanal Kampüsü - IDEA: METU Virtual Campus</p>

## Project Description
This project is a Spring Boot application that provides an admin, trader, and producer dashboard. Users can manage depots and product acquisition transactions. The project works with MySQL database and Thymeleaf for the frontend.
Bu proje, bir yönetici, tüccar ve üretici panosu sağlayan bir Spring Boot uygulamasıdır. Kullanıcılar depoları ve ürün alım işlemlerini yönetebilirler. Proje, MySQL veritabanı ve frontend için Thymeleaf ile çalışır.

## Requirements - Gereksinimler
- Java 21 Corretto
- Maven
- MySQL
- Git
- Playwright
- Allure Report

## Installation - Kurulum

### Step 1: Clone the Project - Adım 1: Projeyi Klonlayın
```bash 
git clone https://github.com/your_username/s25054_idea.git
cd s25054_idea
```

### Step 2: Set Up MySQL Database - Adım 2: MySQL Veritabanını Kurun
Create a database in your MySQL server using the following commands:
MySQL sunucunuzda aşağıdaki komutları kullanarak bir veritabanı oluşturun:

```
CREATE DATABASE s25054_idea;
CREATE USER 's25054_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON s25054_idea.* TO 's25054_user'@'localhost';
FLUSH PRIVILEGES;
```

### Step 3: Configure Application Settings - Adım 3: Uygulama Ayarlarını Yapılandırın
Open the src/main/resources/application.properties file and configure the MySQL settings as follows:
src/main/resources/application.properties dosyasını açın ve MySQL ayarlarını aşağıdaki gibi yapılandırın:

```spring.datasource.url=jdbc:mysql://localhost:3306/s25054_idea
spring.datasource.username=s25054_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
```
### Step 4: Download Maven Dependencies - Adım 4: Maven Bağımlılıklarını İndirin
Run the following command in the project directory to download the necessary dependencies:
Gerekli bağımlılıkları indirmek için proje dizininde aşağıdaki komutu çalıştırın:


```
mvn clean install
```
## Running the Application - Uygulamayı Çalıştırma
### Step 1: Start the Application - Adım 1: Uygulamayı Başlatın
Run the following command to start the application:
Uygulamayı başlatmak için aşağıdaki komutu çalıştırın:

```
mvn spring-boot:run
```


### Step 2: Open the Application in a Browser - Adım 2: Uygulamayı Bir Tarayıcıda Açın
```
Go to http://localhost:8080
```

## Testing - Testler
### Step 1: Set Up Playwright and Allure for Testing - Adım 1: Playwright ve Allure Kurulumu
Ensure that Playwright and Allure are set up and installed. Run the following commands to install Playwright and Allure:
Playwright ve Allure'in kurulu olduğundan emin olun. Playwright ve Allure'i kurmak için aşağıdaki komutları çalıştırın:
```
npx playwright install
brew install allure
```

### Step 2: Run the Tests with Allure - Adım 2: Testleri Allure ile Çalıştırın
Run the following commands to execute the tests and generate the Allure report:
Testleri çalıştırmak ve Allure raporunu oluşturmak için aşağıdaki komutları çalıştırın:
```
mvn clean test
mvn allure:serve
mvn allure:report
```
# Proje Amacı - Project Purpose

Bu proje, tarımsal üretim yapılan bölgelerde üreticiler ve tüccarlar arasında yapılan alım-satım işlemlerinin şeffaf ve güvenilir bir şekilde kayıt altına alınmasını amaçlamaktadır. Kağıt tabanlı alım-satım işlemlerinin yol açtığı karışıklıklar ve anlaşmazlıklar önlenerek, taraflar arasındaki güven ve işbirliği artırılacaktır. Beklenen sonuçlar arasında, işlem sürecinin dijitalleştirilmesi, veri bütünlüğünün sağlanması ve tarafların ticari işlemlerini daha etkin bir şekilde yönetmesi yer almaktadır.

This project aims to record the purchase and sale transactions between producers and traders in agricultural production areas in a transparent and reliable manner. By preventing the confusion and disputes caused by paper-based transactions, trust and cooperation between parties will be increased. Expected outcomes include digitizing the transaction process, ensuring data integrity, and enabling parties to manage their commercial transactions more effectively.

---

# Proje Tanımı - Project Description

Bu proje, tüccarların üreticilerden aldığı tarımsal ürünlerin kayıt altına alınmasını sağlayan, web tabanlı bir yönetim sistemidir. Tüccarlar, ürün alımlarını ön ödeme ya da peşin ödeme şeklinde belgeleyebilecek, ürünlerin hangi depolarda saklandığını takip edebilecek ve barkod sistemi ile ürünlerin takibini yapabilecektir. Üreticiler, kendi taraflarında alım-satım kayıtlarını onaylayarak işlemlerin doğruluğunu teyit edecek ve böylece her iki taraf için güvenilir bir ticaret ortamı sağlanacaktır.

This project is a web-based management system that records agricultural products purchased by traders from producers. Traders will be able to document their purchases either as pre-payment or cash payment, track which warehouses the products are stored in, and follow the products with a barcode system. Producers will confirm the purchase and sale records on their side, verifying the accuracy of the transactions, thus creating a reliable trading environment for both parties.

---

# Projenin Kapsamı - Project Scope
Bu proje, tüccarların üreticilerden aldığı tarımsal ürünlerin kayıt altına alınmasını sağlayan, web tabanlı bir yönetim sistemidir. Sistem, farklı kullanıcı rollerine göre işlemlerin yapılmasını ve yönetilmesini sağlayacaktır. Kullanıcı rolleri ve iş akışı aşağıdaki gibidir:

This project is a web-based management system that records agricultural products purchased by traders from producers. The system will enable transactions and management according to different user roles. User roles and workflows are as follows:

---

