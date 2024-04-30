package game;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player {

    private final JFrame gameFrame;
    private Level curLevel;

    private static final HashSet<String> levelNames = new HashSet<>(Set.of("StartPage", "Level1", "Level2")); // 所有合法的level名，用于player.goto
    private final HashMap<String, Level> accessibleLevels = new HashMap<>();

    public Player(JFrame _frame) {
        gameFrame = _frame;
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
                System.err.println("newInstance" + e.getMessage());
                System.exit(1);
            }
        } else {
            curLevel = accessibleLevels.get(dest);
        }

        gameFrame.setContentPane(Objects.requireNonNull(curLevel).getLayeredPane()); // 我超 这么智能
        gameFrame.pack();
        gameFrame.revalidate();
        gameFrame.repaint();
        System.out.println("goto" + dest);
    }
}
