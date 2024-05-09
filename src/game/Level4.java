package game;

import javax.swing.*;
import java.awt.*;
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
        chandelierButton.setBounds(380, 0, 100, 85); // 指定吊灯的位置和大小
        chandelierButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chandelierButton.setBorderPainted(false);
        chandelierButton.setContentAreaFilled(false);
        chandelierButton.setFocusPainted(false);
        chandelierButton.setOpaque(false);
        chandelierButton.addActionListener(e -> gemDialog.setVisible(true));

        gemDialog = new GemUnlockDialog(layeredPane, player);

        layeredPane.add(chandelierButton, Integer.valueOf(2));
    }


    private static class GemUnlockDialog extends JDialog {
        private JLabel[] slots = new JLabel[3];
        private Map<String, ImageIcon> gemIcons;
        private Map<String, Integer> gemIdMap;
        private String[] correctOrder = {"blue", "green", "red"}; // 正确的顺序

        public GemUnlockDialog(JLayeredPane parent, Player player) {
            super();
            setSize(400, 200);
            setLocationRelativeTo(parent);
            setLayout(new FlowLayout());

            gemIcons = new HashMap<>();
            loadAndResizeIcons();
            gemIdMap = new HashMap<>();
            setupGemIdMap();

            for (int i = 0; i < slots.length; i++) {
                slots[i] = new JLabel();
                slots[i].setPreferredSize(new Dimension(64, 64));
                slots[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                add(slots[i]);
            }

            initializeInventory(player);
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
            for (Map.Entry<String, Integer> entry : gemIdMap.entrySet()) {
                String gemColor = entry.getKey();
                int gemId = entry.getValue();
                if (player.hasItem(gemId)) {
                    JLabel gemLabel = new JLabel(gemIcons.get(gemColor));
                    gemLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            JLabel source = (JLabel) e.getSource();
                            boolean placed = false;
                            for (JLabel slot : slots) {
                                if (slot.getIcon() == null && source.getParent() != slots[0].getParent()) {
                                    slot.setIcon(source.getIcon());
                                    source.setIcon(null);
                                    placed = true;
                                    checkIfUnlocked();
                                    break;
                                }
                            }
                            if (!placed && source.getIcon() != null) {
                                // Move the gem back to inventory if it was clicked in a slot
                                for (Component comp : getContentPane().getComponents()) {
                                    if (comp instanceof JLabel && ((JLabel) comp).getIcon() == null) {
                                        ((JLabel) comp).setIcon(source.getIcon());
                                        source.setIcon(null);
                                        break;
                                    }
                                }
                            }
                        }
                    });
                    add(gemLabel);
                }
            }
        }


        private void checkIfUnlocked() {
            for (int i = 0; i < slots.length; i++) {
                ImageIcon icon = (ImageIcon) slots[i].getIcon();
                if (icon == null || !icon.getDescription().equals(correctOrder[i])) {
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Seems the chandelier is connected to somewhere.", "Unlocked", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
