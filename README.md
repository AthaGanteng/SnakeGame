# Update Versi 2.0

Pada versi 2.0, aplikasi Snake Game mendapatkan penambahan fitur GoldenApple sebagai entitas spesial yang merupakan turunan (subclass) dari class Apple. GoldenApple memiliki atribut unik berupa bonusScore yang menerapkan konsep Encapsulation melalui penggunaan atribut private serta Getter dan Setter. Selain itu, GoldenApple juga menerapkan Polymorphism dengan melakukan override pada method render() sehingga memiliki karakter tampilan yang berbeda dari Apple biasa.
Selain penambahan entitas baru, versi 2.0 juga menambahkan penggunaan Collections berupa ArrayList untuk menyimpan objek GoldenApple, fitur pengurutan (sorting) riwayat skor menggunakan Collections.sort(), serta validasi input menggunakan Custom Exception InputTidakValidException. Fitur ini bertujuan meningkatkan robustnes aplikasi sehingga kesalahan input dari pengguna dapat ditangani tanpa menyebabkan program crash.

masih ada sedikit bug, karena waktunya kurang banyak ✌✌
