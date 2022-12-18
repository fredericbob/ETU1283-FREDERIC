package ioStream;

import java.io.BufferedReader;
import java.io.FileReader;

import static ioStream.Input.countLine;

public class Output {

    public static String[][] getData(String fichier) throws Exception{
        String filename = fichier;

        int nbLine = countLine(filename);
        String[][] data = new String[nbLine][2];

        FileReader read = new FileReader(filename);
        BufferedReader br = new BufferedReader(read);
        int i=0;
        String line = br.readLine();
        while(line != null){
            data[i] = line.split(":::");
            line = br.readLine();
            i++;
        }
        return data;
    }
}
