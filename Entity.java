/**
 * Abstract Class Entity
 * Superclass untuk semua objek yang ada di dalam board game.
 * Menerapkan Encapsulation dengan getter/setter.
 *
 * Role 1 - Class Architect
 */
public abstract class Entity {
    protected int x;
    protected int y;
    protected char symbol;

    public Entity(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    // Getter & Setter (Encapsulation)
    public int getX() { return x; }
    public int getY() { return y; }
    public char getSymbol() { return symbol; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setSymbol(char symbol) { this.symbol = symbol; }

    /**
     * Abstract method render - setiap entity wajib punya representasi karakter
     */
    public abstract char render();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " at (" + x + ", " + y + ")";
    }
}