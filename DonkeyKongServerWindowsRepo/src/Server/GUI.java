package Server;

import Instances.Alligator;
import Instances.DonkeyKongJr;
import Instances.Fruit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {

    // Attributes for GUI
    private JPanel panel;

    private ImageIcon image;

    private JLabel labelObject;
    private JLabel labelLiana;
    private JLabel labelPlatform;
    private JLabel labelPlayer;

    private JComboBox comboBoxObject;
    private JComboBox comboBoxLiana;
    private JComboBox comboBoxPlatform;
    private JComboBox comboBoxPlayer;

    private String[] objectsArray;
    private String[] lianasArray;
    private String[] platformsArray;
    private String[] playersArray;

    private JFrame frame;

    private ArrayList<DonkeyKongJr> players;

    private DonkeyKongJr player1;
    private DonkeyKongJr player2;

    private ArrayList<Alligator> alligators1;
    private ArrayList<Alligator> alligators2;

    private ArrayList<Fruit> fruits1;
    private ArrayList<Fruit> fruits2;

    // Constants
    private static final Integer PLATFORM1_X = 100;
    private static final Integer PLATFORM1_Y = 100;

    private static final Integer PLATFORM2_X = 100;
    private static final Integer PLATFORM2_Y = 100;

    private static final Integer PLATFORM3_X = 100;
    private static final Integer PLATFORM3_Y = 100;

    private static final Integer PLATFORM4_X = 100;
    private static final Integer PLATFORM4_Y = 100;

    private static final Integer PLATFORM5_X = 100;
    private static final Integer PLATFORM5_Y = 100;

    private static final Integer PLATFORM6_X = 100;
    private static final Integer PLATFORM6_Y = 100;

    private static final Integer LIANA1_X = 100;
    private static final Integer LIANA1_Y = 100;

    private static final Integer LIANA2_X = 100;
    private static final Integer LIANA2_Y = 100;

    private static final Integer LIANA3_X = 100;
    private static final Integer LIANA3_Y = 100;

    private static final Integer LIANA4_X = 100;
    private static final Integer LIANA4_Y = 100;

    private static final Integer LIANA5_X = 100;
    private static final Integer LIANA5_Y = 100;

    private static final Integer LIANA6_X = 100;
    private static final Integer LIANA6_Y = 100;

    private static final Integer SPEED = 1;

    /**
     * Description: constructor method.
     * @param players
     * @param alligators1
     * @param alligators2
     * @param fruits1
     * @param fruits2
     */
    public GUI(ArrayList<DonkeyKongJr> players, ArrayList<Alligator> alligators1,
               ArrayList<Alligator> alligators2, ArrayList<Fruit> fruits1,
               ArrayList<Fruit> fruits2) {

        this.players = players;

        this.alligators1 = alligators1;
        this.alligators2 = alligators2;

        this.fruits1 = fruits1;
        this.fruits2 = fruits2;

        panel = new JPanel();
        panel.setLayout(null);

        // Background
        image = new ImageIcon(getClass().getResource("/Images/ArcadeMachine.png"));
        JLabel background = new JLabel("", image,JLabel.CENTER);
        background.setBounds(0,0, 700, 670);
        panel.add(background);

        // Liana
        labelLiana = new JLabel("Seleccione una liana");
        labelLiana.setBounds(150, 295, 300, 25);
        labelLiana.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelLiana);

        lianasArray = new String[] {"1", "2", "3", "4", "5", "6"};
        comboBoxLiana = new JComboBox(lianasArray);
        comboBoxLiana.setBounds(450, 295, 100, 25);
        comboBoxLiana.setEnabled(false);
        panel.add(comboBoxLiana);

        // Platform
        labelPlatform = new JLabel("Seleccione una plataforma");
        labelPlatform.setBounds(150, 355, 300, 25);
        labelPlatform.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelPlatform);

        platformsArray = new String[] {"1", "2", "3", "4", "5", "6"};
        comboBoxPlatform = new JComboBox(platformsArray);
        comboBoxPlatform.setBounds(450, 355, 100, 25);
        comboBoxPlatform.setEnabled(false);
        panel.add(comboBoxPlatform);

        // Object
        labelObject = new JLabel("Seleccione una accion");
        labelObject.setBounds(150, 175, 300, 25);
        labelObject.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelObject);

        objectsArray = new String[] {"Anadir Fruta", "Eliminar Fruta", "Anadir Cocodrilo Rojo", "Anadir Cocodrilo Azul"};
        comboBoxObject = new JComboBox(objectsArray);
        comboBoxObject.setBounds(400, 175, 150, 25);
        comboBoxObject.addActionListener(new ActionListener() {

            // Enables, disables and add the respective items to the comboBoxes
            @Override
            public void actionPerformed(ActionEvent e) {

                comboBoxPlayer.setEnabled(true);

                //players.set(0, new DonkeyKongJr(0,0,0,0));
                //players.set(1, new DonkeyKongJr(0,0,0,0));

                DefaultComboBoxModel<String> playerModel = new DefaultComboBoxModel<>(getDynamicPlayer());
                comboBoxPlayer.setModel(playerModel);

                Integer player = comboBoxPlayer.getSelectedIndex();

                String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();

                // Add alligator case
                if (strComboBoxObjectValue.contains("Cocodrilo")) {

                    DefaultComboBoxModel<String> lianaModel = new DefaultComboBoxModel<>(getDynamicLiana(player));
                    comboBoxLiana.setModel(lianaModel);

                    comboBoxLiana.setEnabled(true);
                    comboBoxPlatform.setEnabled(false);

                // Add fruit case
                } else if (strComboBoxObjectValue.equals("Anadir Fruta")) {

                    DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>(getDynamicPlatform(player, "ADD"));
                    comboBoxPlatform.setModel(platformModel);

                    comboBoxLiana.setEnabled(false);
                    comboBoxPlatform.setEnabled(true);

                // Delete fruit case
                } else if (strComboBoxObjectValue.equals("Eliminar Fruta")) {

                    DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>(getDynamicPlatform(player, "DELETE"));
                    comboBoxPlatform.setModel(platformModel);

                    comboBoxLiana.setEnabled(false);
                    comboBoxPlatform.setEnabled(true);

                }

            }

        });
        panel.add(comboBoxObject);

        // Player
        labelPlayer = new JLabel("Seleccione un jugador");
        labelPlayer.setBounds(150, 235, 300, 25);
        labelPlayer.setFont(new Font("Courier New", Font.CENTER_BASELINE, 18));
        panel.add(labelPlayer);

        playersArray = new String[] {"1", "2"};
        comboBoxPlayer = new JComboBox(playersArray);
        comboBoxPlayer.setBounds(450, 235, 100, 25);
        comboBoxPlayer.setEnabled(false);
        comboBoxPlayer.addActionListener(new ActionListener() {

            // Enables, disables and add the respective items to the comboBoxes
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer player = comboBoxPlayer.getSelectedIndex();

                DefaultComboBoxModel<String> lianaModel = new DefaultComboBoxModel<>(getDynamicLiana(player));
                comboBoxLiana.setModel(lianaModel);

                String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();

                // Add fruit case
                if (strComboBoxObjectValue.equals("Anadir Fruta")) {

                    DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>(getDynamicPlatform(player, "ADD"));
                    comboBoxPlatform.setModel(platformModel);

                // Delete fruit case
                } else if (strComboBoxObjectValue.equals("Eliminar Fruta")) {

                    DefaultComboBoxModel<String> platformModel = new DefaultComboBoxModel<>(getDynamicPlatform(player, "DELETE"));
                    comboBoxPlatform.setModel(platformModel);

                }

            }

        });
        panel.add(comboBoxPlayer);

        JButton buttonApplyChanges = new JButton("Aplicar cambios");
        buttonApplyChanges.setBounds(200, 415, 300, 50);
        buttonApplyChanges.addActionListener(new ActionListener() {

            // Create the respective instances of alligators and fruits depending on user administrator selection
            @Override
            public void actionPerformed(ActionEvent e) {

                // Dynamic manage information of the comboBoxes
                String strComboBoxObjectValue = comboBoxObject.getSelectedItem().toString();
                String strComboBoxPlatformValue = comboBoxPlatform.getSelectedItem().toString();
                String strComboBoxPlayerValue = comboBoxPlayer.getSelectedItem().toString();

                Integer intComboBoxLianaValue = Integer.parseInt(comboBoxLiana.getSelectedItem().toString()) - 1;
                Integer intComboBoxPlatformValue = Integer.parseInt(comboBoxPlatform.getSelectedItem().toString()) - 1;

                Integer positionX = 0;
                Integer positionY = 0;

                // Pick the selected information
                switch (strComboBoxObjectValue) {

                    // Add fruit
                    case "Anadir Fruta":

                        // Depending on the comboBoxObjectValue, the respective constants will be assigned to the fruit
                        switch (strComboBoxPlatformValue) {

                            case "1":
                                positionX = PLATFORM1_X;
                                positionY = PLATFORM1_Y;
                                break;

                            case "2":
                                positionX = PLATFORM2_X;
                                positionY = PLATFORM2_Y;
                                break;

                            case "3":
                                positionX = PLATFORM3_X;
                                positionY = PLATFORM3_Y;
                                break;

                            case "4":
                                positionX = PLATFORM4_X;
                                positionY = PLATFORM4_Y;
                                break;

                            case "5":
                                positionX = PLATFORM5_X;
                                positionY = PLATFORM5_Y;
                                break;

                            case "6":
                                positionX = PLATFORM6_X;
                                positionY = PLATFORM6_Y;
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
                                positionX = LIANA1_X;
                                positionY = LIANA1_Y;
                                break;

                            case "2":
                                positionX = LIANA2_X;
                                positionY = LIANA2_Y;
                                break;

                            case "3":
                                positionX = LIANA3_X;
                                positionY = LIANA3_Y;
                                break;

                            case "4":
                                positionX = LIANA4_X;
                                positionY = LIANA4_Y;
                                break;

                            case "5":
                                positionX = LIANA5_X;
                                positionY = LIANA5_Y;
                                break;

                            case "6":
                                positionX = LIANA6_X;
                                positionY = LIANA6_Y;
                                break;

                        }

                        Alligator temporalAlligatorR = new Alligator("r", positionX,positionY, SPEED);

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

                        Alligator temporalAlligatorB = new Alligator("b", positionX,positionY, SPEED);

                        // Add blue alligator to player1
                        if (strComboBoxPlayerValue.equals("1")) {

                            alligators1.set(intComboBoxLianaValue, temporalAlligatorB);

                            // Add blue alligator to player2
                        } else {

                            alligators2.set(intComboBoxLianaValue, temporalAlligatorB);

                        }

                        break;

                }

                // Disables the respective comboBoxes
                comboBoxPlatform.setEnabled(false);
                comboBoxLiana.setEnabled(false);
                comboBoxPlayer.setEnabled(false);

            }

        });
        panel.add(buttonApplyChanges);

        frame = new JFrame();
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("USUARIO ADMINISTRADOR");
        frame.setResizable(false);
        frame.add(panel);
        frame.setVisible(true);

    }

    /**
     * Description: returns which player instances are not null
     */
    private String[] getDynamicPlayer() {

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
    private String[] getDynamicLiana(Integer player) {

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
    private String[] getDynamicPlatform(Integer player, String action) {

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

}
