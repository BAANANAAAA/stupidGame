package game;

import javax.swing.*;
import java.awt.*;
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
        leftButton.setBounds(190, contentHeight / 2 - 50, 50, 50);
        leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftButton.setBorderPainted(false);
        leftButton.setContentAreaFilled(false);
        leftButton.setFocusPainted(false);
        leftButton.setOpaque(false);
        leftButton.addActionListener(e -> handleButtonPress(1));

        // right button
        JButton rightButton = new JButton();
        rightButton.setBounds(550, contentHeight / 2 - 50, 50, 50);
        rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightButton.setBorderPainted(false);
        rightButton.setContentAreaFilled(false);
        rightButton.setFocusPainted(false);
        rightButton.setOpaque(false);
        rightButton.addActionListener(e -> handleButtonPress(2));

        JButton resetButton = new JButton();
        resetButton.setBounds(contentWidth / 2 - 50, 270, 50, 50);
        resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        resetButton.setOpaque(false);
        resetButton.addActionListener(e -> resetSequence());

        layeredPane.add(leftButton, Integer.valueOf(2));
        layeredPane.add(rightButton, Integer.valueOf(2));
        layeredPane.add(resetButton, Integer.valueOf(2));
    }

    private void handleButtonPress(int buttonId) {
        userSequence.add(buttonId);
        if (userSequence.equals(correctSequence)) {
            JOptionPane.showMessageDialog(layeredPane, "Something is open...");
            userSequence.clear();
        }
    }

    private void resetSequence() {
        userSequence.clear();
    }
}