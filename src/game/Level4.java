package game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level4 extends Level{
    public Level4(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level4", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level4.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.GoTo("Level5");
            }
        });
    }


}
