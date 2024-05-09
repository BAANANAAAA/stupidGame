package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level1 extends Level {
    private JPanel vasePanel;
    private final String correctPassword = "secret"; // Correct password to proceed to level2
    private String cookie = "";

    public Level1(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        this.player = player;
        init();
        player.insertLevel("Level1", this);
        player.addAccessTo("Level1");
    }

    public void init() {
        ImageIcon backgroundImage = new ImageIcon("figs/level1.PNG");
        JLabel label = new JLabel(backgroundImage);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getRedGemLabel();
        getHintButton();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // Check access and navigate
                if (player.hasAccessTo("Level2")) {
                    player.GoTo("Level2");
                } else {
                    player.showTemporaryMessage("Access to Level2 is denied.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                // Check access and navigate
                if (player.hasAccessTo("Level5")) {
                    player.GoTo("Level5");
                } else {
                    player.showTemporaryMessage("Access to Level5 is denied.");
                }
                break;
            // More key handling
        }
    }

    private void getRedGemLabel() {
        ImageIcon RedGemIcon = new ImageIcon("figs/red_gem.PNG");
        Image redGemImage = RedGemIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        RedGemIcon = new ImageIcon(redGemImage);
        JLabel RedGemLabel = new JLabel(RedGemIcon);
        RedGemLabel.setBounds(52, 405,
                RedGemIcon.getIconWidth(), RedGemIcon.getIconHeight());

        RedGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RedGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(10);
                player.showTemporaryMessage("OHHHH! Is that a...red gem?");
                layeredPane.remove(RedGemLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(RedGemLabel, Integer.valueOf(2));
    }

    private void getHintButton() {
        JButton hintButton = new JButton();
        hintButton.setBounds(380, 360, 50, 80);
        hintButton.setOpaque(false);
        hintButton.setContentAreaFilled(false);
        hintButton.setBorderPainted(false);
        hintButton.setFocusPainted(false);
        hintButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hintButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               showVaseImage();
            }
        });

        layeredPane.add(hintButton, Integer.valueOf(2));
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
        vasePanel.setBounds((contentWidth - width) / 2 - 10, (contentHeight - height) / 2 - 10, width + 20, height + 60);

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
        if (enteredPassword.equals(correctPassword)) {
            // Correct password entered, proceed to level2
            closeVasePanel();
            JOptionPane.showMessageDialog(frame, "Yes, indeed..." + cookie, "Wow", JOptionPane.WARNING_MESSAGE);
            cookie += "a";
//            player.GoTo("Level2");
            player.addAccessTo("Level2");
        } else {
            // Incorrect password, show error message
            JOptionPane.showMessageDialog(frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
