package game.levels;

import game.utils.Player;

import javax.swing.*;

abstract public class Level extends JFrame {
    public JFrame frame; // 所有level共享一个显示frame
    public JLayeredPane layeredPane; // 保存所有当前level的内容
    public Player player;

    public Level(JFrame mainFrame, Player player) {
//        this.frame = mainFrame;
        this.frame = mainFrame;
        this.player = player;
        init();
    }

    abstract void init(); // 函数应该设置所有的ui图片和listener，放进layeredPane
    abstract void restartLevel(); // 每个关卡具体实现自己的重启逻辑

    @Override
    public JLayeredPane getLayeredPane() {
        return layeredPane;
    }

    // 按下ESC键显示的菜单
    protected void showMenu() {
        Object[] options = {"Restart", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                "What would you like to do?",
                "Game Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            // 重启游戏
            restartLevel();
        } else if (choice == 1) {
            // 退出游戏
            System.exit(0);
        }
    }

}
