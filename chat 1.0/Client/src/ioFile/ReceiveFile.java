package ioFile;

import main.Main;

import java.io.*;

public class ReceiveFile extends Thread {
    DataInputStream in;

    public ReceiveFile(DataInputStream is) {
        this.in=is;

        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String name = in.readUTF();
                System.out.println("Receive file");
                int size = in.read();
                byte[] fileByte = new byte[size];
                int totalRead = 0;
                int currentRead;
                while (totalRead <= size && (currentRead = in.read(fileByte, totalRead, size-totalRead)) > 0) {
                    System.out.println(currentRead);
                    totalRead += currentRead;
                }

                File file = new File(Main.dir+name);
                OutputStream os = new FileOutputStream(file);
                os.write(fileByte);
                System.out.println("Received");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
