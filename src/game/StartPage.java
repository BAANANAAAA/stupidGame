package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends Level {

    public StartPage(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        System.out.println("StartPage Constructed");
        player.insertLevel("StartPage", this);
        player.GoTo("StartPage");
    }

    @Override
    void init() {
        // 创建 JLayeredPane 用于层次化布局
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));
        // 创建一个自定义 JPanel 来绘制背景图片
        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, 800, 600); // 设置和窗口一样的大小

        // start button
        JButton startButton = new JButton("Start");
        startButton.setBounds(380, 450, 100, 30); // 指定按钮位置和大小

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new level1(mainFrame, player);
                layeredPane.setVisible(false);
                player.GoTo("Level1");
            }
        });

        // exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(380, 520, 100, 30); // 指定按钮位置和大小
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 将背景面板和按钮添加到 JLayeredPane
        layeredPane.add(backgroundPanel, Integer.valueOf(1)); // 将背景加入更低层
        layeredPane.add(startButton, Integer.valueOf(2)); // 将按钮加入更高层
        layeredPane.add(exitButton, Integer.valueOf(2)); // 将按钮加入更高层
    }
}

class BackgroundPanel extends JPanel {
    private final ImageIcon imageIcon;

    public BackgroundPanel() {
        imageIcon = new ImageIcon("figs/start.PNG"); // 加载图片
        this.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 绘制图片以覆盖整个面板
        g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
