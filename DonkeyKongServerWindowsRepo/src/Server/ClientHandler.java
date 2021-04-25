package Server;

import Instances.Alligator;
import Instances.DonkeyKongJr;
import Instances.Fruit;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    // Sockets Attributes
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private DataOutputStream output;
    private DataInputStream input;
    private String incomingMessage = "";

    // Attributes for ClientHandler instance
    private ArrayList<ClientHandler> clients;

    private ArrayList<DonkeyKongJr> players;

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    private ArrayList<Alligator> alligators1;
    private ArrayList<Alligator> alligators2;

    private ArrayList<Fruit> fruits1;
    private ArrayList<Fruit> fruits2;

    // Constants
    private static final Integer PIXELS_UP_DOWN = 50;
    private static final Integer PIXELS_RIGHT_LEFT = 50;

    private static final Integer ALLIGATOR_UP = 50;
    private static final Integer ALLIGATOR_DOWN = 50;

    private static final Integer LIANA1_START = 50;
    private static final Integer LIANA2_START = 50;
    private static final Integer LIANA3_START = 50;
    private static final Integer LIANA4_START = 50;
    private static final Integer LIANA5_START = 50;
    private static final Integer LIANA6_START = 50;

    private static final Integer LIANA1_END = 50;
    private static final Integer LIANA2_END = 50;
    private static final Integer LIANA3_END = 50;
    private static final Integer LIANA4_END = 50;
    private static final Integer LIANA5_END = 50;
    private static final Integer LIANA6_END = 50;

    /**
     * Description: constructor method.
     */
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients, ArrayList<DonkeyKongJr> players,
                         ArrayList<Alligator> alligators1, ArrayList<Alligator> alligators2,
                         ArrayList<Fruit> fruits1, ArrayList<Fruit> fruits2) throws IOException {

        this.client = clientSocket;
        this.clients = clients;
        this.players = players;
        this.alligators1 = alligators1;
        this.alligators2 = alligators2;
        this.fruits1 = fruits1;
        this.fruits2 = fruits2;

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

    }

    /**
     * Description: main thread method.
     */
    @Override
    public void run() {

        System.out.println("Listening thread active");

        // Receiving messages until getting the "s" character
        while (!incomingMessage.equals("s")){

            try {

                // Waits for the client to send something
                input = new DataInputStream(this.client.getInputStream());
                incomingMessage = this.readObject(input);
                System.out.println("Message from client: "+ incomingMessage);

                // Instruction that will be followed depending on what client message says
                this.followInstruction(incomingMessage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Description: Configures the socket message into a one Java could understand.
     * Resturn: returns the string.
     */
    private String readObject(java.io.DataInputStream input) throws IOException {

        // Deletes \0 character from C strings
        Integer messageLenght = input.readInt() - 1;

        byte [] aux = null;
        aux = new byte[messageLenght];

        input.read(aux, 0, messageLenght);
        String message = new String(aux);
        input.read(aux, 0, 1);

        return message;

    }

    /**
     * Description: method that configures the message into a C understandable one
     * and also sends the message by an output stream.
     * @param output 
     * @throws IOException
     */
    private void writeObject(java.io.DataOutputStream output) throws IOException{
        
        String message = getData();
        Integer messageLength = message.length();

        output.writeInt(messageLength + 1);
        output.writeBytes(message);
        output.writeByte('\0');
        output.flush();
        System.out.println("Enviando: " + message);

    }

    /**
     * Description: Analyzes the string given by the client and calls the given functions.
     * @param instruction String with the indication given by the client
     */
    private void followInstruction(String instruction) throws IOException {

        // Instructions for player1
        if (instruction.contains("1")) {

            // Checks if the player1 is already instantiated or not
            if (players.get(0) == null) {

                player1 = new DonkeyKongJr(0,0, 1, 0);
                players.add(player1);

            }

            // Moves player1 to the right
            if (instruction.equals("r1")) {

                player1.setPositionX(PIXELS_RIGHT_LEFT);

            // Moves player1 to the left
            } else if (instruction.equals("l1")) {

                player1.setPositionX(PIXELS_RIGHT_LEFT * -1);

            // Moves player1 upwards
            } else if (instruction.equals("u1")) {

                player1.setPositionY(PIXELS_UP_DOWN);

            // Moves player1 downwards
            } else if (instruction.equals("d1")) {

                player1.setPositionY(PIXELS_UP_DOWN * -1);

            }

        // Instructions for player2
        } else if (instruction.contains("2")) {

            // Checks if the player2 is already instantiated or not
            if (players.get(1) == null) {

                player2 = new DonkeyKongJr(0,0, 1, 0);
                players.add(player2);

            }

            // Moves player2 to the right
            if (instruction.equals("r2")) {

                player2.setPositionX(PIXELS_RIGHT_LEFT);

            // Moves player2 to the left
            } else if (instruction.equals("l2")) {

                player2.setPositionX(PIXELS_RIGHT_LEFT * -1);

            // Moves player2 upwards
            } else if (instruction.equals("u2")) {

                player2.setPositionY(PIXELS_UP_DOWN);

            // Moves player2 upwards
            } else if (instruction.equals("d2")) {

                player2.setPositionY(PIXELS_UP_DOWN * -1);

            }

        }

        // Actualizes alligators information
        moveAlligators();

        // The server always has to notify clients no matter what string it receives
        output = new DataOutputStream(client.getOutputStream());
        writeObject(output);

    }

    /**
     * Description: return the full data of players, fruits and alligators in one simple string.
     */
    private String getData() {

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
                            lianaStart = LIANA1_START;
                            lianaEnd = LIANA1_END;
                            break;

                        case 1:
                            lianaStart = LIANA2_START;
                            lianaEnd = LIANA2_END;
                            break;

                        case 2:
                            lianaStart = LIANA3_START;
                            lianaEnd = LIANA3_END;
                            break;

                        case 3:
                            lianaStart = LIANA4_START;
                            lianaEnd = LIANA4_END;
                            break;

                        case 4:
                            lianaStart = LIANA5_START;
                            lianaEnd = LIANA5_END;
                            break;

                        case 5:
                            lianaStart = LIANA6_START;
                            lianaEnd = LIANA6_END;
                            break;

                    }

                    // Red alligator case
                    if (alligatorsTemporal.get(i).get(j).getColor() == "r") {

                        // Alligator goes down and its positions is different to the end of the liana
                        if (alligatorsTemporal.get(i).get(j).getDirection() == "DOWN" &&
                            alligatorsTemporal.get(i).get(j).getPositionY() != lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setPositionY(ALLIGATOR_DOWN);

                        // Alligator changes its direction to up because its positions is equal to the end of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setDirection("UP");
                            alligatorsTemporal.get(i).get(j).setPositionY(ALLIGATOR_UP  * -1);

                        // Alligator goes up and its positions is different to the start of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getDirection() == "UP" &&
                                   alligatorsTemporal.get(i).get(j).getPositionY() != lianaStart) {

                            alligatorsTemporal.get(i).get(j).setPositionY(ALLIGATOR_UP  * -1);

                        // Alligator changes its direction to down because its positions is equal to the start of the liana
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaStart) {

                            alligatorsTemporal.get(i).get(j).setDirection("DOWN");
                            alligatorsTemporal.get(i).get(j).setPositionY(ALLIGATOR_DOWN);

                        }

                    // Blue alligator case
                    } else {

                        // Alligator goes down and its positions is different to the end of the liana
                        if (alligatorsTemporal.get(i).get(j).getDirection() == "DOWN" &&
                            alligatorsTemporal.get(i).get(j).getPositionY() != lianaEnd) {

                            alligatorsTemporal.get(i).get(j).setPositionY(ALLIGATOR_DOWN);

                        // Alligator has to be eliminated
                        } else if (alligatorsTemporal.get(i).get(j).getPositionY() == lianaEnd) {

                            alligatorsTemporal.get(i).set(j, null);

                        }

                    }

                }

            }

        }

    }

}
