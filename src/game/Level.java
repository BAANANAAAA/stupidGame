package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract public class Level extends JPanel {
    public JFrame frame; // 所有level共享一个显示frame
    public JLayeredPane layeredPane; // 保存所有当前level的内容
    public Player player;

    public static final int contentWidth = 800;
    public static final int contentHeight = 750;
    private static final int BORDER_WIDTH = 60;


    public Level(JFrame mainFrame, Player player) {
        this.frame = mainFrame;
        this.player = player;
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(contentWidth, contentHeight));
        frame.add(layeredPane, BorderLayout.EAST);

        layeredPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + " " + y);
                if (y <= BORDER_WIDTH) {
                    System.out.println("up");
                    goUp();
                } else if (y >= contentHeight - BORDER_WIDTH) {
                    System.out.println("down");
                    goDown();
                } else if (x >= contentWidth - BORDER_WIDTH) {
                    System.out.println("right");
                    goRight();
                } else if (x <= BORDER_WIDTH) {
                    System.out.println("left");
                    goLeft();
                }
            }
        });
    }

    abstract void init(); // 函数应该设置所有的ui图片和listener，放进layeredPane

    abstract void goUp();
    abstract void goDown();
    abstract void goRight();
    abstract void goLeft();

    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    abstract void handleKeyInput(KeyEvent e);
}
