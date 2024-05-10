package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Level5_2 extends Level {
    private final ArrayList<Integer> userSequence = new ArrayList<>();
    private final ArrayList<Integer> correctSequence = new ArrayList<>(Arrays.asList(1,2,1,1,2));

    public Level5_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level5_2", this);
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

        getOrangeGemHintLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (player.hasAccessTo("Level1_2")) {
                player.GoTo("Level1_2");
            } else {
                player.showTemporaryMessage("Access to Level1 is denied.");
            }
        }
    }

    private void handleButtonPress(int buttonId) {
        userSequence.add(buttonId);
        if (userSequence.equals(correctSequence)) {
            player.showTemporaryMessage("Far comes a crack...");
            player.addAccessTo("Level7");
            userSequence.clear();
            layeredPane.requestFocus();
            layeredPane.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    handleKeyInput(e);
                }
            });
        }
    }

    private void resetSequence() {
        userSequence.clear();
        layeredPane.requestFocus();
        layeredPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyInput(e);
            }
        });
    }

    private void getOrangeGemHintLabel() {
        ImageIcon RedGemIcon = new ImageIcon("figs/wallhint1.PNG");
        Image redGemImage = RedGemIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        RedGemIcon = new ImageIcon(redGemImage);
        JLabel RedGemLabel = new JLabel(RedGemIcon);
        RedGemLabel.setBounds(300, 360,
                RedGemIcon.getIconWidth(), RedGemIcon.getIconHeight());

        RedGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RedGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.showParchmentHint("In the dance of light and shadow\nGo to bright, dim, then bright\nTo unveil the jewel kissed by the sun");
            }
        });

        layeredPane.add(RedGemLabel, Integer.valueOf(2));
    }
}
