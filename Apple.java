import java.util.Random;
public class Apple extends Entity {
    private static final char APPLE_SYMBOL = '@';
    private Random random;

    public Apple(int boardWidth, int boardHeight) {
        super(0, 0, APPLE_SYMBOL);
        this.random = new Random();
        respawn(boardWidth, boardHeight);
    }


    public void respawn(int boardWidth, int boardHeight) {
  
        this.x = 1 + random.nextInt(boardWidth - 2);
        this.y = 1 + random.nextInt(boardHeight - 2);
    }

    @Override
    public char render() {
        return APPLE_SYMBOL;
    }
}