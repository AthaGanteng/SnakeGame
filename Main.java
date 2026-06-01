public class Main {
    public static void main(String[] args) {
        System.out.println("  Memulai Snake Game...");
        
        try {
            GameController controller = new GameController();
            controller.startGame();
        } catch (Exception e) {
            System.err.println("[FATAL ERROR] Program berhenti tidak terduga:");
            System.err.println("  " + e.getMessage());
            e.printStackTrace();
        }
    }
}
