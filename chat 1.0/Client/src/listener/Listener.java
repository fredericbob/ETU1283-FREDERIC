package listener;

import ioFile.SendFile;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Listener implements ActionListener {
    JTextField message;
    JTextField path;
    DataOutputStream os;
    DataOutputStream osFile;
    String msgTxt;
    File file;

    public Listener(JTextField textField, JTextField path, Socket socket, Socket osFile) throws IOException {
        message = textField;
        this.path = path;
        os = new DataOutputStream(socket.getOutputStream());
        this.osFile = new DataOutputStream(osFile.getOutputStream());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("message")) {
            msgTxt = " "+message.getText();
            try {
                os.writeUTF(msgTxt);
                os.flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            message.setText("");
        } else if(e.getActionCommand().equals("file")) {
            file = new File(path.getText());
            try {
                SendFile.sendImage(file, path.getText(), osFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            path.setText("");
        }
    }
}
