package message;

import listener.Listener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Messenger extends JFrame {
    JButton send;
    JScrollPane jScrollPane;
    JTextArea allMessage;
    JTextField inputMessage;

    public Messenger(Socket socket) throws IOException {
        inputMessage = new JTextField();
        send = new JButton("Send");
        jScrollPane = new JScrollPane();
        allMessage = new JTextArea();

        initFrame();
        send.addActionListener(new Listener(inputMessage, socket));

        inputMessage.setBounds(20,50,300,40);
        add(inputMessage);

        send.setBounds(inputMessage.getWidth()+50, 50, 100,40);
        send.setFocusable(false);
        add(send);

        allMessage.setEditable(false);
        jScrollPane.setViewportView(allMessage);
        jScrollPane.setPreferredSize(new Dimension(300,320));
        jScrollPane.setBounds(20,120, (int) jScrollPane.getPreferredSize().getWidth(), (int) jScrollPane.getPreferredSize().getHeight());
        add(jScrollPane);

        setVisible(true);
        new Message(allMessage, socket);
    }

    private void initFrame() {
        setTitle("Chat 1.0");
        setLayout(null);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
