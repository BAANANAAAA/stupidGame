package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level3 extends Level {

    public Level3(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level3", this);
    }

    public void init() {
//        layeredPane = new JLayeredPane();
//        layeredPane.setPreferredSize(frame.getSize()); // 设定与frame相同的尺寸

        ImageIcon imageIcon = new ImageIcon("figs/level3.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        createSofaHintArea();
        createMirrorArea();
        JLabel hammerLabel = getHammerLabel();
        layeredPane.add(hammerLabel, Integer.valueOf(2)); // 添加至中层
    }

    private void createSofaHintArea() {
        int sofaWidth = 100; // Width of the clickable area
        int sofaHeight = 50; // Height of the clickable area
        int sofaX = contentWidth - sofaWidth - 30; // Position X (from right minus width of the area and some margin)
        int sofaY = contentHeight - sofaHeight - 30; // Position Y (from bottom minus height of the area and some margin)

        JLabel sofaArea = new JLabel();
        sofaArea.setBounds(sofaX, sofaY, sofaWidth, sofaHeight);
        sofaArea.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change the cursor to indicate clickable area

        // Add mouse listener to show hint when clicked
        sofaArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showHint();
            }
        });

        layeredPane.add(sofaArea, Integer.valueOf(2)); // Add the clickable area above the background
    }

    private void createMirrorArea() {
        int mirrorWidth = 200; // Width of the clickable area
        int mirrorHeight = 200; // Height of the clickable area
        int sofaX = contentWidth - mirrorWidth - 550; // Position X (from right minus width of the area and some margin)
        int sofaY = contentHeight - mirrorHeight - 200; // Position Y (from bottom minus height of the area and some margin)

        JLabel mirrorArea = new JLabel();
        mirrorArea.setBounds(sofaX, sofaY, mirrorWidth, mirrorHeight);
        mirrorArea.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change the cursor to indicate clickable area

        // Add mouse listener to show hint when clicked
        mirrorArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyHammer();
            }
        });

        layeredPane.add(mirrorArea, Integer.valueOf(2)); // Add the clickable area above the background
    }

    private JLabel getHammerLabel() {
        ImageIcon keyIcon = new ImageIcon("figs/hammer0.PNG");
        Image keyImage = keyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 调整大小为50x50
        keyIcon = new ImageIcon(keyImage);
        JLabel keyLabel = new JLabel(keyIcon);
        keyLabel.setBounds(keyIcon.getIconWidth() / 2,
                contentHeight / 2 - keyIcon.getIconHeight() / 2,
                keyIcon.getIconWidth(), keyIcon.getIconHeight());
        keyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(3); // 添加钥匙到玩家物品列表
                player.showTemporaryMessage("An exquisite hammer..");
                layeredPane.remove(keyLabel); // 移除钥匙标签
                layeredPane.repaint(); // 重绘界面
            }
        });
        return keyLabel;
    }

    private void verifyHammer() {
        if (player.hasItem(3)) {
            JOptionPane.showMessageDialog(frame, "Oh! You broke something...!");
            player.GoTo("Level4");
        } else {
            player.showTemporaryMessage("Nothing happened..."); // Show message if the key doesn't exist
        }
    }

    private void showHint() {
        JOptionPane.showMessageDialog(frame, "Look in the shadow of mirror", "Hint", JOptionPane.INFORMATION_MESSAGE);
    }
}
