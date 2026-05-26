public class GameBoard {
    private int width;
    private int height;
    private char[][] grid;

    private static final char BORDER_CORNER = '+';
    private static final char BORDER_HORIZ = '-';
    private static final char BORDER_VERT = '|';
    private static final char EMPTY = ' ';

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
    }

    public void render(Snake snake, Apple apple, int score, int highScore) {
        clearGrid();
        placeEntities(snake, apple);
        printGrid(score, highScore);
    }

    private void clearGrid() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1) {
                    grid[row][col] = (col == 0 || col == width - 1) ? BORDER_CORNER : BORDER_HORIZ;
                } else if (col == 0 || col == width - 1) {
                    grid[row][col] = BORDER_VERT;
                } else {
                    grid[row][col] = EMPTY;
                }
            }
        }
    }

    private void placeEntities(Snake snake, Apple apple) {
        int ax = apple.getX();
        int ay = apple.getY();
        if (inBounds(ax, ay)) {
            grid[ay][ax] = apple.render();
        }

        for (int[] segment : snake.getBody()) {
            int sx = segment[0];
            int sy = segment[1];
            if (inBounds(sx, sy)) {
                grid[sy][sx] = snake.getBodySymbol();
            }
        }

        int hx = snake.getX();
        int hy = snake.getY();
        if (inBounds(hx, hy)) {
            grid[hy][hx] = snake.render();
        }
    }

    private void printGrid(int score, int highScore) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("╔══════════════════════════╗");
        System.out.println("║       SNAKE  GAME        ║");
        System.out.println("╚══════════════════════════╝");
        System.out.printf("  Score: %-5d  High Score: %d%n", score, highScore);
        System.out.println();

        for (int row = 0; row < height; row++) {
            System.out.print("  ");
            for (int col = 0; col < width; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("  Kontrol: W=Atas  S=Bawah  A=Kiri  D=Kanan  Q=Quit");
    }

    private boolean inBounds(int x, int y) {
        return x > 0 && x < width - 1 && y > 0 && y < height - 1;
    }

    public boolean isWallCollision(int x, int y) {
        return x <= 0 || x >= width - 1 || y <= 0 || y >= height - 1;
    }

    public void renderGameOver(int score, int highScore) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("  ╔══════════════════════════════╗");
        System.out.println("  ║         GAME  OVER           ║");
        System.out.println("  ╚══════════════════════════════╝");
        System.out.printf("  Skor Akhir  : %d%n", score);
        System.out.printf("  High Score  : %d%n", highScore);
        System.out.println();
        System.out.println("  Tekan ENTER untuk main lagi, Q+ENTER untuk keluar.");
    }

    public void renderMenu() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println("  ╔════════════════════════════════════╗");
        System.out.println("  ║          SNAKE  GAME  OOP          ║");
        System.out.println("  ║     Final Project PBO - Java       ║");
        System.out.println("  ╠════════════════════════════════════╣");
        System.out.println("  ║  Kontrol:                          ║");
        System.out.println("  ║    W / S / A / D  = Gerak Ular     ║");
        System.out.println("  ║    Q              = Keluar Game     ║");
        System.out.println("  ║                                    ║");
        System.out.println("  ║  Ular makan @ = bertambah panjang  ║");
        System.out.println("  ║  Nabrak dinding/tubuh = Game Over  ║");
        System.out.println("  ╠════════════════════════════════════╣");
        System.out.println("  ║  Tekan ENTER untuk mulai...        ║");
        System.out.println("  ╚════════════════════════════════════╝");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
