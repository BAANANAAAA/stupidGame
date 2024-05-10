package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level4_2 extends Level {

    public Level4_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level4_2", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level4_2.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getBurningLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (player.hasAccessTo("Level2_2")) {
                player.GoTo("Level2_2");
            } else {
                player.showTemporaryMessage("Access to Level2 is denied.");
            }
        }
    }

    private void getBurningLabel() {
        ImageIcon burningIcon = new ImageIcon("figs/burning.PNG");
        Image burningImage = burningIcon.getImage().getScaledInstance(60, 140, Image.SCALE_SMOOTH);
        burningIcon = new ImageIcon(burningImage);
        JLabel burningLabel = new JLabel(burningIcon);
        burningLabel.setBounds(395, 218,
                burningIcon.getIconWidth(), burningIcon.getIconHeight());

        burningLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        burningLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(player.hasItem(2)){
                    player.showTemporaryMessage("Flame extinguished.");
                    player.addAccessTo("Level3_2");
                    player.addAccessTo("Level6_2");
                    layeredPane.remove(burningLabel);
                    layeredPane.repaint();
                }
                else {
                    player.showTemporaryMessage("Mirror, flaming hot!");
                }
            }
        });

        layeredPane.add(burningLabel, Integer.valueOf(2));
    }
}
