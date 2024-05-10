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
                player.showTemporaryMessage("Something behind the door blocks you.");
            }
        });
        layeredPane.add(clickableArea, Integer.valueOf(2));

        getHolyWaterLabel();
        getSwordHolderLabel();
        getRedGemLabel();
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                // 按左键跳转到Level4
                if (player.hasAccessTo("Level4_2")) {
                    player.GoTo("Level4_2");
                } else {
                    player.showTemporaryMessage("Just...no...");
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
                    player.showTemporaryMessage("Ouch! That burns...");
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
                player.showTemporaryMessage("Holy! It hurts! But...");
                layeredPane.remove(holyWaterLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(holyWaterLabel, Integer.valueOf(2));
    }

    private void getSwordHolderLabel() {
        ImageIcon swordHolderIcon = new ImageIcon("figs/swordholder.PNG");
        Image swordHolderImage = swordHolderIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        swordHolderIcon = new ImageIcon(swordHolderImage);
        JLabel swordHolderLabel = new JLabel(swordHolderIcon);
        swordHolderLabel.setBounds(350, 510,
                swordHolderIcon.getIconWidth(), swordHolderIcon.getIconHeight());

        swordHolderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        swordHolderLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(player.hasItem(1) && !player.hasAccessTo("Level4_2")){
                    player.showTemporaryMessage("Oh! Now you can traverse the door...");
                    player.addAccessTo("Level4_2");
                }
                else {
                    player.showTemporaryMessage("Strange thing...");
                }
            }
        });

        layeredPane.add(swordHolderLabel, Integer.valueOf(2));
    }

    private void getRedGemLabel() {
        ImageIcon RedGemIcon = new ImageIcon("figs/wallhint2.PNG");
        Image redGemImage = RedGemIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        RedGemIcon = new ImageIcon(redGemImage);
        JLabel RedGemLabel = new JLabel(RedGemIcon);
        RedGemLabel.setBounds(190, 360,
                RedGemIcon.getIconWidth(), RedGemIcon.getIconHeight());

        RedGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RedGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.showParchmentHint("Third...\nFlaming RED of candle.");
            }
        });

        layeredPane.add(RedGemLabel, Integer.valueOf(2));
    }

}
