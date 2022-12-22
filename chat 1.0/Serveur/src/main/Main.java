package main;

import chat.Chat;
import chat.SaveTempFile;
import client.Client;
import ioStream.Input;
import ioStream.ListenDir;
import ioStream.listenFile;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int port = 11111;
    private static final int portFile = 11112;
    public static List<Client> clients = new ArrayList<>();
    public static String dossier="C:\\Users\\frédéric\\Desktop\\DATA\\message\\";
    public static String dossierFile="C:\\Users\\frédéric\\Desktop\\DATA\\temp\\";


    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        ServerSocket serverSocketFile = new ServerSocket(portFile);
        System.out.println("Serveur pret");
        File f;
        while (true) {
            Socket sc = serverSocket.accept();
            Socket scFile = serverSocketFile.accept();
            DataInputStream in = new DataInputStream(sc.getInputStream());

            String nom = in.readUTF();
            String room = in.readUTF();

            Client c = new Client();
            c.setOs(new ObjectOutputStream(sc.getOutputStream()));
            c.setIn(new DataInputStream(sc.getInputStream()));
            c.setNom(nom);
            c.setRoom(room.toUpperCase());
            c.setOsFile(new DataOutputStream(scFile.getOutputStream()));

            if(!findIfgroupeExist(c)) {
                f = new File(Main.dossierFile+c.getRoom());
                f.mkdir();
                Input.createFile(new File(dossier+c.getRoom()+".txt"));
                new listenFile(c.getRoom());
                new ListenDir(f);
            }
            clients.add(c);
            new Chat(c);
            new SaveTempFile(c);
        }
    }

    private static boolean findIfgroupeExist(Client client) {
        for (Client c: Main.clients) {
            if(c.getRoom() .equals(client.getRoom())) {
                return true;
            }
        }
        return false;
    }
}
