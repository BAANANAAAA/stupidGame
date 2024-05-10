package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Level7 extends Level {
    public Level7(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        this.player = player;
        init();
        player.insertLevel("Level7", this);
    }

    public void init() {
        ImageIcon backgroundImage = new ImageIcon("figs/level7.PNG");
        JLabel label = new JLabel(backgroundImage);
        label.setBounds(0, 0, contentWidth, contentHeight);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isInMirror(e.getX(), e.getY())) {
                    showCustomDialog();
                }
            }
        });

        label.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (isInMirror(e.getX(), e.getY())) {
                    label.setCursor(new Cursor(Cursor.HAND_CURSOR));  // 设置光标为手形
                } else {
                    label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  // 其他区域使用默认光标
                }
            }
        });

        layeredPane.add(label, Integer.valueOf(1));
    }

    private boolean isInMirror(int x, int y) {
        int mirrorCenterX = contentWidth / 2;
        int mirrorCenterY = contentHeight / 2;
        int mirrorWidth = 100;
        int mirrorHeight = 200;
        return x >= mirrorCenterX - mirrorWidth / 2 && x <= mirrorCenterX + mirrorWidth / 2 &&
                y >= mirrorCenterY - mirrorHeight / 2 && y <= mirrorCenterY + mirrorHeight / 2;
    }

    private void showCustomDialog() {
        Object[] options = {"Yes", "Yes"};
        JOptionPane.showOptionDialog(layeredPane, "Do you want to break the world?", "MirrorMirrorMirror",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        showCompletionDialog();
    }

    private void showCompletionDialog() {
        JOptionPane.showMessageDialog(layeredPane, "Yes, you are they, they are you.", "TRUE END!", JOptionPane.INFORMATION_MESSAGE);
        int action = JOptionPane.showOptionDialog(layeredPane, "Do you want to exit?", "Exit",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (action == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    void handleKeyInput(KeyEvent e) {

    }
}
