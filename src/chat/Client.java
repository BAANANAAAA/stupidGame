package chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormatSymbols;

import javax.crypto.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;


public class Client extends ChatPanel implements Runnable {

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

    public Client(JPanel _panel, User _user) {

        super(_panel);
        user = _user;

//        Thread t = new Thread(this);
//        t.start();
    }

    @Override
    public void run() {

    }
}

