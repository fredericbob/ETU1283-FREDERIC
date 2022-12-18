package message;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Message extends Thread{

    private int nbLigne = 0;
    private boolean loop = true;
    private ObjectInputStream in;
    private final JTextArea allMessage;
    private Socket socket;


    public Message(JTextArea allMessage, Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        this.allMessage = allMessage;
        this.socket = socket;

        start();
    }

    @Override
    public void run() {
        String[][] data;
        while (loop) {
            try {
                data = (String[][]) in.readObject();
                for (int i = nbLigne; i< data.length; i++) {
                    allMessage.setText(allMessage.getText()+"\n"+data[i][0]+": "+ data[i][1]);
                }
                nbLigne = data.length;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
