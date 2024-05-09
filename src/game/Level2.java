package game;

import javax.swing.*;
import java.awt.*;
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

        JLabel keyLabel = getjLabel();
        layeredPane.add(keyLabel, Integer.valueOf(2));

        // 创建并设置返回按钮
        JButton backButton = new JButton("Back to Level 1");
        backButton.setBounds(contentWidth / 2 - 50, contentHeight - 100, 150, 30); // 按钮位置
        layeredPane.add(backButton, Integer.valueOf(2));

        // 添加监听器
        backButton.addActionListener(e -> backToLevel1());

        // 创建并设置一个透明的点击区域
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

        getGreenGemLabel();
        getBlueGemLabel();
    }

    private JLabel getjLabel() {
        ImageIcon keyIcon = new ImageIcon("figs/key2.PNG");
        Image keyImage = keyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        keyIcon = new ImageIcon(keyImage);
        JLabel keyLabel = new JLabel(keyIcon);
        keyLabel.setBounds(contentWidth / 2 - keyIcon.getIconWidth() / 2,
                contentHeight / 2 - keyIcon.getIconHeight() / 2,
                keyIcon.getIconWidth(), keyIcon.getIconHeight());

        // Set the cursor to hand cursor when mouse hovers over the JLabel
        keyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        keyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(2);
                player.showTemporaryMessage("Wow, you acquired a magical key!");
                layeredPane.remove(keyLabel);
                layeredPane.repaint();
            }
        });
        return keyLabel;
    }

    private void getGreenGemLabel() {
        ImageIcon GreenGemIcon = new ImageIcon("figs/green_gem.PNG");
        Image BlueGemImage = GreenGemIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        GreenGemIcon = new ImageIcon(BlueGemImage);
        JLabel GreenGemLabel = new JLabel(GreenGemIcon);
        GreenGemLabel.setBounds(530,
                295,
                GreenGemIcon.getIconWidth(), GreenGemIcon.getIconHeight());

        GreenGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GreenGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(12);
                player.showTemporaryMessage("Wait..You picked a green gem!");
                layeredPane.remove(GreenGemLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(GreenGemLabel, Integer.valueOf(2));
    }

    private void getBlueGemLabel() {
        ImageIcon BlueGemIcon = new ImageIcon("figs/blue_gem.PNG");
        Image BlueGemImage = BlueGemIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        BlueGemIcon = new ImageIcon(BlueGemImage);
        JLabel BlueGemLabel = new JLabel(BlueGemIcon);
        BlueGemLabel.setBounds(140, 500,
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
            player.GoTo("Level3");
        } else {
            player.showTemporaryMessage("Nothing happened...");
        }
    }

    private void backToLevel1() {
        player.GoTo("Level1");
    }
}
