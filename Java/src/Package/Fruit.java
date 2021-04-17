package Package;

public class Fruit {

    private String type;
    private int value;
    private int positionX;
    private int positionY;


    public Fruit(String type, int value, int positionX, int positionY) {

        this.type = type;
        this.value = value;
        this.positionX = positionX;
        this.positionY = positionY;

    }

    public String getType() {

        return type;

    }

    public void setType(String type) {

        this.type = type;

    }

    public int getValue() {

        return value;

    }

    public void setValue(int value) {

        this.value = value;

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

}
