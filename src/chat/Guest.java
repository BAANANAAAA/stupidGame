package chat;

import java.net.ServerSocket;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class Guest extends ChatPanel implements Runnable {

    // Socket
    private static final int PORT_NUM = 9898;
    ServerSocket serverSocket = null;


    public Guest(JPanel _panel, User _user) {

        super(_panel, _user);

//        Thread t = new Thread(this);
//        t.start();
    }

    @Override
    public void run() {

    }
}

