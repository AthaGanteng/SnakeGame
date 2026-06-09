# 🐍 Snake Game — OOP Java Project

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

**Proyek Akhir Mata Kuliah Pemrograman Berorientasi Objek (PBO)**
Implementasi permainan ular klasik berbasis terminal menggunakan Java, dirancang dengan prinsip OOP dan dilengkapi unit testing otomatis dengan JUnit 5.

</div>

---

## 📖 Deskripsi Proyek

**Snake Game** adalah implementasi dari permainan ular klasik yang dijalankan di terminal (console-based). Pemain mengendalikan seekor ular yang bergerak di dalam arena berpagar, dengan tujuan memakan apel (`@`) sebanyak mungkin untuk menambah skor. Setiap apel yang dimakan akan membuat tubuh ular bertambah panjang, sekaligus meningkatkan kesulitan permainan.

### 🎮 Aturan Main

| Aturan | Keterangan |
|--------|-----------|
| 🍎 **Makan Apel** | Ular bergerak ke posisi apel (`@`) → tubuh bertambah, skor +10 |
  🍎 **Makan SuperApel** | Ular bergerak ke posisi apel (`?`) → tubuh bertambah sesuai input, skor + sesuai input |
| 💀 **Tabrak Dinding** | Kepala ular menyentuh batas arena → **Game Over** |
| 💀 **Tabrak Diri Sendiri** | Kepala ular menyentuh segmen tubuhnya → **Game Over** |
| 🔄 **Main Lagi** | Setelah Game Over, tekan `ENTER` untuk memulai ronde baru |
| 🏆 **High Score** | Skor tertinggi selama satu sesi bermain akan tersimpan dan ditampilkan |

### 🕹️ Kontrol Keyboard

```
  W  = Gerak ke Atas
  S  = Gerak ke Bawah
  A  = Gerak ke Kiri
  D  = Gerak ke Kanan
  Q  = Keluar / Quit
```

> **Catatan:** Ular tidak dapat berbalik 180° secara langsung (misalnya, dari `D` langsung ke `A`).

---

## ✨ Fitur Utama

### 🏗️ Desain OOP (Object-Oriented Programming)
- **Abstract Class** `Entity` — kelas induk untuk semua objek dalam game (`Snake`, `Apple`)
- **Interface** `Movable` — kontrak pergerakan yang diimplementasi oleh `Snake`
- **Inheritance** — `Snake` dan `Apple` mewarisi properti & metode dari `Entity`
- **Encapsulation** — semua atribut dilindungi dengan akses modifier yang tepat
- **Polymorphism** — metode `render()` di-*override* oleh masing-masing subclass

### 🖥️ Tampilan Terminal yang Responsif
- Tampilan arena di-*refresh* setiap *game tick* menggunakan ANSI escape codes
- Papan permainan berukuran **30×20** sel dengan border karakter
- Menu awal, layar Game Over, dan riwayat skor per sesi ditampilkan secara terstruktur

### 📊 Sistem Skor Real-Time
- Skor bertambah **+10 poin** setiap kali ular memakan apel
- **High Score** sesi tersimpan dan diperbarui secara otomatis
- **Riwayat skor** seluruh ronde dalam satu sesi ditampilkan di akhir permainan

### ✅ Unit Testing Otomatis (JUnit 5)
- **40+ test case** yang mencakup seluruh komponen inti permainan
- Pengujian meliputi: pergerakan ular, deteksi tabrakan, respawn apel, validasi input, dan hierarki OOP

---

## 🛠️ Tech Stack

| Teknologi | Versi | Kegunaan |
|-----------|-------|----------|
| ☕ **Java SE (JDK)** | 11+ | Bahasa pemrograman utama |
| 🧪 **JUnit 5** (Jupiter) | 5.x | Framework unit testing otomatis |
| 🔧 **IDE** | IntelliJ IDEA / Eclipse / VS Code | Lingkungan pengembangan |
| 🗂️ **Git** | 2.x | Version control & kolaborasi tim |

---

## 🚀 Panduan Instalasi & Menjalankan

### Prasyarat

