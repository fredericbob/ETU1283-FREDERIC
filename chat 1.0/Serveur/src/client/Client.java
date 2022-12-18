package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String nom;
    private String room;
    private DataInputStream in;
    private ObjectOutputStream os;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOs() {
        return os;
    }

    public void setOs(ObjectOutputStream os) {
        this.os = os;
    }
}
