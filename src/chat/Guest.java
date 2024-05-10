package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import javax.swing.*;

public class Guest extends ChatPanel {

    // Socket
    String serverIP = null;

    public Guest(JPanel _panel, User _user, String _ip) {

        super(_panel, _user);
        serverIP = _ip;
        panel.revalidate();
        panel.repaint();

        try {
            socket = new Socket(serverIP, PORT_NUM);
            receiveStream = new DataInputStream(socket.getInputStream());
            sendStream = new DataOutputStream(socket.getOutputStream());

            showMessage(user.getUsername() + " entered the room.\n");
            sendMessage(user.getUsername() + " entered the room.\n");

            Thread t = new Thread(this);
            t.start();
        } catch (IOException e) {
            System.err.println("Connection failed." + e.getMessage());
        }
    }
}

