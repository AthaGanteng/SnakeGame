public class SuperApple extends Apple {
    
    private final int bonus; 

    public SuperApple(int boardWidth, int boardHeight, int bonus) {
        super(boardWidth, boardHeight);
        this.bonus = bonus; 
    }

    public int getBonus() {
        return this.bonus;
    }

    @Override
    public char render() {
        return '?'; 
    }
}