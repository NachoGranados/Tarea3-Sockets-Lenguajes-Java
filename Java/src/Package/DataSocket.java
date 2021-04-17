package Package;

import java.io.*;

public class DataSocket implements Serializable {

    public int messageLength;
    public String message;

    public DataSocket(String message) {

        if (message != null) {

            this.messageLength = message.length();
            this.message = message;

        }

    }

    public void writeObject(java.io.DataOutputStream out) throws IOException {

        out.writeInt(messageLength + 1);
        out.writeBytes(message);
        out.writeByte('\0');

    }

    public void readObject(java.io.DataInputStream in) throws IOException {

        messageLength = in.readInt() - 1;

        byte [] aux = null;
        aux = new byte[messageLength];

        in.read(aux, 0, messageLength);
        message = new String(aux);

        in.read(aux, 0, 1);

    }

}
