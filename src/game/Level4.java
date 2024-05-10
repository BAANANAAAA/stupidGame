package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Level4 extends Level {
    private GemUnlockDialog gemDialog;

    public Level4(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        init();
        player.insertLevel("Level4", this);
    }

    public void init() {
        ImageIcon imageIcon = new ImageIcon("figs/level4.PNG");
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        JButton chandelierButton = new JButton();
        chandelierButton.setBounds(380, 200, 100, 85);
        chandelierButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chandelierButton.setBorderPainted(false);
        chandelierButton.setContentAreaFilled(false);
        chandelierButton.setFocusPainted(false);
        chandelierButton.setOpaque(false);
        chandelierButton.addActionListener(e -> gemDialog.setVisible(true));

        gemDialog = new GemUnlockDialog(layeredPane, player);

        layeredPane.add(chandelierButton, Integer.valueOf(2));
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (player.hasAccessTo("Level2")) {
                player.GoTo("Level2");
            } else {
                player.showTemporaryMessage("Access to Level2 is denied.");
            }
        }
    }

    private class GemUnlockDialog extends JDialog {
        private JLabel[] slots = new JLabel[3];
        private Map<String, ImageIcon> gemIcons;
        private Map<String, Integer> gemIdMap;
        private String[] correctOrder = {"blue", "green", "red"}; // 正确的顺序

        public GemUnlockDialog(JLayeredPane parent, Player player) {
            super();
            setSize(400, 200);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout()); // 更改为 BorderLayout

            gemIcons = new HashMap<>();
            loadAndResizeIcons();
            gemIdMap = new HashMap<>();
            setupGemIdMap();

            JPanel slotPanel = new JPanel(new FlowLayout()); // 创建一个面板放置插槽
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new JLabel();
                slots[i].setPreferredSize(new Dimension(64, 64));
                slots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                slotPanel.add(slots[i]); // 添加插槽到面板
            }
            add(slotPanel, BorderLayout.CENTER); // 将插槽面板添加到中央

            initializeInventory(player);

            JButton resetButton = new JButton("Reset Gems");
            resetButton.addActionListener(e -> resetGems());
            add(resetButton, BorderLayout.SOUTH); // 将按钮放置在窗体的南部
        }

        private void setupGemIdMap() {
            gemIdMap.put("red", 10);
            gemIdMap.put("blue", 11);
            gemIdMap.put("green", 12);
        }

        private void loadAndResizeIcons() {
            String[] gemNames = {"red", "blue", "green"};
            String pathPrefix = "figs/";
            for (String gem : gemNames) {
                ImageIcon originalIcon = new ImageIcon(pathPrefix + gem + "_gem.png");
                Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                gemIcons.put(gem, new ImageIcon(scaledImage, gem));
            }
        }

        private void initializeInventory(Player player) {
            JPanel gemPanel = new JPanel(new FlowLayout()); // 创建一个面板放置宝石
            for (Map.Entry<String, Integer> entry : gemIdMap.entrySet()) {
                String gemColor = entry.getKey();
                int gemId = entry.getValue();
                if (player.hasItem(gemId)) {
                    JLabel gemLabel = new JLabel(gemIcons.get(gemColor));
                    gemLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            JLabel source = (JLabel) e.getSource();
                            for (JLabel slot : slots) {
                                if (slot.getIcon() == null) {
                                    slot.setIcon(source.getIcon());
                                    source.setIcon(null);
                                    checkIfUnlocked();
                                    break;
                                }
                            }
                        }
                    });
                    gemPanel.add(gemLabel); // 将宝石添加到面板
                }
            }
            add(gemPanel, BorderLayout.NORTH); // 将宝石面板添加到窗体的北部
        }

        private void checkIfUnlocked() {
            for (int i = 0; i < slots.length; i++) {
                ImageIcon icon = (ImageIcon) slots[i].getIcon();
                if (icon == null || !icon.getDescription().equals(correctOrder[i])) {
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Seems the chandelier is connected to somewhere.", "Unlocked", JOptionPane.INFORMATION_MESSAGE);
            player.addAccessTo("Level6");
            dispose();
        }

        private void resetGems() {
            // 清空所有插槽的图标
            for (JLabel slot : slots) {
                slot.setIcon(null);
            }
            initializeInventory(player); // 重新初始化库存，将宝石放回固定位置
        }

    }
}
