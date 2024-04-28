package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscapeGame {
    private JFrame mainFrame;
    private Player player;

    public EscapeGame() {
        mainFrame = new JFrame("Escape!");
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // initialize player
        player = new Player();
        showStartScreen();
    }

    private void showStartScreen() {
        // 创建 JLayeredPane 用于层次化布局
        JLayeredPane layeredPane = new JLayeredPane();
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
                new level1(mainFrame, player);
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

        // 将 layeredPane 设置为窗口的内容面板
        mainFrame.setContentPane(layeredPane);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    // 自定义 JPanel 用于绘制背景图像
    class BackgroundPanel extends JPanel {
        private ImageIcon imageIcon;

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

    public static void main(String[] args) {
        EscapeGame game = new EscapeGame();
    }
}

