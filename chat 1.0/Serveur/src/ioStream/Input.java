package ioStream;

import main.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Input {

    public static int countLine(String filename) throws Exception{
        Path path = Paths.get(filename);
        int nbLine = 0;

        nbLine = (int) Files.lines(path).count();
        return nbLine;
    }

    public static void createFile(File file) throws IOException {
        if(!file.exists()) {
            file.createNewFile();
        }
    }

    public static void messageToTxt(String message,String fichier)throws Exception {
        File file = new File(Main.dossier+fichier.toUpperCase()+".txt");

        FileWriter write = new FileWriter(file,true);
        FileReader read = new FileReader(file);
        BufferedReader r_file = new BufferedReader(read);
        BufferedWriter donne = new BufferedWriter(write);

        String verif = r_file.readLine();
        if(verif != null){
            donne.write("\n");
        }

        donne.write(message);

        donne.close();
    }
}
