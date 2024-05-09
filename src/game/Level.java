package game;

import javax.swing.*;
import java.awt.*;

abstract public class Level extends JPanel {
    public JFrame frame; // 所有level共享一个显示frame
    public JLayeredPane layeredPane; // 保存所有当前level的内容
    public Player player;

    public static final int contentWidth = 800;
    public static final int contentHeight = 750;

    public Level(JFrame mainFrame, Player player) {
        this.frame = mainFrame;
        this.player = player;
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(contentWidth, contentHeight));
        frame.add(layeredPane, BorderLayout.EAST);
    }

    abstract void init(); // 函数应该设置所有的ui图片和listener，放进layeredPane

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}
