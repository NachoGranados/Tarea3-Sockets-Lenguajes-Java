package Controller;

import Model.*;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private static ArrayList<DonkeyKongJr> players = new ArrayList<>();

    private static ArrayList<Alligator> alligators1 = new ArrayList<>();
    private static ArrayList<Alligator> alligators2 = new ArrayList<>();

    private static ArrayList<Fruit> fruits1 = new ArrayList<>();
    private static ArrayList<Fruit> fruits2 = new ArrayList<>();

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    /**
     * Description: constructor method.
     */
    public Controller() {

        createNullInstances();

        players.set(0, new DonkeyKongJr(0,0,0,0));
        players.set(1, new DonkeyKongJr(0,0,0,0));

    }

    /**
     * Description: creates null instances of players, alligators and fruits to set the
     *              correct size to the respective arrayLists.
     */
    private void createNullInstances() {

        // Loop to add two null instances to players
        for (Integer i = 0; i < 2; i++) {

            players.add(null);

        }

        // Loop to add six null instances to alligators1
        for (Integer i = 0; i < 6; i++) {

            alligators1.add(null);

        }

        // Loop to add six null instances to alligators2
        for (Integer i = 0; i < 6; i++) {

            alligators2.add(null);

        }

        // Loop to add six null instances to fruits1
        for (Integer i = 0; i < 6; i++) {

            fruits1.add(null);

        }

        // Loop to add six null instances to fruits2
        for (Integer i = 0; i < 6; i++) {

            fruits2.add(null);

        }

    }

    /**
     * Description: Analyzes the string given by the client and calls the given functions.
     * @param instruction String with the indication given by the client
     */
    public void followInstruction(String instruction) throws IOException {

        // Instructions for player1
        if (instruction.contains("1")) {

            // Checks if the player1 is already instantiated or not
            if (players.get(0) == null) {

                player1 = new DonkeyKongJr(Constants.INITIAL_X,Constants.INITIAL_Y,
                                           Constants.INITIAL_LIFES, Constants.INITIAL_SCORE);
                players.add(player1);

            }

            // Moves player1 to the right
            if (instruction.equals("r1")) {

                player1.setPositionX(Constants.PIXELS_RIGHT_LEFT);

                // Moves player1 to the left
            } else if (instruction.equals("l1")) {

                player1.setPositionX(Constants.PIXELS_RIGHT_LEFT * -1);

                // Moves player1 upwards
            } else if (instruction.equals("u1")) {

                player1.setPositionY(Constants.PIXELS_UP_DOWN);

                // Moves player1 downwards
            } else if (instruction.equals("d1")) {

                player1.setPositionY(Constants.PIXELS_UP_DOWN * -1);

            }

            // Instructions for player2
        } else if (instruction.contains("2")) {

            // Checks if the player2 is already instantiated or not
            if (players.get(1) == null) {

                player2 = new DonkeyKongJr(Constants.INITIAL_X,Constants.INITIAL_Y,
                                           Constants.INITIAL_LIFES, Constants.INITIAL_SCORE);
                players.add(player2);

            }

            // Moves player2 to the right
            if (instruction.equals("r2")) {

                player2.setPositionX(Constants.PIXELS_RIGHT_LEFT);

                // Moves player2 to the left
            } else if (instruction.equals("l2")) {

                player2.setPositionX(Constants.PIXELS_RIGHT_LEFT * -1);

                // Moves player2 upwards
            } else if (instruction.equals("u2")) {

                player2.setPositionY(Constants.PIXELS_UP_DOWN);

                // Moves player2 upwards
            } else if (instruction.equals("d2")) {

                player2.setPositionY(Constants.PIXELS_UP_DOWN * -1);

            }

        }

        // Actualizes alligators information
        moveAlligators();

        // Checks if DonkeyKongJr has reached a fruit
        checkPoints();

        // Checks if DonkeyKongJr has reached an alligator
        checkLifes();

        // Checks if DonkeyKongJr has reached DonkeyKong
        nextLevel();

    }

    /**
     * Description: return the full data of players, fruits and alligators in one simple string.
     */
    public String getData() {

        String data = "";
        ArrayList<Fruit> fruitsTemporal;
        ArrayList<Alligator> alligatorsTemporal;

        for (Integer i = 0; i < players.size(); i++) {

            // Player does not exist
            if (players.get(i) == null) {

                data += "dk,_,_;" +
                        "f1,_,_;f2,_,_;f3,_,_;f4,_,_f5,_,_f6,_,_;" +
                        "c1,_,_,_;c2,_,_,_;c3,_,_,_;c4,_,_,_;c5,_,_,_;c6,_,_,_;" +
                        "/";

            } else {

                data += "dk," + players.get(i).getPositionX() + "," + players.get(i).getPositionY() + ";";

                if (i == 0) {

                    fruitsTemporal = fruits1;
                    alligatorsTemporal = alligators1;

                } else {

                    fruitsTemporal = fruits2;
                    alligatorsTemporal = alligators2;

                }

                // Fruits information
                for (Integer j = 0; j < fruitsTemporal.size(); j++) {

                    // Fruit does not exist
                    if (fruitsTemporal.get(j) == null) {

                        data += "f" + (j + 1) + ",_,_;";

                    } else {

                        data += "f" + (j + 1) + "," + fruitsTemporal.get(j).getPositionX() + "," +
                                fruitsTemporal.get(j).getPositionY()+ ";";

                    }

                }

                // Alligators information
                for (Integer k = 0; k < alligatorsTemporal.size(); k++) {

                    // Alligator does not exist
                    if (alligatorsTemporal.get(k) == null) {

                        data += "c" + (k + 1) + ",_,_,_;";

                    } else {

                        data += "c" + (k + 1) + "," + alligatorsTemporal.get(k).getColor() + "," +
                                alligatorsTemporal.get(k).getPositionX() + "," + alligatorsTemporal.get(k).getPositionY() + ";";

                    }

                }

                data += "/";

            }

        }

        return data;

    }

    /**
     * Description: Actualizes the alligators positions and directions.
     */
    private void moveAlligators() {

        Integer lianaStart = 0;
        Integer lianaEnd = 0;

        ArrayList< ArrayList<Alligator> > alligatorsTemporal = new ArrayList<>();
        alligatorsTemporal.add(alligators1);
        alligatorsTemporal.add(alligators2);

        for (Integer i = 0; i < alligatorsTemporal.size(); i++) {

            for (Integer j = 0; j < alligatorsTemporal.get(i).size(); j++) {

                // Checks if alligator instance exists
                if (alligatorsTemporal.get(i).get(j) != null) {

                    // Depending on the iteration, the respective constants will be assigned to the alligator
                    switch (j) {

                        case 0:
                            lianaStart = Constants.LIANA1_START;
                            lianaEnd = Constants.LIANA1_END;
                            break;

                        case 1:
                            lianaStart = Constants.LIANA2_START;
                            lianaEnd = Constants.LIANA2_END;
                            break;

                        case 2:
                            lianaStart = Constants.LIANA3_START;
                            lianaEnd = Constants.LIANA3_END;
                            break;

                        case 3:
                            lianaStart = Constants.LIANA4_START;
                            lianaEnd = Constants.LIANA4_END;
                            break;

                        case 4:
                            lianaStart = Constants.LIANA5_START;
                            lianaEnd = Constants.LIANA5_END;
                            break;

                        case 5:
                            lianaStart = Constants.LIANA6_START;
                            lianaEnd = Constants.LIANA6_END;
                            break;

                    }

                    // Red alligator case
                    if (alligatorsTemporal.get(i).get(j).getColor() == "r") {

                        // Alligator goes down and its positions is different to the end of the liana
                        if (alligatorsTemporal.get(i).get(j).getDirection() == "DOWN" &&
                                alligatorsTemporal.get(i).get(j).getPositionY() != lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setPositionY(Constants.ALLIGATOR_DOWN);

                            // Alligator changes its direction to up because its positions is equal to the end of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setDirection("UP");
                            alligatorsTemporal.get(i).get(j).setPositionY(Constants.ALLIGATOR_UP  * -1);

                            // Alligator goes up and its positions is different to the start of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getDirection() == "UP" &&
                                alligatorsTemporal.get(i).get(j).getPositionY() != lianaStart) {

                            alligatorsTemporal.get(i).get(j).setPositionY(Constants.ALLIGATOR_UP  * -1);

                            // Alligator changes its direction to down because its positions is equal to the start of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaStart) {

                            alligatorsTemporal.get(i).get(j).setDirection("DOWN");
                            alligatorsTemporal.get(i).get(j).setPositionY(Constants.ALLIGATOR_DOWN);

                        }

                        // Blue alligator case
                    } else {

                        // Alligator goes down and its positions is different to the end of the liana
                        if (alligatorsTemporal.get(i).get(j).getDirection() == "DOWN" &&
                                alligatorsTemporal.get(i).get(j).getPositionY() != lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setPositionY(Constants.ALLIGATOR_DOWN);

                            // Alligator has to be eliminated
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaEnd) {

                            alligatorsTemporal.get(i).set(j, null);

                        }

                    }

                }

            }

        }

    }

    /**
     * Description: Checks if the player has reached a fruit and increases its points.
     */
    private void checkPoints() {

        ArrayList< ArrayList<Fruit> > fruitsTemporal = new ArrayList<>();
        fruitsTemporal.add(fruits1);
        fruitsTemporal.add(fruits2);

        for (Integer i = 0; i < fruitsTemporal.size(); i++) {

            for (Integer j = 0; j < fruitsTemporal.get(i).size(); j++) {

                // Checks match
                if (players.get(i).getPositionX() == fruitsTemporal.get(i).get(j).getPositionX() &&
                        players.get(i).getPositionY() == fruitsTemporal.get(i).get(j).getPositionY()) {

                    // Increase points
                    players.get(i).setScore(Constants.POINTS);

                    // Delete fruit
                    fruitsTemporal.get(i).set(j, null);

                }

            }

        }

    }

    /**
     * Description: Checks if the player has reached a alligator and loses the game.
     */
    private void checkLifes() {

        ArrayList< ArrayList<Alligator> > alligatorsTemporal = new ArrayList<>();
        alligatorsTemporal.add(alligators1);
        alligatorsTemporal.add(alligators2);

        ArrayList< ArrayList<Fruit> > fruitsTemporal = new ArrayList<>();
        fruitsTemporal.add(fruits1);
        fruitsTemporal.add(fruits2);

        for (Integer i = 0; i < alligatorsTemporal.size(); i++) {

            for (Integer j = 0; j < alligatorsTemporal.get(i).size(); j++) {

                // Checks match
                if (players.get(i).getPositionX() == alligatorsTemporal.get(i).get(j).getPositionX() &&
                        players.get(i).getPositionY() == alligatorsTemporal.get(i).get(j).getPositionY()) {

                    // Set initial values
                    players.get(i).setPositionX(Constants.INITIAL_X);
                    players.get(i).setPositionY(Constants.INITIAL_Y);

                    // Decrease lifes
                    players.get(i).setLifes(-1);

                    // Restart score
                    players.get(i).setScore(0);

                    // Delete all alligators
                    for (Integer k = 0; k < 6; k++) {

                        alligatorsTemporal.get(i).set(k, null);

                    }

                    // Delete all fruits
                    for (Integer l = 0; l < 6; l++) {

                        fruitsTemporal.get(i).set(l, null);

                    }

                }

            }

        }

    }

    /**
     * Description: Checks if the player has reached DonkeyKong and next level will start.
     */
    private void nextLevel() {

        for (Integer i = 0; i < players.size(); i++) {

            // Checks match
            if (players.get(i).getPositionX() == Constants.DONKEY_KONG_X &&
                    players.get(i).getPositionY() == Constants.DONKEY_KONG_Y) {

                // Increases lifes
                players.get(i).setLifes(1);

                // Increases alligators speed
                Constants.SPEED += 1;

                ArrayList< ArrayList<Alligator> > alligatorsTemporal = new ArrayList<>();
                alligatorsTemporal.add(alligators1);
                alligatorsTemporal.add(alligators2);

                // Delete all alligators
                for (Integer j = 0; j < 6; j++) {

                    alligatorsTemporal.get(i).set(j, null);

                }

                ArrayList< ArrayList<Fruit> > fruitsTemporal = new ArrayList<>();
                fruitsTemporal.add(fruits1);
                fruitsTemporal.add(fruits2);

                // Delete all fruits
                for (Integer k = 0; k < 6; k++) {

                    fruitsTemporal.get(i).set(k, null);

                }

            }

        }

    }

    /**
     * Description: returns which player instances are not null
     */
    public String[] getDynamicPlayer() {

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every player
        for (Integer i = 0; i < players.size(); i++) {

            if (players.get(i) != null) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        String[] selections = new String[temporal.size()];

        // Loop to add final player instances
        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    /**
     * Description: returns which alligator instances are not null
     * @param player
     */
    public String[] getDynamicLiana(Integer player) {

        ArrayList< ArrayList<Alligator> > alligatorsTemporal = new ArrayList<>();
        alligatorsTemporal.add(alligators1);
        alligatorsTemporal.add(alligators2);

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every alligator instance depending on the player received
        for (Integer i = 0; i < alligatorsTemporal.get(player).size(); i++) {

            if (alligatorsTemporal.get(player).get(i) == null) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        String[] selections = new String[temporal.size()];

        // Loop to add final alligator instances
        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    /**
     * Description: returns which fruit instances are or not null depending on the action received
     * @param player
     * @param action
     */
    public String[] getDynamicPlatform(Integer player, String action) {

        ArrayList< ArrayList<Fruit> > fruitsTemporal = new ArrayList<>();
        fruitsTemporal.add(fruits1);
        fruitsTemporal.add(fruits2);

        ArrayList<String> temporal = new ArrayList<>();

        // Loop to check every fruit instance depending on the player and action received
        for (Integer i = 0; i < fruitsTemporal.get(player).size(); i++) {


            if (fruitsTemporal.get(player).get(i) == null && action.equals("ADD")) {

                temporal.add(String.valueOf(i + 1));

            } else if(fruitsTemporal.get(player).get(i) != null && action.equals("DELETE")) {

                temporal.add(String.valueOf(i + 1));

            }

        }

        // Loop to add final fruit instances
        String[] selections = new String[temporal.size()];

        for (Integer j = 0; j < temporal.size(); j++) {

            selections[j] = temporal.get(j);

        }

        return selections;

    }

    public void alligatorsFruitsCreation(String strComboBoxObjectValue, String strComboBoxPlatformValue,
                                        String strComboBoxPlayerValue, Integer intComboBoxLianaValue,
                                        Integer intComboBoxPlatformValue) {

        Integer positionX = 0;
        Integer positionY = 0;

        // Pick the selected information
        switch (strComboBoxObjectValue) {

            // Add fruit
            case "Anadir Fruta":

                // Depending on the comboBoxObjectValue, the respective constants will be assigned to the fruit
                switch (strComboBoxPlatformValue) {

                    case "1":
                        positionX = Constants.PLATFORM1_X;
                        positionY = Constants.PLATFORM1_Y;
                        break;

                    case "2":
                        positionX = Constants.PLATFORM2_X;
                        positionY = Constants.PLATFORM2_Y;
                        break;

                    case "3":
                        positionX = Constants.PLATFORM3_X;
                        positionY = Constants.PLATFORM3_Y;
                        break;

                    case "4":
                        positionX = Constants.PLATFORM4_X;
                        positionY = Constants.PLATFORM4_Y;
                        break;

                    case "5":
                        positionX = Constants.PLATFORM5_X;
                        positionY = Constants.PLATFORM5_Y;
                        break;

                    case "6":
                        positionX = Constants.PLATFORM6_X;
                        positionY = Constants.PLATFORM6_Y;
                        break;

                }

                Fruit temporalFruit = new Fruit(positionX, positionY);

                // Add fruit to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    fruits1.set(intComboBoxPlatformValue, temporalFruit);

                    // Add fruit to player2
                } else {

                    fruits2.set(intComboBoxPlatformValue, temporalFruit);

                }

                break;

            // Delete fruit
            case "Eliminar Fruta":

                // Delete fruit from player1
                if (strComboBoxPlayerValue.equals("1")) {

                    fruits1.set(intComboBoxPlatformValue, null);

                    // Delete fruit from player2
                } else {

                    fruits2.set(intComboBoxPlatformValue, null);

                }

                break;

            // Add red alligatior
            case "Anadir Cocodrilo Rojo":

                // Depending on the comboBoxObjectValue, the respective constants will be assigned to the alligator
                switch (strComboBoxPlatformValue) {

                    case "1":
                        positionX = Constants.LIANA1_X;
                        positionY = Constants.LIANA1_Y;
                        break;

                    case "2":
                        positionX = Constants.LIANA2_X;
                        positionY = Constants.LIANA2_Y;
                        break;

                    case "3":
                        positionX = Constants.LIANA3_X;
                        positionY = Constants.LIANA3_Y;
                        break;

                    case "4":
                        positionX = Constants.LIANA4_X;
                        positionY = Constants.LIANA4_Y;
                        break;

                    case "5":
                        positionX = Constants.LIANA5_X;
                        positionY = Constants.LIANA5_Y;
                        break;

                    case "6":
                        positionX = Constants.LIANA6_X;
                        positionY = Constants.LIANA6_Y;
                        break;

                }

                Alligator temporalAlligatorR = new Alligator("r", positionX,positionY, Constants.SPEED);

                // Add red alligator to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    alligators1.set(intComboBoxLianaValue, temporalAlligatorR);

                    // Add red alligator to player2
                } else {

                    alligators2.set(intComboBoxLianaValue, temporalAlligatorR);

                }

                break;

            // Add blue alligator
            case "Anadir Cocodrilo Azul":

                Alligator temporalAlligatorB = new Alligator("b", positionX,positionY, Constants.SPEED);

                // Add blue alligator to player1
                if (strComboBoxPlayerValue.equals("1")) {

                    alligators1.set(intComboBoxLianaValue, temporalAlligatorB);

                    // Add blue alligator to player2
                } else {

                    alligators2.set(intComboBoxLianaValue, temporalAlligatorB);

                }

                break;

        }

    }

}

