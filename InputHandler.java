import java.util.Scanner;

/**
 * Class InputHandler
 * Menangani semua input dari user lewat Scanner.
 * Menerapkan try-catch untuk mencegah crash saat input tidak valid.
 * Memastikan aplikasi Robust (tidak crash karena input sembarangan).
 *
 * Role 3 - UI & Robustness Engineer
 */
public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Baca satu karakter input dari user.
     * try-catch memastikan input tidak valid tidak menyebabkan crash.
     * 
     * @return karakter yang dimasukkan user (uppercase), atau '\0' jika tidak valid
     */
    public char readInput() {
        try {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    return Character.toUpperCase(line.charAt(0));
                }
            }
        } catch (Exception e) {
            // Tangani semua exception agar game tidak crash
            System.err.println("[InputHandler] Error membaca input: " + e.getMessage());
        }
        return '\0'; // karakter null jika input kosong atau error
    }

    /**
     * Tunggu user menekan ENTER (untuk menu / pause).
     * try-catch menangani jika Scanner bermasalah.
     */
    public void waitEnter() {
        try {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error waitEnter: " + e.getMessage());
        }
    }

    /**
     * Tanya user apakah ingin main lagi setelah Game Over.
     * Validasi input: hanya 'Q' yang dianggap berhenti, selain itu main lagi.
     * 
     * @return true jika ingin lanjut bermain, false jika quit
     */
    public boolean askPlayAgain() {
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim().toUpperCase();
                return !input.equals("Q");
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error askPlayAgain: " + e.getMessage());
        }
        return false;
    }

    /**
     * Validasi input arah - hanya W, A, S, D yang valid.
     * 
     * @param input karakter yang dimasukkan
     * @return true jika valid
     */
    public static boolean isValidDirection(char input) {
        char upper = Character.toUpperCase(input);
        return upper == 'W' || upper == 'A' || upper == 'S' || upper == 'D';
    }

    /**
     * Tutup scanner saat program selesai
     */
    public void close() {
        try {
            if (scanner != null) {
                scanner.close();
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error menutup scanner: " + e.getMessage());
        }
    }
}