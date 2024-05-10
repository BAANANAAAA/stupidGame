package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level6_2 extends Level{
    public Level6_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level6_2", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level6.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getCarpet();
        getCrossLabel();
        addMagicCircleInteraction();
    }

    private void getCrossLabel() {
        ImageIcon crossIcon = new ImageIcon("figs/cross.PNG");
        Image crossImage = crossIcon.getImage().getScaledInstance(40, 95, Image.SCALE_SMOOTH);
        crossIcon = new ImageIcon(crossImage);
        JLabel crossLabel = new JLabel(crossIcon);
        crossLabel.setBounds(385, 235,
                crossIcon.getIconWidth(), crossIcon.getIconHeight());

        crossLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        crossLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(3);
                player.showTemporaryMessage("Holding it stings.");
                layeredPane.remove(crossLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(crossLabel, Integer.valueOf(2));
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (player.hasAccessTo("Level3_2")) {
                    player.GoTo("Level3_2");
                } else {
                    player.showTemporaryMessage("Access to Level3 is denied.");
                }
                break;
            case KeyEvent.VK_DOWN:
                if (player.hasAccessTo("Level7")) {
                    player.GoTo("Level7");
                }
                break;
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
            JOptionPane.showMessageDialog(layeredPane, "You fled! However, the house comes into your dream...");
            System.exit(0);
        }
    }

}
