package main;

import message.Messenger;

import javax.swing.JOptionPane;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String ip = JOptionPane.showInputDialog("Entrer l'Adresse Ip du sereur");
        String nom = JOptionPane.showInputDialog("Entrer Votre nom");
        String room = JOptionPane.showInputDialog("Entrer le nom de votre room");

        Socket socket = new Socket(ip, 11111);
        System.out.println("En attente de reponse du serveur ");
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connection reussit");
        os.writeUTF(nom);
        os.writeUTF(room);

        new Messenger(socket);
    }
}
