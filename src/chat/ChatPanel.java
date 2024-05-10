package chat;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class ChatPanel implements Runnable {

    JPanel panel;
    JTextArea messageArea;
    JTextField inputField;
    JScrollBar scrollBar;

    Date currentDate;
    SimpleDateFormat sdf;
    String dateFormat = "HH:mm:ss";

    User user;

    public Socket socket = null;
    public static final int PORT_NUM = 9898;
    public DataInputStream receiveStream = null;
    public DataOutputStream sendStream = null;

    public ChatPanel(JPanel _panel, User _user) {
        user = _user;
        panel = _panel;

        currentDate = new Date();
        sdf = new SimpleDateFormat(dateFormat, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));

        panel.removeAll();
        createDisplay();
        panel.revalidate();
        panel.repaint();
    }

    private void createDisplay() {
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar = scrollPane.getVerticalScrollBar();

        inputField = new JTextField();
        inputField.addActionListener(new inputListener());

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);
    }

    private String getCurrentTime() {
        return sdf.format(currentDate);
    }

    class inputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText();
            String message = "(" + getCurrentTime() + "): " + inputText +"\n";
            // show message on screen
            showMessage("Me" + message); // 显示自己的要加一个Me，但接收信息直接显示即可
            inputField.setText("");
            // send
            String outMessage = user.getUsername() + message;
            sendMessage(outMessage);
        }
    }

    public void showMessage(String msg) {
        messageArea.append(msg);
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public void sendMessage(String msg) {
        try {
            sendStream.writeUTF(msg);
            sendStream.flush();
        } catch (IOException ex) {
            System.err.println("Failed to send message." + ex.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String inString = receiveStream.readUTF();
                showMessage(inString);
            }
        } catch (IOException e) {
            System.err.println("Failed to read message." + e.getMessage());
        }
    }
}
