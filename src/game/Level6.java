package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level6 extends Level{
    public Level6(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level6", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level6.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getCarpet();
    }

    private void getCarpet() {
        ImageIcon carpetIcon = new ImageIcon("figs/carpet.PNG");
        Image keyImage = carpetIcon.getImage().getScaledInstance(640, 610, Image.SCALE_SMOOTH);
        carpetIcon = new ImageIcon(keyImage);
        JLabel carpetLabel = new JLabel(carpetIcon);
        carpetLabel.setBounds(220, 150,
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
