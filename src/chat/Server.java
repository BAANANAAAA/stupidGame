package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;

import java.security.*;
import java.text.SimpleDateFormat;


public class Server extends ChatPanel implements Runnable {

    // Socket
    private static final int PORT_NUM = 9898;
    ServerSocket serverSocket = null;

    // JPanel
    JPanel panel;
    User user;
    JTextArea messageArea;
    JTextField inputField;
    JScrollBar scrollBar;

    Date currentDate;
    SimpleDateFormat sdf;
    String dateFormat = "E MMM dd HH:mm:ss z yyyy";

    public Server(JPanel _panel, User _user) {

        super(_panel);
        user = _user;

//        Thread t = new Thread(this);
//        t.start();
    }

    @Override
    public void run() {

    }
}


