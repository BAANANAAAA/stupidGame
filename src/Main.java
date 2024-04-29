import game.Player;
import game.StartPage;

import javax.swing.*;

class Main {

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        new StartPage(mainFrame, new Player(mainFrame));
    }
}