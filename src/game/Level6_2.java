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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (player.hasAccessTo("Level3_2")) {
                player.GoTo("Level3_2");
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

}
