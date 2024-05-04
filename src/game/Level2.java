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
        // 设置LayeredPane
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frame.getSize()); // 设定与frame相同的尺寸

        // 加载并设置图片标签
        ImageIcon imageIcon = new ImageIcon("figs/level2.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        JLabel keyLabel = getjLabel();
        layeredPane.add(keyLabel, Integer.valueOf(2)); // 添加至中层

        // 创建并设置返回按钮
        JButton backButton = new JButton("Back to Level 1");
        backButton.setBounds(frame.getWidth() / 2 - 50, frame.getHeight() - 100, 150, 30); // 按钮位置
        layeredPane.add(backButton, Integer.valueOf(2)); // 添加至高层

        // 添加监听器
        backButton.addActionListener(e -> backToLevel1());

        // 创建并设置一个透明的点击区域
        JLabel clickableArea = new JLabel();
        clickableArea.setBounds(frame.getWidth() / 2 - 100, frame.getHeight() / 2 - 100, 200, 200); // 设置点击区域的大小和位置
        clickableArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verifyKey2();
            }
        });
        layeredPane.add(clickableArea, Integer.valueOf(2)); // 添加至中层，确保不阻挡其他组件
    }

    private JLabel getjLabel() {
        ImageIcon keyIcon = new ImageIcon("figs/key2.PNG");
        Image keyImage = keyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // 调整大小为50x50
        keyIcon = new ImageIcon(keyImage);
        JLabel keyLabel = new JLabel(keyIcon);
        keyLabel.setBounds(frame.getWidth() / 2 - keyIcon.getIconWidth() / 2,
                frame.getHeight() / 2 - keyIcon.getIconHeight() / 2,
                keyIcon.getIconWidth(), keyIcon.getIconHeight()); // 设置在窗口正中
        keyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(2); // 添加钥匙到玩家物品列表
                player.showTemporaryMessage("Wow, you acquired a magical key!");
                layeredPane.remove(keyLabel); // 移除钥匙标签
                layeredPane.repaint(); // 重绘界面
            }
        });
        return keyLabel;
    }

    private void verifyKey2() {
        if (player.hasItem(2)) { // Assuming '2' is the ID for key2
            JOptionPane.showMessageDialog(frame, "The door opens..");
            player.GoTo("Level3"); // Go to Level 3 if the key exists
        } else {
            player.showTemporaryMessage("Nothing happened..."); // Show message if the key doesn't exist
        }
    }

    private void backToLevel1() {
        player.GoTo("Level1");
    }
}
