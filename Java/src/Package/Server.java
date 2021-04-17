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
            System.out.println("Package.Server is Waiting for client request... ");

            // Listening a connection to be made between the server and the client.
            Socket socket = serverSocket.accept();



            // Sending a message to the client in network format.
            DatoSocket datoEnviar = new DatoSocket("Hola");
            dout = new DataOutputStream(socket.getOutputStream());

            datoEnviar.writeObject(dout);
            System.out.println("Enviado " + datoEnviar.d);




            // Reading a message from the server in hardware format.
            din = new DataInputStream(socket.getInputStream());

            DatoSocket datoRecibir = new DatoSocket("");
            datoRecibir.readObject(din);
            System.out.println("Recibido " + datoRecibir.d);




            /*

            // Sending a message to the client in network format.
            DatoSocket aux1 = new DatoSocket("Prueba");
            dout = new DataOutputStream(socket.getOutputStream());

            aux1.writeObject(dout);
            System.out.println("Servidor: Enviado " + aux1.d);





            //OutputStream outputStream = socket.getOutputStream();
            //dout = new DataOutputStream(outputStream);


            */




            br = new BufferedReader(new InputStreamReader(System.in));

            String strFromClient = "";
            String strToClient = "";

            while (!strFromClient.equals("stop")) {

                strFromClient = din.readUTF();
                System.out.println("client says: " + strFromClient);

                strToClient = br.readLine();
                dout.writeUTF(strToClient);
                dout.flush();

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
