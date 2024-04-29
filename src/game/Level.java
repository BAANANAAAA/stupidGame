package game;

import javax.swing.*;

abstract class Level extends JFrame {
    public JFrame frame; // 所有level共享一个显示frame
    public JLayeredPane layeredPane; // 保存所有当前level的内容
    public ImageIcon imageIcon; // 背景图片
    public Player player;

    public Level(JFrame mainFrame, Player player) {
//        this.frame = mainFrame;
        this.frame = mainFrame;
        this.player = player;
        init();
    }

    abstract void init(); // 函数应该设置所有的ui图片和listener，放进layeredPane

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }
}
