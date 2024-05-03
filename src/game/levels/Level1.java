package game.levels;

import game.utils.Player;
import game.utils.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level1 extends Level {

    private JPanel vasePanel; // Panel to display the vase image
    private final String correctPassword = "secret"; // Correct password to proceed to level2
    private String cookie = "";

    public Level1(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        this.player = player;
        init();
        player.insertLevel("Level1", this);

        // 添加按下ESC键时显示菜单的功能
        layeredPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "showMenu");
        layeredPane.getActionMap().put("showMenu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu();
            }
        });

    }

    public void init() {
        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("figs/level1.PNG");
        layeredPane = new JLayeredPane() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //                // Draw the background image to cover the entire panel
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        layeredPane.setPreferredSize(frame.getSize());

        layeredPane.setLayout(new BorderLayout());

        // Setup hint label but don't add it yet
        // Label to display hints at the bottom of the screen
        JLabel hintLabel = new JLabel(" ", SwingConstants.CENTER);
        hintLabel.setOpaque(true);
        hintLabel.setBackground(Color.WHITE);
        hintLabel.setPreferredSize(new Dimension(frame.getWidth(), 30));

        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Assuming the clickable area is in the center of the screen
                Rectangle vaseArea = new Rectangle(350, 250, 100, 100);
                if (vaseArea.contains(e.getPoint())) {
                    showVaseImage(); // Show vase image on the same frame
                }
            }
        });

        SoundPlayer.playSound("resources/sounds/horror_bg_0.wav", 200000);
    }

    @Override
    void restartLevel() {
        // 重置游戏状态
        // ...
        player.GoTo("Level1");
        // 重新开始游戏循环
        // ...
        System.out.println("Try to set restart here");
    }

    private void showVaseImage() {
        // Load the vase image
        ImageIcon vaseImageIcon = new ImageIcon("figs/image.png");

        // Calculate the size and position for the vase image (let's say it's 200x200 pixels)
        int width = 200;
        int height = 200;
        Image vaseImage = vaseImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        vaseImageIcon = new ImageIcon(vaseImage); // Convert back to ImageIcon for the label

        // Create a label to show the vase image
        JLabel vaseLabel = new JLabel(vaseImageIcon);

        // Create a panel to contain the vase label and the input field, with a little padding
        vasePanel = new JPanel(new BorderLayout(10, 10));
        vasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vasePanel.setBackground(Color.WHITE); // Set the background to white

        // Set the size and position of the panel (in the center of the frame)
        vasePanel.setBounds((frame.getWidth() - width) / 2 - 10, (frame.getHeight() - height) / 2 - 10, width + 20, height + 60);

        // Create the input field and submit button
        JTextField passwordField = new JTextField(10); // Field for entering password
        JButton submitButton = new JButton("Submit");

        // Add listener to the submit button
        submitButton.addActionListener(e -> verifyPassword(passwordField.getText()));

        // Add mouse listener to the panel to close it on click
        vasePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeVasePanel();
            }
        });

        // Create a panel for the input field and submit button
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(passwordField);
        inputPanel.add(submitButton);

        // Add components to the vase panel
        vasePanel.add(vaseLabel, BorderLayout.CENTER);
        vasePanel.add(inputPanel, BorderLayout.SOUTH);

        // Add the vase panel to the frame's layered pane, so it can float over other components
        frame.getLayeredPane().add(vasePanel, JLayeredPane.MODAL_LAYER);
        frame.getLayeredPane().moveToFront(vasePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void closeVasePanel() {
        frame.getLayeredPane().remove(vasePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void verifyPassword(String enteredPassword) {
        SoundPlayer.playSound("resources/sounds/click_0.wav", 0);
        if (enteredPassword.equals(correctPassword)) {
            // Correct password entered, proceed to level2
            closeVasePanel();
            JOptionPane.showMessageDialog(frame, "Yes, indeed..." + cookie, "Wow", JOptionPane.WARNING_MESSAGE);
            cookie += "a";
            player.GoTo("Level2");
        } else {
            // Incorrect password, show error message
            JOptionPane.showMessageDialog(frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
