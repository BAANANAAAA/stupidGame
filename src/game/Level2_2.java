package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level2_2 extends Level {

    public Level2_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level2_2", this);
        player.insertLevel("Level4_2", this);
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
                player.showTemporaryMessage("The door cannot block you. Why?");
            }
        });
        layeredPane.add(clickableArea, Integer.valueOf(2));

        getHolyWaterLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // 按左键跳转到Level4
                if (player.hasAccessTo("Level4_2")) {
                    player.GoTo("Level4_2");
                } else {
                    player.showTemporaryMessage("Not accessible yet.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                // 按右键返回Level1
                if (player.hasAccessTo("Level1_2")) {
                    player.GoTo("Level1_2");
                } else {
                    player.showTemporaryMessage("Access to Level1 is denied.");
                }
                break;
            case KeyEvent.VK_DOWN:
                // 按下键前往Level3
                if (player.hasAccessTo("Level3_2")) {
                    player.GoTo("Level3_2");
                } else {
                    player.showTemporaryMessage("Hmm? It waits something..");
                }
                break;
        }
    }

    private void getHolyWaterLabel() {
        ImageIcon holyWaterIcon = new ImageIcon("figs/holywater.PNG");
        Image holyWaterImage = holyWaterIcon.getImage().getScaledInstance(30, 50, Image.SCALE_SMOOTH);
        holyWaterIcon = new ImageIcon(holyWaterImage);
        JLabel holyWaterLabel = new JLabel(holyWaterIcon);
        holyWaterLabel.setBounds(515, 360,
                holyWaterIcon.getIconWidth(), holyWaterIcon.getIconHeight());

        holyWaterLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        holyWaterLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(2);
                player.showTemporaryMessage("It hurts! But...");
                layeredPane.remove(holyWaterLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(holyWaterLabel, Integer.valueOf(2));
    }

}
