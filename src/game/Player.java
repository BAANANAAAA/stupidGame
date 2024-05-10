package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {

    private final JFrame gameFrame;
    private Level curLevel;

    private static final HashSet<String> levelNames = new HashSet<>(Set.of("StartPage", "Level1", "Level2", "Level3", "Level4", "Level5", "Level6")); // 所有合法的level名，用于player.goto
    private final HashMap<String, Level> accessibleLevels = new HashMap<>();
    private final HashSet<String> unLockedLevels = new HashSet<>();
    private final HashSet<Integer> items = new HashSet<>();

    public Player(JFrame _frame) {
        gameFrame = _frame;
        initKeyboardListener();
        System.out.println("player constructed");
    }

    public void insertLevel(String dest, Level _level) {
        // 总之也要先检查是否合法
        if (!levelNames.contains(dest)) {
            System.err.println("GoTo destination does not exist");
            System.exit(1);
        }
        // 这个函数只会在首次进入level时被构造函数调用
        accessibleLevels.put(dest, _level);
    }

    public void GoTo(String dest) {
        // 检查level名是否合法
        if (!levelNames.contains(dest)) {
            System.err.println("GoTo destination does not exist");
            System.exit(1);
        }
        // 不显示原来的内容
        if (curLevel != null) {
            Objects.requireNonNull(curLevel).getLayeredPane().setVisible(false);
        }
        // 更换curLevel
        if (!accessibleLevels.containsKey(dest)) {
            // 如果不存在，新建一个实例
            try {
                Class<?> clazz = Class.forName("game." + dest);
                Constructor<?> constructor = clazz.getConstructor(JFrame.class, Player.class);
                curLevel = (Level) constructor.newInstance(gameFrame, this);
            } catch (ClassNotFoundException | NoSuchMethodException e) {
                System.err.println("??" + e.getMessage());
                System.exit(1);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
                System.err.println("newInstance " + e.getMessage());
                System.exit(1);
            }
        } else {
            curLevel = accessibleLevels.get(dest);
        }
        Objects.requireNonNull(curLevel).getLayeredPane().setVisible(true);

        gameFrame.revalidate();
        gameFrame.repaint();
        System.out.println("goto" + dest);
    }

    public void addItemToPackage(int id) {
        items.add(id);
    }

    public boolean hasItem(int id){
        return items.contains(id);
    }

    public void addAccessTo(String levelName) {
        unLockedLevels.add(levelName);
    }

    public boolean hasAccessTo(String levelName) {
        return unLockedLevels.contains(levelName);
    }

    private void initKeyboardListener() {
        gameFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (curLevel != null) {
                    curLevel.handleKeyInput(e);
                }
            }
        });
        gameFrame.setFocusable(true);
        gameFrame.requestFocusInWindow();
    }

    public void showTemporaryMessage(String message) {
        JLayeredPane layeredPane = curLevel.getLayeredPane();

        ImageIcon dialogIcon = new ImageIcon("figs/dialog.PNG");
        Image dialogImage = dialogIcon.getImage().getScaledInstance(500, 250, Image.SCALE_SMOOTH); // 调整大小为50x50
        dialogIcon = new ImageIcon(dialogImage);
        JLabel dialogLabel = new JLabel(dialogIcon);
        dialogLabel.setBounds(160,460, dialogIcon.getIconWidth(), dialogIcon.getIconHeight());

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setBounds(0, 110, dialogIcon.getIconWidth(), 30);

        dialogLabel.setLayout(null);
        dialogLabel.add(messageLabel);

        layeredPane.add(dialogLabel, JLayeredPane.POPUP_LAYER);

        dialogLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layeredPane.remove(dialogLabel);
                layeredPane.repaint();
            }
        });

        Timer timer = new Timer(4000, e -> {
            layeredPane.remove(dialogLabel);
            layeredPane.repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

}
