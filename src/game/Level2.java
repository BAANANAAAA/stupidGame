package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level2 extends Level {

    public Level2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level2", this);
    }

    public void init() {
        // 加载并设置图片标签
        ImageIcon imageIcon = new ImageIcon("figs/level2.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        JLabel clickableArea = new JLabel();
        clickableArea.setBounds(contentWidth / 2 - 100, contentHeight / 2 - 100, 200, 200);
        clickableArea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickableArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyKey2();
            }
        });
        layeredPane.add(clickableArea, Integer.valueOf(2));

        getBlueGemLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // 按左键跳转到Level4
                if (player.hasAccessTo("Level4")) {
                    player.GoTo("Level4");
                } else {
                    player.showTemporaryMessage("Not accessible yet.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                // 按右键返回Level1
                if (player.hasAccessTo("Level1")) {
                    player.GoTo("Level1");
                } else {
                    player.showTemporaryMessage("Access to Level1 is denied.");
                }
                break;
            case KeyEvent.VK_DOWN:
                // 按下键前往Level3
                if (player.hasAccessTo("Level3")) {
                    player.GoTo("Level3");
                } else {
                    player.showTemporaryMessage("Yes, a key is needed.");
                }
                break;
        }
    }

    private void getBlueGemLabel() {
        ImageIcon BlueGemIcon = new ImageIcon("figs/blue_gem.PNG");
        Image BlueGemImage = BlueGemIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        BlueGemIcon = new ImageIcon(BlueGemImage);
        JLabel BlueGemLabel = new JLabel(BlueGemIcon);
        BlueGemLabel.setBounds(290, 180,
                BlueGemIcon.getIconWidth(), BlueGemIcon.getIconHeight());

        BlueGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BlueGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(11);
                player.showTemporaryMessage("So beautiful...");
                layeredPane.remove(BlueGemLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(BlueGemLabel, Integer.valueOf(2));
    }


    private void verifyKey2() {
        if (player.hasItem(2)) {
            JOptionPane.showMessageDialog(frame, "The door opens..");
            player.addAccessTo("Level3");
        } else {
            player.showTemporaryMessage("Nothing happened...");
        }
    }

}
