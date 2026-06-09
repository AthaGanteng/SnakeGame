import java.util.ArrayList;
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
    
   
    private int applesEatenCount = 0; 
    private int customBonus = 5; // Nilai default jika user tidak mengubahnya

    private static final int BOARD_WIDTH  = 30;
    private static final int BOARD_HEIGHT = 20;
    private static final int GAME_SPEED_MS = 200;
    private static final int SCORE_PER_APPLE = 10;

    public GameController() {
        this.board        = new GameBoard(BOARD_WIDTH, BOARD_HEIGHT);
        this.inputHandler = new InputHandler();
        this.scoreHistory = new ArrayList<>();
        this.highScore    = 0;
    }

    public void startGame() {
        board.renderMenu();
        inputHandler.waitEnter();

        
        askCustomBonusInput();

        boolean keepPlaying = true;
        while (keepPlaying) {
            initNewGame();
            runGameLoop();
            board.renderGameOver(score, highScore);
            
          
            keepPlaying = handleMenuInput();
        }

        printScoreHistory();
        System.out.println("\n  Terima kasih sudah bermain! Sampai jumpa 🐍");
    }

  
    private void askCustomBonusInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\n  ┌────────────────────────────────────────────────────────┐");
                System.out.println("  │   [PENGATURAN GAME V2.0 - TEMA 2]                      │");
                System.out.print("  │   Masukkan Bonus Size Apel Super (Rekomendasi: 2 - 5): ");
                String input = scanner.nextLine().trim();


                if (input.isEmpty()) {
                    throw new InvalidInputException("Input tidak boleh kosong! Mohon masukkan angka.");
                }

            
                int bonus;
                try {
                    bonus = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new InvalidInputException("Tipe data salah! Input harus berupa angka bulat (integer).");
                }

           
                if (bonus <= 0) {
                    throw new InvalidInputException("Bonus ukuran tubuh ular harus lebih besar dari 0!");
                }
                if (bonus > 5) {
                    throw new InvalidInputException("Bonus terlalu besar! Maksimal 5 agar tidak langsung menabrak dinding.");
                }

             
                this.customBonus = bonus;
                System.out.printf("  │   => BERHASIL: Bonus size Apel Super di-set ke: %d 🎮   │%n", this.customBonus);
                System.out.println("  └────────────────────────────────────────────────────────┘\n");
                break; // Keluar dari loop input

            } catch (InvalidInputException e) {
     
                System.out.println("  │   [ERROR] " + e.getMessage());
                System.out.println("  │   Silakan coba masukkan kembali dengan benar.");
                System.out.println("  └────────────────────────────────────────────────────────┘");
            }
        }
    }

   
    private boolean handleMenuInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("  Pilihan (ENTER = Main lagi, Q = Keluar, H = Lihat Riwayat): ");
                String input = scanner.nextLine().trim().toUpperCase();

                if (input.equals("Q")) {
                    return false;
                } else if (input.equals("")) {
                    return true;
                } else if (input.equals("H")) {
                    printScoreHistory(); 
                } else {
                    throw new InvalidInputException("Input tidak dikenali! Masukkan ENTER, Q, atau H.");
                }
            } catch (InvalidInputException e) {
                System.out.println("  [ERROR] " + e.getMessage() + "\n");
            }
        }
    }

    private void initNewGame() {
        int startX = BOARD_WIDTH / 2;
        int startY = BOARD_HEIGHT / 2;

        this.snake    = new Snake(startX, startY);
        this.apple    = new Apple(BOARD_WIDTH, BOARD_HEIGHT);
        this.score    = 0;
        this.applesEatenCount = 0; 
        this.running  = true;
        this.gameOver = false;

        respawnAppleSafely();
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

        // Logika Interaksi ketika Kepala Ular menabrak koordinat Apel
        if (headX == apple.getX() && headY == apple.getY()) {
            applesEatenCount++;

            
            if (apple instanceof SuperApple) {
                SuperApple superApple = (SuperApple) apple;
                
              
                for (int i = 0; i < superApple.getBonus(); i++) {
                    snake.eatApple();
                }
                score += (SCORE_PER_APPLE * 3); 
            } else {
                snake.eatApple();
                score += SCORE_PER_APPLE;
            }

            respawnAppleSafely();
        }
    }

    private void respawnAppleSafely() {
    do {
   
        if (applesEatenCount > 0 && applesEatenCount % 2 == 0) {
            this.apple = new SuperApple(BOARD_WIDTH, BOARD_HEIGHT, this.customBonus);
        } else {
            this.apple = new Apple(BOARD_WIDTH, BOARD_HEIGHT);
        }
        
       
        this.apple.respawn(BOARD_WIDTH, BOARD_HEIGHT);

    } while (snake.occupies(apple.getX(), apple.getY()));
}

    private void printScoreHistory() {
        System.out.println("\n  ╔══════════════════════════╗");
        System.out.println("  ║     RIWAYAT SKOR         ║");
        System.out.println("  ╚══════════════════════════╝");
        if (scoreHistory.isEmpty()) {
            System.out.println("  Tidak ada skor tersimpan.");
        } else {
            // Menampilkan data dari Collections (Soal B.a)
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
    public ArrayList<Integer> getScoreHistory() { return scoreHistory; }
    public GameBoard getBoard() { return board; }
}