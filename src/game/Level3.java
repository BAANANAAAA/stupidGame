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
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frame.getSize()); // 设定与frame相同的尺寸

        ImageIcon imageIcon = new ImageIcon("figs/level3.PNG"); // 加载图片
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // 覆盖整个窗口
        layeredPane.add(label, Integer.valueOf(1)); // 添加至低层

        createSofaHintArea();
    }

    private void createSofaHintArea() {
        int sofaWidth = 100; // Width of the clickable area
        int sofaHeight = 50; // Height of the clickable area
        int sofaX = frame.getWidth() - sofaWidth - 30; // Position X (from right minus width of the area and some margin)
        int sofaY = frame.getHeight() - sofaHeight - 30; // Position Y (from bottom minus height of the area and some margin)

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

    private void showHint() {
        JOptionPane.showMessageDialog(frame, "Look behind the mirror", "Hint", JOptionPane.INFORMATION_MESSAGE);
    }
}
