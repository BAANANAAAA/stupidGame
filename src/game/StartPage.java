package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        layeredPane.add(background, Integer.valueOf(1));


        JLabel startButton1 = getButton(160, 370, "Start As Player 1");
        layeredPane.add(startButton1, Integer.valueOf(2));
        startButton1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.GoTo("Level1_1");
            }
        });

        JLabel startButton2 = getButton(460, 370, "Start As Player 2");
        layeredPane.add(startButton2, Integer.valueOf(2));
        startButton2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.GoTo("Level1_2");
            }
        });

        JLabel exitButton = getButton(310, 550, "Exit");
        layeredPane.add(exitButton, Integer.valueOf(2));
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    void handleKeyInput(KeyEvent e) {

    }

    @Override
    void goUp() {

    }

    @Override
    void goDown() {

    }

    @Override
    void goRight() {

    }

    @Override
    void goLeft() {

    }

    private JLabel getButton(int x, int y, String msg){
        ImageIcon GlassButtonIcon = new ImageIcon("figs/button.PNG");
        Image GlassButtonImage = GlassButtonIcon.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
        GlassButtonIcon = new ImageIcon(GlassButtonImage);
        JLabel GlassButtonLabel = new JLabel(GlassButtonIcon);
        GlassButtonLabel.setBounds(x, y, GlassButtonIcon.getIconWidth(), GlassButtonIcon.getIconHeight());
        GlassButtonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel messageLabel = new JLabel(msg, SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setBounds(x, y-5, GlassButtonIcon.getIconWidth(), 30);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GlassButtonLabel.setLayout(new BorderLayout());
        GlassButtonLabel.add(messageLabel);

        return GlassButtonLabel;
    }
}
