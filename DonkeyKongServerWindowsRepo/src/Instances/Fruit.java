package Instances;

public class Fruit extends ObjectFactory {

    // Attribute for Fruit
    private Integer value;

    // Constant
    private static final Integer POINTS = 100;

    /**
     * Description: constructor method.
     * param positionX
     * @param positionY
     */
    public Fruit(Integer positionX, Integer positionY) {

        super(positionX, positionY);

        this.value = POINTS;

    }

    /**
     * Description: returns value of fruit.
     */
    public Integer getValue() {

        return value;

    }

}
