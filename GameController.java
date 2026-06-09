import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class GameController {
    private Snake snake;
    private Apple apple;
    private GameBoard board;
    private InputHandler inputHandler;

    private int score;
    private int highScore;
    private boolean running;
    private boolean gameOver;

    private ArrayList<Integer> scoreHistory;
    private ArrayList<Apple> specialApples;

    private static final int BOARD_WIDTH  = 30;
    private static final int BOARD_HEIGHT = 20;
    private static final int GAME_SPEED_MS = 200;
    private static final int SCORE_PER_APPLE = 10;

    public GameController() {
        this.board        = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);
        this.inputHandler = new InputHandler();
        this.scoreHistory = new ArrayList<>();
        this.specialApples = new ArrayList<>();
        this.highScore    = 0;
    }

    public void sortScoresDescending() {
    Collections.sort(scoreHistory);
    Collections.reverse(scoreHistory);
    }

    public void startGame() {
        board.renderMenu();
        inputHandler.waitEnter();
        System.out.println("\n=== UPDATE 2.0 ===");
        System.out.println("1. Main Normal");
        System.out.println("2. Tambah Golden Apple");
        System.out.println("3. Lihat Golden Apple");
        System.out.print("Pilih: ");

        char pilihan = inputHandler.readInput();

        if (pilihan == '2') {
            setupGoldenApple();
        }
        if (pilihan == '3') {
        showSpecialApples();
}

        boolean keepPlaying = true;
        while (keepPlaying) {
            initNewGame();
            runGameLoop();
            board.renderGameOver(score, highScore);
            keepPlaying = inputHandler.askPlayAgain();
        }

        sortScoresDescending();
        printScoreHistory();
        System.out.println("\n  Terima kasih sudah bermain! Sampai jumpa 🐍");
    }

    private void initNewGame() {
        int startX = BOARD_WIDTH / 5;
        int startY = BOARD_HEIGHT / 5;

        this.snake    = new Snake(startX, startY);
        this.apple    = new Apple(BOARD_WIDTH, BOARD_HEIGHT);
        this.score    = 0;
        this.running  = true;
        this.gameOver = false;

        respawnAppleSafely();
    }

    private void runGameLoop() {
        Thread inputThread = new Thread(() -> {
            while (running) {
                char input = inputHandler.readInput();
                if (!InputHandler.isValidDirection(input)) {
                    System.out.println("  Input tidak valid. Gunakan W/A/S/D untuk arah atau Q untuk keluar.");
                }
                if (input == 'Q' || input == 'q') {
                    running = false;
                    gameOver = true;
                } else {
                    snake.changeDirection(input);
                }
            }
        });
        inputThread.setDaemon(true);
        inputThread.start();

        while (running) {
            snake.move();
            checkCollisions();

            if (running) {
                board.render(snake, apple, score, highScore);
            }

            try {
                Thread.sleep(GAME_SPEED_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                running = false;
            }
        }

        scoreHistory.add(score);
        if (score > highScore) {
            highScore = score;
        }
    }

    private void checkCollisions() {
        int headX = snake.getX();
        int headY = snake.getY();

        if (board.isWallCollision(headX, headY)) {
            snake.setAlive(false);
            running = false;
            return;
        }

        if (snake.isSelfCollision()) {
            snake.setAlive(false);
            running = false;
            return;
        }

        if (headX == apple.getX() && headY == apple.getY()) {
            snake.eatApple();
            score += SCORE_PER_APPLE;
            respawnAppleSafely();
        }
    }

    private void respawnAppleSafely() {
        do {
            apple.respawn(BOARD_WIDTH, BOARD_HEIGHT);
        } while (snake.occupies(apple.getX(), apple.getY()));
    }

    private void printScoreHistory() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     RIWAYAT SKOR         ║");
        System.out.println("  ╚══════════════════════════╝");
        if (scoreHistory.isEmpty()) {
            System.out.println("  Tidak ada skor tersimpan.");
        } else {
            for (int i = 0; i < scoreHistory.size(); i++) {
                System.out.printf("  Game %d : %d poin%n", i + 1, scoreHistory.get(i));
            }
        }
        System.out.printf("%n  High Score Sesi: %d poin%n", highScore);
    }

    public void addGoldenApple(
        int bonusScore) {

    GoldenApple goldenApple =
        new GoldenApple(
            BOARD_WIDTH,
            BOARD_HEIGHT,
            bonusScore
        );

    specialApples.add(goldenApple);
    }

private void setupGoldenApple() {
    try {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan bonus score Golden Apple: ");
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
            throw new InputTidakValidException(
                    "Bonus score tidak boleh kosong!");
        }
        int bonusScore = Integer.parseInt(input);
        GoldenApple goldenApple =
                new GoldenApple(
                        BOARD_WIDTH,
                        BOARD_HEIGHT,
                        bonusScore);
        specialApples.add(goldenApple);
        System.out.println(
                "Golden Apple berhasil ditambahkan!");
    }
    catch (InputTidakValidException e) {
        System.out.println(
                "Error: " + e.getMessage());
    }
    catch (NumberFormatException e) {
        System.out.println(
                "Bonus score harus berupa angka!");
    }
    }
    public void showSpecialApples() {
    if (specialApples.isEmpty()) {
        System.out.println("Belum ada Golden Apple.");
        return;
    }
    System.out.println("=== DAFTAR GOLDEN APPLE ===");
    for (Apple apple : specialApples) {
        System.out.println(apple);
    }
    }

    public int getScore()       { return score; }
    public int getHighScore()   { return highScore; }
    public Snake getSnake()     { return snake; }
    public Apple getApple()     { return apple; }
    public ArrayList<Integer> getScoreHistory() { return scoreHistory; }
    public GameBoard getBoard() { return board; }
}