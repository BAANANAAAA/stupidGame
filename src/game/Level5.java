package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Level5 extends Level {
    private final ArrayList<Integer> userSequence = new ArrayList<>();
    private final ArrayList<Integer> correctSequence = new ArrayList<>(Arrays.asList(1, 2, 1));

    public Level5(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level5", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level5.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        // left button
        JButton leftButton = new JButton();
        leftButton.setBounds(190, 200, 50, 50);
        leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.setOpaque(false);
        leftButton.addActionListener(e -> handleButtonPress(1));

        // right button
        JButton rightButton = new JButton();
        rightButton.setBounds(550, 200, 50, 50);
        rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.setOpaque(false);
        rightButton.addActionListener(e -> handleButtonPress(2));

        JButton resetButton = new JButton();
        resetButton.setBounds(380, 270, 50, 50);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        resetButton.setOpaque(false);
        resetButton.addActionListener(e -> resetSequence());

        layeredPane.add(leftButton, Integer.valueOf(2));
        layeredPane.add(rightButton, Integer.valueOf(2));
        layeredPane.add(resetButton, Integer.valueOf(2));

        getHammerLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (player.hasAccessTo("Level1")) {
                player.GoTo("Level1");
            } else {
                player.showTemporaryMessage("Access to Level1 is denied.");
            }
        }
    }

    private void handleButtonPress(int buttonId) {
        userSequence.add(buttonId);
        if (userSequence.equals(correctSequence)) {
            JOptionPane.showMessageDialog(layeredPane, "O, yes, the orange gem...the final one");
            player.addItemToPackage(14);
            userSequence.clear();
        }
    }

    private void resetSequence() {
        userSequence.clear();
    }

    private void getHammerLabel() {
        ImageIcon hammerIcon = new ImageIcon("figs/hammer0.PNG");
        Image hammerImage = hammerIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 调整大小为50x50
        hammerIcon = new ImageIcon(hammerImage);
        JLabel hammerLabel = new JLabel(hammerIcon);
        hammerLabel.setBounds(425, 260,
                hammerIcon.getIconWidth(), hammerIcon.getIconHeight());
        hammerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        hammerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(3);
                player.showTemporaryMessage("Maybe it can break something?..");
                layeredPane.remove(hammerLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(hammerLabel, Integer.valueOf(2));
    }
}
