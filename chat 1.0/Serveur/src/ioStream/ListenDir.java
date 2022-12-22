package ioStream;

import client.Client;
import main.Main;

import java.io.File;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListenDir extends Thread{

    private int nbFile=0;
    File dir;
    List<String> allFile = new ArrayList<>();

    public ListenDir(File dir) {
        this.dir = dir;
        
        start();
    }


    public void sendMessage(Client client, File file) throws Exception {
        Path path = Paths.get(file.getAbsolutePath());
        byte[] byteArray = Files.readAllBytes(path);
        client.getOsFile().writeUTF(file.getName());
        client.getOsFile().write(byteArray.length);
        client.getOsFile().write(byteArray);
        client.getOsFile().flush();
    }

    @Override
    public void run() {
        File[] file;
        int i=0;
        while (true) {
            try {
                int nbTemp = dir.listFiles().length;
                if(nbFile < nbTemp) {
                    file = dir.listFiles();

                    for (File f : file) {
                        for (Client c : Main.clients) {
                            if (f.getAbsolutePath().contains(c.getRoom())) {
                                sendMessage(c, f);
                                System.out.println("BroadCast File");
                            }
                        }
                        f.delete();
                        System.out.println("Temp file deleted");
                    }
                    nbFile = nbTemp;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
