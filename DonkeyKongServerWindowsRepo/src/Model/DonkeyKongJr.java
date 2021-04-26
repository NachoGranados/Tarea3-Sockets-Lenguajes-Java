package Model;

public class DonkeyKongJr extends ObjectFactory {

    // Attributes for DonkeyKongJr instance
    private Integer lifes;
    private Integer score;

    /**
     * Description: constructor method.
     * @param positionX
     * @param positionY
     * @param lifes
     * @param score
     */
    public DonkeyKongJr(Integer positionX, Integer positionY, Integer lifes, Integer score) {

        super(positionX, positionY);

        this.lifes = lifes;
        this.score = score;

    }

    /**
     * Description: returns lifes of the DonkeyKongJr.
     */
    public Integer getLifes() {

        return lifes;

    }

    /**
     * Description: sets lifes to DonkeyKongJr.
     * @param lifes
     */
    public void setLifes(Integer lifes) {

        this.lifes += lifes;

    }

    /**
     * Description: returns score of the DonkeyKongJr.
     */
    public Integer getScore() {

        return score;

    }

    /**
     * Description: sets score to DonkeyKongJr.
     * @param score
     */
    public void setScore(Integer score) {

        this.score += score;

    }

}
