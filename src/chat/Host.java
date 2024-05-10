package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;

import javax.swing.*;

import java.text.SimpleDateFormat;

public class Host extends ChatPanel implements Runnable {

    public ServerSocket serverSocket = null;

    public Host(JPanel _panel, User _user, String _id) {

        super(_panel, _user);

        showMessage("Your room id is " + _id + ".\n");

        System.out.println("ChatPanel Initialized.");
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT_NUM);
                socket = serverSocket.accept();
                receiveStream = new DataInputStream(socket.getInputStream());
                sendStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.err.println("Server socket failed." + e.getMessage());
            }

            Thread t = new Thread(this);
            t.start();
        }).start();
/*
        try {
            serverSocket = new ServerSocket(PORT_NUM);
            socket = serverSocket.accept();
            receiveStream = new DataInputStream(socket.getInputStream());
            sendStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("Server socket failed." + e.getMessage());
        }

        Thread t = new Thread(this);
        t.start();
*/
    }
}


