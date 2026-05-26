import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {

    private Snake snake;
    private Apple apple;
    private GameBoard board;

    @BeforeEach
    public void setUp() {
        snake = new Snake(10, 10);
        apple = new Apple(30, 20);
        board = new GameBoard(30, 20);
    }


    @Test
    public void testSnakeInitialLength() {
        assertEquals(3, snake.getLength(), "Panjang awal ular harus 3");
    }

    @Test
    public void testSnakeInitialPosition() {
        assertEquals(10, snake.getX());
        assertEquals(10, snake.getY());
    }

    @Test
    public void testSnakeIsAliveInitially() {
        assertTrue(snake.isAlive(), "Ular harus alive saat inisialisasi");
    }

    @Test
    public void testSnakeMoveRight() {
        int initialX = snake.getX();
        snake.move();
        assertEquals(initialX + 1, snake.getX(), "Ular harus bergerak ke kanan");
    }

    @Test
    public void testSnakeMoveUp() {
        snake.changeDirection('W');
        int initialY = snake.getY();
        snake.move();
        assertEquals(initialY - 1, snake.getY(), "Ular harus bergerak ke atas (Y berkurang)");
    }

    @Test
    public void testSnakeMoveDown() {
        snake.changeDirection('S');
        int initialY = snake.getY();
        snake.move();
        assertEquals(initialY + 1, snake.getY(), "Ular harus bergerak ke bawah (Y bertambah)");
    }

    @Test
    public void testSnakeMoveLeft() {
        snake.changeDirection('W');
        snake.move();
        snake.changeDirection('A');
        int xBeforeLeft = snake.getX();
        snake.move();
        assertEquals(xBeforeLeft - 1, snake.getX(), "Ular harus bergerak ke kiri");
    }

    @Test
    public void testSnakeCannotReverse() {
        snake.changeDirection('A');
        assertEquals('D', snake.getDirection(), "Ular tidak boleh berbalik arah 180 derajat");
    }

    @Test
    public void testSnakeGrowsAfterEatingApple() {
        int initialLength = snake.getLength();
        snake.eatApple();
        snake.move();
        assertEquals(initialLength + 1, snake.getLength(), "Panjang ular harus bertambah setelah makan apel");
    }

    @Test
    public void testSnakeNoSelfCollisionInitially() {
        assertFalse(snake.isSelfCollision(), "Tidak boleh ada self-collision di awal");
    }
    
    @Test
    public void testSnakeOccupiesStartPosition() {
        assertTrue(snake.occupies(10, 10), "Ular harus occupies posisi kepalanya");
    }

    @Test
    public void testSnakeDoesNotOccupyRandomPosition() {
        assertFalse(snake.occupies(0, 0), "Ular tidak boleh occupies posisi yang tidak ditempatinya");
    }


    @Test
    public void testAppleWithinBounds() {
        assertTrue(apple.getX() > 0 && apple.getX() < 29, "Apple X harus dalam batas board");
        assertTrue(apple.getY() > 0 && apple.getY() < 19, "Apple Y harus dalam batas board");
    }

    @Test
    public void testAppleRespawn() {
        int oldX = apple.getX();
        int oldY = apple.getY();
        boolean changed = false;
        for (int i = 0; i < 20; i++) {
            apple.respawn(30, 20);
            if (apple.getX() != oldX || apple.getY() != oldY) {
                changed = true;
                break;
            }
        }
        assertTrue(changed, "Apple harus bisa respawn ke posisi berbeda");
    }

    @Test
    public void testAppleSymbol() {
        assertEquals('@', apple.render(), "Simbol apple harus '@'");
    }


    @Test
    public void testWallCollisionLeft() {
        assertTrue(board.isWallCollision(0, 5), "X=0 adalah dinding kiri");
    }

    @Test
    public void testWallCollisionRight() {
        assertTrue(board.isWallCollision(29, 5), "X=29 adalah dinding kanan (width-1)");
    }

    @Test
    public void testWallCollisionTop() {
        assertTrue(board.isWallCollision(5, 0), "Y=0 adalah dinding atas");
    }

    @Test
    public void testWallCollisionBottom() {
        assertTrue(board.isWallCollision(5, 19), "Y=19 adalah dinding bawah (height-1)");
    }

    @Test
    public void testNoWallCollisionCenter() {
        assertFalse(board.isWallCollision(15, 10), "Posisi tengah tidak boleh dianggap dinding");
    }

    @Test
    public void testBoardDimensions() {
        assertEquals(30, board.getWidth());
        assertEquals(20, board.getHeight());
    }


    @Test
    public void testValidDirectionW() {
        assertTrue(InputHandler.isValidDirection('W'));
        assertTrue(InputHandler.isValidDirection('w'));
    }

    @Test
    public void testValidDirectionA() {
        assertTrue(InputHandler.isValidDirection('A'));
        assertTrue(InputHandler.isValidDirection('a'));
    }

    @Test
    public void testValidDirectionS() {
        assertTrue(InputHandler.isValidDirection('S'));
        assertTrue(InputHandler.isValidDirection('s'));
    }

    @Test
    public void testValidDirectionD() {
        assertTrue(InputHandler.isValidDirection('D'));
        assertTrue(InputHandler.isValidDirection('d'));
    }

    @Test
    public void testInvalidDirectionX() {
        assertFalse(InputHandler.isValidDirection('X'), "X bukan arah yang valid");
    }

    @Test
    public void testInvalidDirectionNumber() {
        assertFalse(InputHandler.isValidDirection('5'), "'5' bukan arah yang valid");
    }


    @Test
    public void testSnakeIsMovable() {
        Movable movable = snake;
        assertNotNull(movable, "Snake harus bisa di-cast ke interface Movable");
    }

    @Test
    public void testSnakeIsEntity() {
        Entity entity = snake;
        assertNotNull(entity, "Snake harus bisa di-cast ke abstract class Entity");
    }

    @Test
    public void testAppleIsEntity() {
        Entity entity = apple;
        assertNotNull(entity, "Apple harus bisa di-cast ke abstract class Entity");
    }

    @Test
    public void testSnakeHeadSymbol() {
        assertEquals('O', snake.render(), "Simbol kepala ular harus 'O'");
    }

    @Test
    public void testSnakeBodySymbol() {
        assertEquals('o', snake.getBodySymbol(), "Simbol body ular harus 'o'");
    }
}
