package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

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
        ImageIcon imageIcon = new ImageIcon("figs/start.PNG");
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, contentWidth, contentHeight);

        // button for player 1
        JButton startButton = new JButton("Start As Player 1");
        startButton.setBounds(330, 500, 130, 30);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.GoTo("Level1_1");
            }
        });

        // button for player 2
        JButton startButtonPlayer2 = new JButton("Start As Player 2");
        startButtonPlayer2.setBounds(330, 550, 130, 30); // 指定按钮位置和大小
        startButtonPlayer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.GoTo("Level1_2");
            }
        });

        layeredPane.add(startButtonPlayer2, Integer.valueOf(2)); // 将按钮加入更高层


        // exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(350, 600, 100, 30); // 指定按钮位置和大小
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        layeredPane.add(background, Integer.valueOf(1));
        layeredPane.add(startButton, Integer.valueOf(2)); // 将按钮加入更高层
        layeredPane.add(exitButton, Integer.valueOf(2)); // 将按钮加入更高层
    }

    @Override
    void handleKeyInput(KeyEvent e) {

    }
}
