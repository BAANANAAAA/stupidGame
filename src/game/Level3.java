package game;

import javax.swing.*;

public class Level3 extends Level {

    public Level3(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level3", this);
    }

    public void init() {
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frame.getSize()); // 设定与frame相同的尺寸

        ImageIcon imageIcon = new ImageIcon("figs/level3.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层
    }
}
