package listener;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Listener implements ActionListener {
    JTextField message;
    DataOutputStream os;

    public Listener(JTextField textField, Socket socket) throws IOException {
        message = textField;
        os = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msgTxt = " "+message.getText();
        try {
            os.writeUTF(msgTxt);
            os.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        message.setText("");
    }
}