Pastikan perangkat Anda sudah terinstal:
- ✅ [Java JDK 11 atau lebih baru](https://www.oracle.com/java/technologies/downloads/)
- ✅ IDE pilihan (IntelliJ IDEA, Eclipse, atau VS Code)
- ✅ Git

Verifikasi instalasi Java di terminal:
```bash
java -version
javac -version
```

---

### Langkah 1 — Clone Repositori

```bash
git clone https://github.com/username/SnakeGame.git
cd SnakeGame
```

---

### Langkah 2 — Buka di IDE

#### 🔵 IntelliJ IDEA
1. Buka IntelliJ IDEA → **File** → **Open**
2. Pilih folder `SnakeGame` → klik **OK**
3. IntelliJ akan otomatis mendeteksi proyek Java
4. Untuk JUnit 5: buka `Snaketest.java`, klik nama impor `org.junit.jupiter` yang bergaris merah → pilih **Add 'JUnit 5' to classpath**

#### 🟠 Eclipse
1. Buka Eclipse → **File** → **Import** → **Existing Projects into Workspace**
2. Pilih direktori `SnakeGame` → klik **Finish**
3. Untuk JUnit 5: klik kanan proyek → **Build Path** → **Add Libraries** → **JUnit** → **JUnit 5**

#### 🔷 VS Code
1. Buka VS Code → **File** → **Open Folder** → pilih `SnakeGame`
2. Pastikan ekstensi **Extension Pack for Java** sudah terpasang
3. Unduh `junit-platform-console-standalone-X.X.X.jar` dari [Maven Repository](https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone) dan tambahkan ke classpath

---

### Langkah 3 — Jalankan Aplikasi

#### Melalui IDE
1. Buka file `Main.java`
2. Klik tombol **▶ Run** atau tekan `Shift + F10` (IntelliJ) / `F11` (Eclipse)
3. Permainan akan berjalan di panel **Run / Terminal** IDE

#### Melalui Terminal / Command Prompt
```bash
# 1. Kompilasi semua file Java
javac *.java

# 2. Jalankan program
java Main
```

---

### Langkah 4 — Menjalankan Unit Test

#### Melalui IDE
- Buka `Snaketest.java`
- Klik kanan di dalam file → **Run 'SnakeTest'**
- Atau klik ikon ▶ di samping nama kelas/metode test

#### Melalui Terminal (dengan JUnit Console Standalone)
```bash
# Kompilasi dengan JUnit di classpath
javac -cp .:junit-platform-console-standalone.jar *.java

# Jalankan semua test
java -jar junit-platform-console-standalone.jar \
     --class-path . \
     --select-class SnakeTest
```

---

## 📁 Struktur Direktori

```
SnakeGame/
│
├── 📄 Main.java               # Entry point — menginisialisasi GameController
├── 📄 GameController.java     # Pengendali utama: game loop, skor, tabrakan
├── 📄 GameBoard.java          # Papan permainan: rendering arena & UI
│
├── 📄 Entity.java             # Abstract class — kelas induk Snake & Apple
├── 📄 Movable.java            # Interface — kontrak move() & changeDirection()
├── 📄 Snake.java              # Entitas ular: pergerakan, pertumbuhan, tabrakan
├── 📄 Apple.java              # Entitas apel: posisi acak & respawn
│
├── 📄 InputHandler.java       # Pembaca input keyboard dari pengguna
│
├── 📄 Snaketest.java          # Unit test JUnit 5 (40+ test case)
│
└── 📂 .git/                   # Direktori Git (version control)
```

### Penjelasan Singkat Setiap File

| File | Tanggung Jawab |
|------|---------------|
| `Main.java` | Titik masuk program, menangani error fatal |
| `GameController.java` | Koordinasi game loop, skor, high score, riwayat |
| `GameBoard.java` | Render grid, border, menu, dan layar Game Over |
| `Entity.java` | Properti dasar (`x`, `y`, `symbol`) + metode abstrak `render()` |
| `Movable.java` | Interface dengan kontrak `move()` dan `changeDirection()` |
| `Snake.java` | Logika gerak, makan apel, deteksi self-collision via `LinkedList` |
| `Apple.java` | Posisi acak dalam batas board menggunakan `Random` |
| `InputHandler.java` | Baca karakter dari `Scanner`, validasi arah, kelola sesi |
| `Snaketest.java` | Seluruh unit test menggunakan JUnit 5 Jupiter |

---

## 🧪 Cakupan Unit Test

Test suite `SnakeTest` mencakup pengujian terhadap:

- 🐍 **Snake** — panjang awal, posisi, pergerakan 4 arah, larangan balik 180°, pertumbuhan setelah makan apel, self-collision, `occupies()`
- 🍎 **Apple** — batas posisi, kemampuan respawn, simbol karakter
- 🗺️ **GameBoard** — deteksi tabrakan dinding (atas, bawah, kiri, kanan), posisi tengah aman, dimensi board
- ⌨️ **InputHandler** — validasi input arah valid (`W/A/S/D`) dan tidak valid
- 🏗️ **OOP** — `Snake` dapat di-cast ke `Movable` dan `Entity`, `Apple` dapat di-cast ke `Entity`, simbol kepala & tubuh ular

---

## 👥 Tim Pengembang

| Nama | NIM | Kontribusi |
|------|-----|-----------|
| *(Atha Aryasatya)* | *(254311024)* | *(Role 1: Class Architect (Fokus: Struktur OOP) )* |
| *(Nadhifia Talitha Putri)* | *(254311003)* | *(Role 2: Data & Logic Engineer (Fokus: Collections & CRUD) )* |
| *(Leo Rhamanata Adrio)* | *(254311012)* | *(Role 3: UI & Robustness Engineer (Fokus: Debugging & Form) )* |

---

## 📝 Lisensi

Proyek ini dibuat untuk keperluan akademik dan tidak untuk didistribusikan secara komersial.

---

<div align="center">
  <sub>🐍 Dibuat dengan ❤️ sebagai Final Project PBO — Teknologi Rekayasa Perangkat Lunak</sub>
</div>
