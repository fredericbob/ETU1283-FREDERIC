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
    private DataOutputStream osFile;
    private DataInputStream inFile;

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

    public DataOutputStream getOsFile() {
        return osFile;
    }

    public void setOsFile(DataOutputStream osFile) {
        this.osFile = osFile;
    }

    public DataInputStream getInFile() {
        return inFile;
    }

    public void setInFile(DataInputStream inFile) {
        this.inFile = inFile;
    }
}
