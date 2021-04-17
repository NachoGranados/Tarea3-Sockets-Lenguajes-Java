package Package;

public class Alligator {

    private String color;
    private int positionX;
    private int positionY;
    private int speed;

    public Alligator(String color, int positionX, int positionY, int speed) {

        this.color = color;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;

    }

    public String getColor() {

        return color;

    }

    public void setColor(String color) {

        this.color = color;

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

    public int getSpeed() {

        return speed;

    }

    public void setSpeed(int speed) {

        this.speed = speed;

    }

}
