package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level3_2 extends Level {

    public Level3_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level3_2", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level3.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        createMirrorArea();
        getGreenGemHintLabel();
    }

    @Override
    void goUp() {
        if (player.hasAccessTo("Level2_2")) {
            player.GoTo("Level2_2");
        } else {
            player.showTemporaryMessage("Access to Level2 is denied.");
        }
    }

    @Override
    void goDown() {
        if (player.hasAccessTo("Level6_2")) {
            player.GoTo("Level6_2");
        } else {
            player.showTemporaryMessage("Maybe there is even more room...but not now..?");
        }
    }

    @Override
    void goRight() {

    }

    @Override
    void goLeft() {

    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (player.hasAccessTo("Level2_2")) {
                    player.GoTo("Level2_2");
                } else {
                    player.showTemporaryMessage("Access to Level2 is denied.");
                }
                break;
            case KeyEvent.VK_DOWN:
                if (player.hasAccessTo("Level6_2")) {
                    player.GoTo("Level6_2");
                } else {
                    player.showTemporaryMessage("Maybe there is even more room...but not now..?");
                }
                break;
        }
    }

    private void createMirrorArea() {
        JLabel mirrorArea = new JLabel();
        mirrorArea.setBounds(400, 150, 100, 300);
        mirrorArea.setCursor(new Cursor(Cursor.HAND_CURSOR));

        mirrorArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyHammer();
            }
        });

        layeredPane.add(mirrorArea, Integer.valueOf(2));
    }

    private void getGreenGemHintLabel() {
        ImageIcon RedGemIcon = new ImageIcon("figs/wallhint4.PNG");
        Image redGemImage = RedGemIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        RedGemIcon = new ImageIcon(redGemImage);
        JLabel RedGemLabel = new JLabel(RedGemIcon);
        RedGemLabel.setBounds(190, 230,
                RedGemIcon.getIconWidth(), RedGemIcon.getIconHeight());

        RedGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RedGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.showParchmentHint("Second....\nWeeping green of mother");
            }
        });

        layeredPane.add(RedGemLabel, Integer.valueOf(2));
    }

    private void verifyHammer() {
        if (player.hasItem(3)) {
            JOptionPane.showMessageDialog(frame, "Oh! You broke something...!");
            player.addAccessTo("Level4_2");
        } else {
            player.showTemporaryMessage("Nothing happened..."); // Show message if the key doesn't exist
        }
    }

    private void showHint() {
        JOptionPane.showMessageDialog(frame, "Look in the shadow of mirror", "Hint", JOptionPane.INFORMATION_MESSAGE);
    }
}
