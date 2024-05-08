import game.Player;
import game.StartPage;

import javax.swing.*;
import java.awt.*;

class Main {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1200, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        JPanel chatPanel = new JPanel();
        chatPanel.setPreferredSize(new Dimension(200, 600));
        chatPanel.setBackground(Color.BLUE);
        mainFrame.add(chatPanel, BorderLayout.WEST);

        Player player = new Player(mainFrame);
        new StartPage(mainFrame, player);
    }
}