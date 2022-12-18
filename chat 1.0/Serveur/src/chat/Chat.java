package chat;

import client.Client;
import ioStream.Input;
import main.Main;

import java.io.IOException;

public class Chat extends Thread{
    private boolean loop = true;
    Client client;

    public Chat(Client client) {
        this.client = client;


        start();
    }

    @Override
    public void run() {
        while (loop) {
            String value = null;
            try {
                value = client.getNom()+":::"+client.getIn().readUTF();

                try {
                    Input.messageToTxt(value,client.getRoom());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            } catch (IOException e) {
                Main.clients.remove(client);
            }
        }
    }
}
