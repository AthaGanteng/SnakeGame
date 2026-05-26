/**
 * Class Main - Entry Point Program
 * Menjalankan Snake Game.
 *
 * Role 3 - UI & Robustness Engineer
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("  Memulai Snake Game...");

        try {
            GameController controller = new GameController();
            controller.startGame();
        } catch (Exception e) {
            // Top-level exception handler - mencegah crash yang tidak terduga
            System.err.println("[FATAL ERROR] Program berhenti tidak terduga:");
            System.err.println("  " + e.getMessage());
            e.printStackTrace();
        }
    }
}