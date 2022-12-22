package ioFile;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SendFile {
    public static void sendImage(File file, String stringPath, DataOutputStream os) throws IOException {
        Path path = Paths.get(stringPath);

        byte[] byteArray = Files.readAllBytes(path);
        os.writeUTF(file.getName());
        os.write(byteArray.length);
        os.write(byteArray);
        os.flush();
        System.out.println("Send ---------");
    }
}
