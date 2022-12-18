package main;

import chat.Chat;
import client.Client;
import ioStream.Input;
import ioStream.listenFile;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int port = 11111;
    public static List<Client> clients = new ArrayList<>();

    //Dossier des registre de message
    public static String dossier="C:\\Users\\frédéric\\Desktop\\Message\\";


    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Serveur pret");

        while (true) {
            Socket sc = serverSocket.accept();
            DataInputStream in = new DataInputStream(sc.getInputStream());

            String nom = in.readUTF();
            String room = in.readUTF();

            Client c = new Client();
            c.setOs(new ObjectOutputStream(sc.getOutputStream()));
            c.setIn(new DataInputStream(sc.getInputStream()));
            c.setNom(nom);
            c.setRoom(room.toUpperCase());

            if(!findIfgroupeExist(c)) {
                Input.createFile(new File(dossier+c.getRoom().toUpperCase()+".txt"));
                new listenFile(c.getRoom());
            }
            clients.add(c);
            new Chat(c);
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
