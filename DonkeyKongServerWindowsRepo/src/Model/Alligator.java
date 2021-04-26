package Model;

public class Alligator extends ObjectFactory {

    // Attributes for Alligator instance
    private String color;
    private Integer speed;
    private String direction;

    /**
     * Description: constructor method.
     * @param color
     * @param positionX
     * @param positionY
     * @param speed
     */
    public Alligator(String color, Integer positionX, Integer positionY, Integer speed) {

        super(positionX, positionY);

        this.color = color;
        this.speed = speed;
        this.direction = "DOWN";

    }

    /**
     * Description: returns color of alligator.
     */
    public String getColor() {

        return color;

    }

    /**
     * Description: returns speed of alligator.
     */
    public Integer getSpeed() {

        return speed;

    }

    /**
     * Description: sets speed to alligator.
     * @param speed
     */
    public void setSpeed(Integer speed) {

        this.speed += speed;

    }

    /**
     * Description: returns direction of alligator.
     */
    public String getDirection() {

        return direction;

    }

    /**
     * Description: sets direction to alligator.
     * @param direction
     */
    public void setDirection(String direction) {

        this.direction = direction;

    }

}
