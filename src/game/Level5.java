package game;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level5 extends Level{
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

        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                player.GoTo("Level6");
            }
        });
    }


}
