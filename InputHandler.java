import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public char readInput() {
        try {
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    return Character.toUpperCase(line.charAt(0));
                }
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error membaca input: " + e.getMessage());
        }
        return '\0';
    }

    public void waitEnter() {
        try {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.err.println("[InputHandler] Error waitEnter: " + e.getMessage());
        }
    }

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

    public static boolean isValidDirection(char input) {
        char upper = Character.toUpperCase(input);
        return upper == 'W' || upper == 'A' || upper == 'S' || upper == 'D';
    }

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
