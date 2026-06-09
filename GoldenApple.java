public class GoldenApple extends Apple {

    private int bonusScore;

    public GoldenApple(
        int boardWidth,
        int boardHeight,
        int bonusScore) {

        super(boardWidth, boardHeight);
        this.bonusScore = bonusScore;
    }

    public int getBonusScore() {
        return bonusScore;
    }

    public void setBonusScore(int bonusScore) {
        this.bonusScore = bonusScore;
    }

    @Override
    public char render() {
        return '$';
    }

    @Override
    public String toString() {
        return "GoldenApple | Bonus Score = "
                + bonusScore;
    }
}