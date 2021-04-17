package Package;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        DataInputStream input = null;
        ServerSocket serverSocket = null;
        DataOutputStream output = null;
        BufferedReader buffer = null;

        try {

            // Creating the socket with the specified port.
            serverSocket = new ServerSocket(6666);
            System.out.println("Server is Waiting for client request... ");

            // Listening a connection to be made between the server and the client.
            Socket socket = serverSocket.accept();

            input = new DataInputStream(socket.getInputStream());

            output = new DataOutputStream(socket.getOutputStream());

            buffer = new BufferedReader(new InputStreamReader(System.in));



            // Sending a message to the client in network format.
            //DataSocket dataSend = new DataSocket(buffer.readLine());
            //output = new DataOutputStream(socket.getOutputStream());
            //dataSend.writeObject(output);
            //System.out.println("Enviado " + dataSend.message);




            // Reading a message from the server in hardware format.
            //input = new DataInputStream(socket.getInputStream());
            //DataSocket dataReceive = new DataSocket("");
            //dataReceive.readObject(input);
            //System.out.println("Recibido " + dataReceive.message);






            String strFromClient = "";
            String strToClient = "";


            while (!strFromClient.equals("stop") || !strToClient.equals("stop")) {

                DataSocket dataSend = new DataSocket(buffer.readLine());
                dataSend.writeObject(output);
                output.flush();
                strToClient = dataSend.message;
                System.out.println("Enviado: " + dataSend.message);


                DataSocket dataReceive = new DataSocket("");
                dataReceive.readObject(input);
                strFromClient = dataReceive.message;
                System.out.println("Recibido: " + dataReceive.message);






                /*
                buffer = new BufferedReader(new InputStreamReader(System.in));

                // Sending a message to the client in network format.
                DataSocket dataSend = new DataSocket(buffer.readLine());
                output = new DataOutputStream(socket.getOutputStream());
                dataSend.writeObject(output);
                //System.out.println("Enviado " + dataSend.message);

                // Reading a message from the server in hardware format.
                input = new DataInputStream(socket.getInputStream());
                DataSocket dataReceive = new DataSocket("");
                dataReceive.readObject(input);
                System.out.println("Recibido " + dataReceive.message);
                output.flush();

                strFromClient = input.readUTF();

                */

            }

        } catch (Exception exe) {

            exe.printStackTrace();

        }

        finally {

            try {

                if (buffer != null) {

                    buffer.close();

                }

                if (input != null) {

                    input.close();

                }

                if (output != null) {

                    output.close();

                }

                if (serverSocket != null) {

                    /*
                     * closes the server socket.
                     */
                    serverSocket.close();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
