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

        DataInputStream din = null;
        ServerSocket serverSocket = null;
        DataOutputStream dout = null;
        BufferedReader br = null;

        try {

            // Creating the socket with the specified port.
            serverSocket = new ServerSocket(6666);
            System.out.println("Server is Waiting for client request... ");

            // Listening a connection to be made between the server and the client.
            Socket socket = serverSocket.accept();


            /*

            // Sending a message to the client in network format.
            DataSocket dataSend = new DataSocket("Hola");
            dout = new DataOutputStream(socket.getOutputStream());

            dataSend.writeObject(dout);
            System.out.println("Enviado " + dataSend.message);

            // Reading a message from the server in hardware format.
            din = new DataInputStream(socket.getInputStream());

            DataSocket dataReceive = new DataSocket("");
            dataReceive.readObject(din);
            System.out.println("Recibido " + dataReceive.message);

             */


            br = new BufferedReader(new InputStreamReader(System.in));



            String strFromClient = "";
            //String strToClient = "";

            while (!strFromClient.equals("stop")) {

                // Sending a message to the client in network format.
                DataSocket dataSend = new DataSocket(br.readLine());
                dout = new DataOutputStream(socket.getOutputStream());
                dataSend.writeObject(dout);
                System.out.println("Enviado " + dataSend.message);

                // Reading a message from the server in hardware format.
                din = new DataInputStream(socket.getInputStream());
                DataSocket dataReceive = new DataSocket("");
                dataReceive.readObject(din);
                System.out.println("Recibido " + dataReceive.message);

                dout.flush();


                /*

                strFromClient = din.readUTF();
                System.out.println("client says: " + strFromClient);

                strToClient = br.readLine();
                dout.writeUTF(strToClient);
                dout.flush();

                 */

            }

        } catch (Exception exe) {

            exe.printStackTrace();

        }

        finally {

            try {

                if (br != null) {

                    br.close();

                }

                if (din != null) {

                    din.close();

                }

                if (dout != null) {

                    dout.close();

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
