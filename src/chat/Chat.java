package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipalNotFoundException;

public class Chat {

    private JPanel panel;
    private User user;

    // Login
    private JTextField usernameField;
    private JTextField passwordField;

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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("Login button clicked!");
                String username = usernameField.getText();
                String password = passwordField.getText();
//                System.out.println("Username: " + username);
//                System.out.println("Password: " + password);
                tryLogin(username, password);
            }
        });
    }

    private void tryLogin(String username, String password) {
        user = Database.login(username, password); // 获取自己的user对象
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

        createRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Room button clicked!");
                String identification = Database.createRoom(user.getUid());
                if (identification != null) {
                    System.out.println("Room created. ID: " + identification);
                    // TODO: Enter chat room, start server
                    new Server(panel, user);
                } else {
                    System.out.println("Failed to create room.");
                }
            }
        });

        // Join Room
        gbc.gridy++;
        JTextField identificationField = new JTextField(20);
        panel.add(identificationField, gbc);
        gbc.gridy++;
        JButton joinRoomButton = new JButton("Join Room");
        panel.add(joinRoomButton, gbc);

        joinRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Join Room button clicked!");
                String identification = identificationField.getText();
                System.out.println(identification);
                String ipAddress = Database.joinRoom(user.getUid(), identification);
                if (ipAddress != null) {
                    System.out.println("Room joined. ip: " + ipAddress);
                    // TODO: Enter chat room, start client
                    new Client(panel, user);
                } else {
                    System.out.println("Failed to join room.");
                }
            }
        });

        panel.revalidate();
        panel.repaint();
    }
}
