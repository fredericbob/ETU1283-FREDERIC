package ioStream;

import client.Client;
import main.Main;

import java.io.ObjectOutputStream;

public class listenFile extends Thread{

    private int nbline=0;
    String file;
    String[][] data;

    public listenFile(String filename) {
        nbline = 0;
        file = Main.dossier+filename.toUpperCase()+".txt";

        start();
    }


    public void sendMessage(Client client) throws Exception {
        data = Output.getData(file);
        client.getOs().writeObject(data);
        client.getOs().flush();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int nbTemp = Input.countLine(file);
                if(nbline < nbTemp) {
                    for (Client c : Main.clients) {
                        if(file.contains(c.getRoom().toUpperCase())) {
                            sendMessage(c);
                        }
                    }
                    nbline = nbTemp;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
