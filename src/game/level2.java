package game;

import javax.swing.*;
import java.awt.*;

class level2 extends Level {

    public level2(JFrame mainFrame, Player player) {
//        this.frame = mainFrame;
        super(mainFrame, player);
        init();
    }

    public void init() {
        // 设置LayeredPane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frame.getSize()); // 设定与frame相同的尺寸

        // 加载并设置图片标签
        ImageIcon imageIcon = new ImageIcon("figs/level2.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        // 创建并设置返回按钮
        JButton backButton = new JButton("Back to Level 1");
        backButton.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() - 100, 150, 30); // 按钮位置
        layeredPane.add(backButton, Integer.valueOf(2)); // 添加至高层

        // 添加监听器
        backButton.addActionListener(e -> backToLevel1());
    }

    private void backToLevel1() {
        player.GoTo("level1");
    }
}
