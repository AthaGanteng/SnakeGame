import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Snake snake;
    private Apple apple;
    private GoldenApple goldenApple;
    private GameBoard board;
    private InputHandler inputHandler;

    private int score;
    private int highScore;
    private boolean running;
    private boolean gameOver;

    private ArrayList<Integer> scoreHistory;
    private ArrayList<Apple> appleList; 

    private static final int BOARD_WIDTH  = 30;
    private static final int BOARD_HEIGHT = 20;
    private static final int GAME_SPEED_MS = 200;
    private static final int SCORE_PER_APPLE = 10;

    public GameController() {
        this.board        = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);
        this.inputHandler = new InputHandler();
        this.scoreHistory = new ArrayList<>();
        this.appleList    = new ArrayList<>();
        this.highScore    = 0;
    }

    public void startGame() {
        board.renderMenu();
        inputHandler.waitEnter();
        askTargetScore();

        boolean keepPlaying = true;
        while (keepPlaying) {
            initNewGame();
            runGameLoop();
            board.renderGameOver(score, highScore);
            keepPlaying = inputHandler.askPlayAgain();
        }

        printScoreHistory();
        printGoldenAppleList();
        System.out.println("\n  Terima kasih sudah bermain! Sampai jumpa 🐍");
    }
    private void askTargetScore() {
    System.out.println("\n  Masukkan target skor yang ingin dicapai (kelipatan 10, min 10):");
    System.out.print("  >> ");
    try {
        String input = new java.util.Scanner(System.in).nextLine().trim();
        int target = Integer.parseInt(input);
        if (target < 10 || target % 10 != 0) {
            throw new InvalidScoreException("Target skor harus kelipatan 10 dan minimal 10!");
        }
        System.out.println("  Target skor: " + target + " poin. Semangat!");
        Thread.sleep(1500);
    } catch (NumberFormatException e) {
        System.out.println("  [ERROR] Input harus berupa angka! Target diabaikan.");
    } catch (InvalidScoreException e) {
        System.out.println("  [ERROR] " + e.getMessage() + " Target diabaikan.");
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
    private void initNewGame() {
        int startX = BOARD_WIDTH / 2;
        int startY = BOARD_HEIGHT / 2;

        this.snake       = new Snake(startX, startY);
        this.apple       = new Apple(BOARD_WIDTH, BOARD_HEIGHT);
        this.goldenApple = new GoldenApple(BOARD_WIDTH, BOARD_HEIGHT);
        this.score       = 0;
        this.running     = true;
        this.gameOver    = false;

        appleList.add(apple);
        appleList.add(goldenApple);

        respawnAppleSafely();
        respawnGoldenAppleSafely();
    }

    private void runGameLoop() {
        Thread inputThread = new Thread(() -> {
            while (running) {
                char input = inputHandler.readInput();
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
                board.render(snake, apple, goldenApple, score, highScore);
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

        if (headX == goldenApple.getX() && headY == goldenApple.getY()) {
            snake.eatApple();
            score += SCORE_PER_APPLE * goldenApple.getBonusMultiplier();
            respawnGoldenAppleSafely();
        }
    }

    private void respawnAppleSafely() {
        do {
            apple.respawn(BOARD_WIDTH, BOARD_HEIGHT);
        } while (snake.occupies(apple.getX(), apple.getY()));
    }

    private void respawnGoldenAppleSafely() {
        do {
            goldenApple.respawn(BOARD_WIDTH, BOARD_HEIGHT);
        } while (snake.occupies(goldenApple.getX(), goldenApple.getY()));
    }

    public void printGoldenAppleList() {
        System.out.println("\n  ╔══════════════════════════════╗");
        System.out.println("  ║   DAFTAR GOLDEN APPLE        ║");
        System.out.println("  ╚══════════════════════════════╝");
        boolean found = false;
        for (Apple a : appleList) {
            if (a instanceof GoldenApple) {
                System.out.println("  " + a.toString());
                found = true;
            }
        }
        if (!found) System.out.println("  Tidak ada Golden Apple.");
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

    public int getScore()       { return score; }
    public int getHighScore()   { return highScore; }
    public Snake getSnake()     { return snake; }
    public Apple getApple()     { return apple; }
    public GoldenApple getGoldenApple() { return goldenApple; }
    public ArrayList<Integer> getScoreHistory() { return scoreHistory; }
    public ArrayList<Apple> getAppleList() { return appleList; }
    public GameBoard getBoard() { return board; }
}