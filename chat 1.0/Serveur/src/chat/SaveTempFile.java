package chat;

import client.Client;
import main.Main;

import java.io.*;

public class SaveTempFile extends Thread{

    DataInputStream in;
    Client c;

    public SaveTempFile(Client client) {
        in = client.getInFile();
        c = client;

        start();
    }

    @Override
    public void run() {
        while (true) {
            int size = 0;
            try {
                String name = in.readUTF();
                System.out.println("Begin receive");
                size = in.read();
                byte[] fileByte = new byte[size];
                int totalRead = 0;
                int currentRead;
                while (totalRead <= size && (currentRead = in.read(fileByte, totalRead, size-totalRead)) > 0) {
                    System.out.println(currentRead);
                    totalRead += currentRead;
                }

                System.out.println(Main.dossierFile+c.getRoom()+"\\"+name);
                File file = new File(Main.dossierFile+c.getRoom()+"\\"+name);
                OutputStream os = new FileOutputStream(file);
                os.write(fileByte);
                os.flush();
                System.out.println("Succes");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
