# Bank Sampah REST API

REST API untuk aplikasi **Bank Sampah** yang memudahkan pengguna untuk melakukan pendataan sampah.

## Fitur Utama

- **Autentikasi**: Menggunakan **Spring Security** untuk login dengan token JWT.
- **Otorisasi**: Mendukung 2 role user, yaitu:
    - **Admin**: Mengelola data transaksi, customer, dan sampah.
    - **Customer**: Menambahkan transaksi (menabung sampah).
- **Dokumentasi API**: Menggunakan **Swagger** untuk dokumentasi API.
- **Entitas**:
    - **UserAccount**: Data akun dari pengguna admin dan customer.
    - **Customer**: Informasi umum pelanggan.
    - **Trash**: Informasi sampah beserta harga per kilogramnya.
    - **Transaction**: Data transaksi tabungan sampah dari customer.
    - **TransactionDetail**: Detail sampah yang ditabung dalam transaksi.

---

## Teknologi yang Digunakan

- **Backend**: Spring Boot (versi terbaru)
- **Keamanan**:
    - Spring Security
    - JSON Web Tokens (JWT)
- **Database**: PostgreSQL
- **Dokumentasi API**: Swagger UI
- **Tools Tambahan**:
    - Maven untuk manajemen dependensi.

---

## Cara Menjalankan Proyek

### Prasyarat

1. **Java**: Menggunakan Java 17
2. **Maven**: Proyek Spring Boot ini menggunakan Maven
3. **Database**: PostgreSQL
4. **Git**: Untuk mengunduh kode sumber.

### Langkah-Langkah

1. Clone repositori:
   ```bash
   git clone https://github.com/Refanz/bank-sampah-app
   cd bank-sampah-app
2. Melakukan konfigurasi **application.properties**
   ```properties
   # SQL Configuration
   spring.datasource.url=jdbc:postgresql://localhost:5432/trash_bank_app
   spring.datasource.username=username
   spring.datasource.password=password
   spring.jpa.hibernate.ddl-auto=update
   
   # JWT Configuration
   trash.app.jwt-secret=${JWT_SECRET:jwt_secret_token}
   trash.app.jwt-exp-in-minutes-access-token=${JWT_EXP_IN_MINUTES_ACCESS_TOKEN:10}
   trash.app.jwt-issuer=${JWT_ISSUER:Trash App}

3. Menjalankan Aplikasi
   ```bash
   mvn clean build -DskipTests
   java -jar target/nama-aplikasi-version.jar

## Dokumentasi API

Swagger UI dapat diakses di:

```bash
http://localhost:8080/swagger-ui/index.html
