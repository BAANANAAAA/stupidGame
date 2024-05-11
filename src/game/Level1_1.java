package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level1_1 extends Level {
    private JPanel vasePanel;
    private final String correctPassword = "TRINITY"; // Correct password to proceed to level2
    private String cookie = "";

    public Level1_1(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        this.player = player;
        init();
        player.insertLevel("Level1_1", this);
        player.addAccessTo("Level1_1");
        player.addAccessTo("Level5_1");
    }

    public void init() {
        ImageIcon backgroundImage = new ImageIcon("figs/level1.PNG");
        JLabel label = new JLabel(backgroundImage);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getRedGemLabel();
        getHintButton();

        getKeyLabel();
    }


    private void getKeyLabel() {
        ImageIcon keyIcon = new ImageIcon("figs/key1.PNG");
        Image keyImage = keyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        keyIcon = new ImageIcon(keyImage);
        JLabel keyLabel = new JLabel(keyIcon);
        keyLabel.setBounds(255, 220, keyIcon.getIconWidth(), keyIcon.getIconHeight());

        keyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        keyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(2);
                player.showTemporaryMessage("EWW! That's a key!");
                layeredPane.remove(keyLabel);
                layeredPane.repaint();
            }
        });
        layeredPane.add(keyLabel, Integer.valueOf(2));
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (player.hasAccessTo("Level2_1")) {
                    player.GoTo("Level2_1");
                } else {
                    player.showTemporaryMessage("Access to Level2 is denied.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (player.hasAccessTo("Level5_1")) {
                    player.GoTo("Level5_1");
                } else {
                    player.showTemporaryMessage("Access to Level5 is denied.");
                }
                break;
        }
    }

//    @Override
//    void goUp() {
//
//    }
//
//    @Override
//    void goDown() {
//
//    }
//
//    @Override
//    void goRight() {
//        if (player.hasAccessTo("Level5_1")) {
//            player.GoTo("Level5_1");
//        } else {
//            player.showTemporaryMessage("Access to Level5 is denied.");
//        }
//    }
//
//    @Override
//    void goLeft() {
//        if (player.hasAccessTo("Level2_1")) {
//            player.GoTo("Level2_1");
//        } else {
//            player.showTemporaryMessage("Access to Level2 is denied.");
//        }
//    }

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
        ImageIcon vaseImageIcon = new ImageIcon("figs/code.png");

        Image vaseImage = vaseImageIcon.getImage().getScaledInstance(280, 200, Image.SCALE_SMOOTH);
        vaseImageIcon = new ImageIcon(vaseImage);

        JLabel vaseLabel = new JLabel(vaseImageIcon);

        vasePanel = new JPanel(new BorderLayout(10, 10));
        vasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vasePanel.setBackground(Color.WHITE); // Set the background to white

        vasePanel.setBounds(450, 280, 300,240);

        JTextField passwordField = new JTextField(10); // Field for entering password
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> verifyPassword(passwordField.getText()));

        vasePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeVasePanel();
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(passwordField);
        inputPanel.add(submitButton);

        vasePanel.add(vaseLabel, BorderLayout.CENTER);
        vasePanel.add(inputPanel, BorderLayout.SOUTH);

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
            closeVasePanel();
            player.showTemporaryMessage("O! Look behind then.");
            cookie += "a";
            player.addAccessTo("Level2_1");
        } else {
            player.showTemporaryMessage("Maybe not..");
        }
    }

}
