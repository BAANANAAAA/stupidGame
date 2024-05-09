package game;

import javax.swing.*;

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
    }


}
