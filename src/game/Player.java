package game;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private Set<Integer> exploredLevels; // 存储已探索的关卡编号
    private Set<Integer> Item;
    private Set<Integer> UsedItem;


    public Player() {
        this.exploredLevels = new HashSet<>(); // 初始化时没有关卡被探索
    }

    // 标记关卡为已探索
    public void markLevelExplored(int level) {
        exploredLevels.add(level);
    }

    // 检查某个关卡是否已被探索
    public boolean isLevelExplored(int level) {
        return exploredLevels.contains(level);
    }
}
