package chat;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat {

    private final JPanel panel;
    private User user;

    // Login
    private final JTextField usernameField;
    private final JTextField passwordField;
    private final String server = "192.168.1.27";
    private final int port = 12345;

    public Chat(JPanel _panel) {
        panel = _panel;
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username: ");
        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password: ");
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        passwordField = new JTextField(20);
        panel.add(passwordField, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        panel.add(loginButton, gbc);

        loginButton.addActionListener(e -> {
//                System.out.println("Login button clicked!");
            String username = usernameField.getText();
            String password = passwordField.getText();
//                System.out.println("Username: " + username);
//                System.out.println("Password: " + password);
            tryLogin(username, password);
        });
    }

    private void tryLogin(String username, String password) {
        user = myLogin(username, password); // 获取自己的user对象
        if (user != null) {
            System.out.println("Logged in.");
            System.out.println("UID: " + user.getUid());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            showRoomOption();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private void showRoomOption() {
        panel.removeAll();

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间的间距

        System.out.println("Showing room option...");

        // Create Room
        JButton createRoomButton = new JButton("Create Room");
        panel.add(createRoomButton, gbc);

        createRoomButton.addActionListener(e -> {
            System.out.println("Create Room button clicked!");
            String identification = myCreateRoom(user.getUid());
            if (identification != null) {
                System.out.println("Room created. ID: " + identification);
                new Host(panel, user);
            } else {
                System.out.println("Failed to create room.");
            }
        });

        // Join Room
        gbc.gridy++;
        JTextField identificationField = new JTextField(20);
        panel.add(identificationField, gbc);
        gbc.gridy++;
        JButton joinRoomButton = new JButton("Join Room");
        panel.add(joinRoomButton, gbc);

        joinRoomButton.addActionListener(e -> {
            System.out.println("Join Room button clicked!");
            String identification = identificationField.getText();
            System.out.println(identification);
            String ipAddress = myJoinRoom(user.getUid(), identification);
            if (ipAddress != null) {
                System.out.println("Room joined. ip: " + ipAddress);
                new Guest(panel, user);
            } else {
                System.out.println("Failed to join room.");
            }
        });

        panel.revalidate();
        panel.repaint();
    }

    private User myLogin(String username, String password) {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // send request and get result
            String request = "login " + username + " " + password;
            out.println(request);
            String response = in.readLine();
            socket.close();

            // 接收到的格式为 "uid username password" 生成对应User实例并返回
            String[] parts = response.split(" ");
            return new User(Integer.parseInt(parts[0]), parts[1], parts[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Login failed.");
        return null;
    }

    private String myCreateRoom(int uid) {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // send request and get result
            String request = "createRoom " + uid;
            out.println(request);
            String response = in.readLine();
            socket.close();

            // 接收到的格式为 "identification" 返回即可
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Create failed.");
        return null;
    }

    private String myJoinRoom(int uid, String identification) {
        try {
            Socket socket = new Socket(server, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // send request and get result
            String request = "joinRoom " + uid + " " + identification;
            out.println(request);
            String response = in.readLine();
            socket.close();

            // 接收到的格式为 "ipAddress" 返回即可
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.err.println("Join failed.");
        return null;
    }
}
