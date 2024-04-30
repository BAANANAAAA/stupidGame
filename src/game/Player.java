package game;

import javax.swing.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {

    private JFrame gameFrame;
    private Level curLevel;

    private StartPage myStartPage = null;
    private level1 myLevel1 = null;
    private level2 myLevel2 = null;

    public Player(JFrame _frame) {
        gameFrame = _frame;
        System.out.println("player constructed");
    }

    public void setMyStartPage(StartPage myStartPage) {
        this.myStartPage = myStartPage;
    }

    public void setMyLevel1(level1 myLevel1) {
        this.myLevel1 = myLevel1;
    }

    public void setMyLevel2(level2 myLevel2) {
        this.myLevel2 = myLevel2;
    }

    public void GoTo(String dest) {
        switch (dest) {
            case "StartPage" -> {
                System.out.println("start");
                if (myStartPage == null) {
                    myStartPage = new StartPage(gameFrame, this);
                }
                curLevel = myStartPage;
            }
            case "Level1" -> {
                if (myLevel1 == null) {
                    myLevel1 = new level1(gameFrame, this);
                }
                curLevel = myLevel1;
            }
            case "Level2" -> {
                if (myLevel2 == null) {
                    myLevel2 = new level2(gameFrame, this);
                }
                curLevel = myLevel2;
            }
        }
        gameFrame.setContentPane(Objects.requireNonNull(curLevel).getLayeredPane()); // 我超 这么智能
        gameFrame.pack();
        gameFrame.revalidate();
        gameFrame.repaint();
        System.out.println("goto" + dest);
    }
}
