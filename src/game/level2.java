package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class level2 extends JFrame {
    private JFrame frame;
    private ImageIcon imageIcon;

    public level2(JFrame mainFrame) {
        this.frame = mainFrame;
        initUI();
    }

    private void initUI() {
        this.frame.setTitle("Level 2");
        this.frame.getContentPane().removeAll();  // 清除之前的内容

        imageIcon = new ImageIcon("figs/level2.PNG"); // 假设图片存放于figs文件夹
        JLabel label = new JLabel(imageIcon);

        // 假设此处有第二关的其他初始化代码
        JButton backButton = new JButton("Back to Level 1");
        backButton.addActionListener(e -> backToLevel1());

        JPanel panel = new JPanel();
        panel.add(backButton);
        add(panel);
    }

    private void backToLevel1() {
        level1 level1 = new level1(frame);
        level1.setVisible(true);
        this.dispose();
    }
}