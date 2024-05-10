package chat;

import java.net.ServerSocket;
import java.util.Date;

import javax.swing.*;

import java.text.SimpleDateFormat;


public class Host extends ChatPanel implements Runnable {

    // Socket
    private static final int PORT_NUM = 9898;
    ServerSocket serverSocket = null;

    public Host(JPanel _panel, User _user) {

        super(_panel, _user);

//        Thread t = new Thread(this);
//        t.start();
    }

    @Override
    public void run() {

    }
}


