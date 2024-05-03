import game.utils.Player;
import game.levels.StartPage;

import javax.swing.*;

class Main {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        Player player = new Player(mainFrame);
        new StartPage(mainFrame, player);
    }
}