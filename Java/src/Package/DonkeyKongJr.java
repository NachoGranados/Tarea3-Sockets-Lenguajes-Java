package Package;

public class DonkeyKongJr {

    private int positionX;
    private int positionY;
    private int lifes;
    private int score;


    public DonkeyKongJr(int positionX, int positionY, int lifes, int score) {

        this.positionX = positionX;
        this.positionY = positionY;
        this.lifes = lifes;
        this.score = score;

    }

    public int getPositionX() {

        return positionX;

    }

    public void setPositionX(int positionX) {

        this.positionX = positionX;

    }

    public int getPositionY() {

        return positionY;

    }

    public void setPositionY(int positionY) {

        this.positionY = positionY;

    }

    public int getLifes() {

        return lifes;

    }

    public void setLifes(int lifes) {

        this.lifes = lifes;

    }

    public int getScore() {

        return score;

    }

    public void setScore(int score) {

        this.score = score;

    }

}
