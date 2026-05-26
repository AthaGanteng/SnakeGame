import java.util.LinkedList;

/**
 * Class Snake - extends Entity, implements Movable
 * Representasi ular yang dikendalikan pemain.
 * Tubuh ular disimpan dalam LinkedList of int[]{x, y}.
 * Menerapkan Polymorphism melalui interface Movable.
 *
 * Role 1 - Class Architect
 */
public class Snake extends Entity implements Movable {
    private static final char HEAD_SYMBOL = 'O';
    private static final char BODY_SYMBOL = 'o';

    private LinkedList<int[]> body;  // Collections - LinkedList
    private char direction;          // 'W', 'A', 'S', 'D'
    private boolean alive;
    private boolean ateApple;

    public Snake(int startX, int startY) {
        super(startX, startY, HEAD_SYMBOL);
        this.body = new LinkedList<>();
        this.direction = 'D'; // default gerak ke kanan
        this.alive = true;
        this.ateApple = false;

        // Inisialisasi body awal (kepala + 2 segmen)
        body.addFirst(new int[]{startX, startY});
        body.addLast(new int[]{startX - 1, startY});
        body.addLast(new int[]{startX - 2, startY});
    }

    /**
     * Gerakkan ular sesuai arah saat ini
     * Polymorphism: implementasi move() dari interface Movable
     */
    @Override
    public void move() {
        int[] head = body.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (direction) {
            case 'W': newY--; break;
            case 'S': newY++; break;
            case 'A': newX--; break;
            case 'D': newX++; break;
        }

        // Tambah kepala baru
        body.addFirst(new int[]{newX, newY});

        // Update posisi kepala di superclass
        this.x = newX;
        this.y = newY;

        // Hapus ekor jika tidak makan apel
        if (!ateApple) {
            body.removeLast();
        } else {
            ateApple = false; // reset flag
        }
    }

    /**
     * Ganti arah - tidak boleh balik arah 180 derajat
     * Polymorphism: implementasi changeDirection() dari interface Movable
     */
    @Override
    public void changeDirection(char newDir) {
        boolean invalid =
            (newDir == 'W' && direction == 'S') ||
            (newDir == 'S' && direction == 'W') ||
            (newDir == 'A' && direction == 'D') ||
            (newDir == 'D' && direction == 'A');

        if (!invalid) {
            this.direction = newDir;
        }
    }

    /**
     * Tandai ular sudah makan apel, tubuh akan bertambah
     */
    public void eatApple() {
        this.ateApple = true;
    }

    /**
     * Cek apakah kepala menabrak tubuh sendiri
     */
    public boolean isSelfCollision() {
        int[] head = body.getFirst();
        for (int i = 1; i < body.size(); i++) {
            int[] segment = body.get(i);
            if (head[0] == segment[0] && head[1] == segment[1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Cek apakah posisi (x, y) adalah bagian dari tubuh ular
     */
    public boolean occupies(int x, int y) {
        for (int[] segment : body) {
            if (segment[0] == x && segment[1] == y) return true;
        }
        return false;
    }

    // Getter
    public LinkedList<int[]> getBody() { return body; }
    public int getLength() { return body.size(); }
    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }
    public char getDirection() { return direction; }

    @Override
    public char render() {
        return HEAD_SYMBOL;
    }

    public char getBodySymbol() {
        return BODY_SYMBOL;
    }
}