package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level6_1 extends Level{
    public Level6_1(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level6_1", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level6.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getCarpet();
        addMagicCircleInteraction();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (player.hasAccessTo("Level3_1")) {
                player.GoTo("Level3_1");
            } else {
                player.showTemporaryMessage("Access to Level3 is denied.");
            }
        }
    }

    private void getCarpet() {
        ImageIcon carpetIcon = new ImageIcon("figs/carpet.PNG");
        Image keyImage = carpetIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        carpetIcon = new ImageIcon(keyImage);
        JLabel carpetLabel = new JLabel(carpetIcon);
        carpetLabel.setBounds(200, 540,
                carpetIcon.getIconWidth(), carpetIcon.getIconHeight());
        carpetLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(6);
                player.showTemporaryMessage("O! Something is beneath..!");
                layeredPane.remove(carpetLabel);
                layeredPane.repaint();
            }
        });
        layeredPane.add(carpetLabel, Integer.valueOf(2));
    }

    private void addMagicCircleInteraction() {
        JLabel magicCircleArea = new JLabel();
        magicCircleArea.setBounds(400, 600, 100, 100);
        magicCircleArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        magicCircleArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(player.hasItem(6)){
                    triggerMagicCircle();
                }
            }
        });
        layeredPane.add(magicCircleArea, Integer.valueOf(2));
    }

    private void triggerMagicCircle() {
        int choice = JOptionPane.showConfirmDialog(layeredPane, "Do you want to use the magic circle?",
                "Magic Circle", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            if (player.hasItem(14)) {
                JOptionPane.showMessageDialog(layeredPane, "Congratulations! You've completed the game!");
                System.exit(0);
            } else {
                asktwice();
            }
        }
    }

    private void asktwice() {
        int choice = JOptionPane.showConfirmDialog(layeredPane,
                "You want to use the magic circle??", "Magic Circle", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        asktwice();
    }

}