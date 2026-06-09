public class GoldenApple extends Apple {
    
    private int bonusMultiplier;

    public GoldenApple(int boardWidth, int boardHeight) {
        super(boardWidth, boardHeight);
        this.bonusMultiplier = 2;
    }

    public int getBonusMultiplier() {
        return bonusMultiplier;
    }

    public void setBonusMultiplier(int bonusMultiplier) {
        this.bonusMultiplier = bonusMultiplier;
    }

    @Override
    public char render() {
        return '$';
    }

    @Override
    public String toString() {
        return "GoldenApple [posisi=(" + getX() + "," + getY() + "), multiplier=" + bonusMultiplier + "]";
    }
}