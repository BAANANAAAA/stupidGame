import game.*;
import chat.*;

import javax.swing.*;
import java.awt.*;

class Main {

    public static void main(String[] args) {
        // 初始化各种frame和组件
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1000, 750);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // 初始化 chatPanel
        JPanel chatPanel = new JPanel();
        chatPanel.setPreferredSize(new Dimension(200, 600));
        mainFrame.add(chatPanel, BorderLayout.WEST);
        new Chat(chatPanel);

        // start game
        Player player = new Player(mainFrame); // 正常游戏可以直接开始，和聊天室登录无关，但会缺少线索
        new StartPage(mainFrame, player);
    }

}