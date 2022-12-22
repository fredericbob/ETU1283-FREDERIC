package message;

import listener.Listener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class Messenger extends JFrame {
    JButton send;
    JButton sendFile;
    JScrollPane jScrollPane;
    JTextArea allMessage;
    JTextField inputMessage;
    JTextField inputFile;

    public Messenger(Socket socket, Socket socketFile) throws IOException {
        inputMessage = new JTextField();
        send = new JButton("Send");
        inputFile = new JTextField();
        sendFile = new JButton("Send File");
        jScrollPane = new JScrollPane();
        allMessage = new JTextArea();

        initFrame();
        Listener listener = new Listener(inputMessage, inputFile, socket, socketFile);
        send.setActionCommand("message");
        send.addActionListener(listener);
        sendFile.setActionCommand("file");
        sendFile.addActionListener(listener);

        inputMessage.setBounds(20,50,300,40);
        add(inputMessage);

        send.setBounds(inputMessage.getWidth()+50, 53, 100,30);
        send.setFocusable(false);
        add(send);

        inputFile.setBounds(20, 95,300,25);
        add(inputFile);

        sendFile.setBounds(inputFile.getWidth()+50, 95,100, 25);
        sendFile.setFocusable(false);
        add(sendFile);

        allMessage.setEditable(false);
        jScrollPane.setViewportView(allMessage);
        jScrollPane.setPreferredSize(new Dimension(300,320));
        jScrollPane.setBounds(20,145, (int) jScrollPane.getPreferredSize().getWidth(), (int) jScrollPane.getPreferredSize().getHeight());
        add(jScrollPane);

        setVisible(true);
        new Message(allMessage, socket);
    }

    private void initFrame() {
        setTitle("Chat 1.0");
        setLayout(null);
        setSize(500,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
