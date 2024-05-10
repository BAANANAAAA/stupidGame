package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level1_2 extends Level {
    private JPanel vasePanel;
    private final String correctPassword = "secret"; // Correct password to proceed to level2
    private String cookie = "";

    public Level1_2(JFrame mainFrame, Player player) {
        super(mainFrame, player);
        this.player = player;
        init();
        player.insertLevel("Level1_2", this);
        player.addAccessTo("Level1_2");
        player.addAccessTo("Level2_2");
    }

    public void init() {
        ImageIcon backgroundImage = new ImageIcon("figs/level1.PNG");
        JLabel label = new JLabel(backgroundImage);
        label.setBounds(0, 0, contentWidth, contentHeight);
        layeredPane.add(label, Integer.valueOf(1));

        getSwordLabel();
        getHint1Label();
        getKeyLabel();
        getCrossHolderLabel();
    }

    private void getKeyLabel() {
        ImageIcon keyIcon = new ImageIcon("figs/key1.PNG");
        Image keyImage = keyIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        keyIcon = new ImageIcon(keyImage);
        JLabel keyLabel = new JLabel(keyIcon);
        keyLabel.setBounds(255, 220, keyIcon.getIconWidth(), keyIcon.getIconHeight());

        keyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        keyLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(2);
                player.showTemporaryMessage("EWW! That's a key!");
                layeredPane.remove(keyLabel);
                layeredPane.repaint();
            }
        });
        layeredPane.add(keyLabel, Integer.valueOf(2));
    }

    @Override
    public void handleKeyInput(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (player.hasAccessTo("Level2_2")) {
                    player.GoTo("Level2_2");
                } else {
                    player.showTemporaryMessage("Access to Level2 is denied.");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (player.hasAccessTo("Level5_2")) {
                    player.GoTo("Level5_2");
                } else {
                    player.showTemporaryMessage("Something unseen blocked the way.");
                }
                break;
        }
    }

    private void getSwordLabel() {
        ImageIcon swordIcon = new ImageIcon("figs/sword.PNG");
        Image swordImage = swordIcon.getImage().getScaledInstance(50, 130, Image.SCALE_SMOOTH);
        swordIcon = new ImageIcon(swordImage);
        JLabel swordLabel = new JLabel(swordIcon);
        swordLabel.setBounds(605, 110,
                swordIcon.getIconWidth(), swordIcon.getIconHeight());

        swordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        swordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.addItemToPackage(1);
                player.showTemporaryMessage("A sword, to whom?");
                layeredPane.remove(swordLabel);
                layeredPane.repaint();
            }
        });

        layeredPane.add(swordLabel, Integer.valueOf(2));
    }

    private void getCrossHolderLabel() {
        ImageIcon crossHolderIcon = new ImageIcon("figs/crossholder.PNG");
        Image crossHolderImage = crossHolderIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        crossHolderIcon = new ImageIcon(crossHolderImage);
        JLabel crossHolderLabel = new JLabel(crossHolderIcon);
        crossHolderLabel.setBounds(355, 480,
                crossHolderIcon.getIconWidth(), crossHolderIcon.getIconHeight());

        crossHolderLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        crossHolderLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(player.hasItem(3) && !player.hasAccessTo("Level5_2")){
                    player.showTemporaryMessage("A cracking sound comes from the right room.");
                    player.addAccessTo("Level5_2");
                }
                else {
                    player.showTemporaryMessage("Something is missing.");
                }
            }
        });

        layeredPane.add(crossHolderLabel, Integer.valueOf(2));
    }

    private void getHint1Label() {
        ImageIcon RedGemIcon = new ImageIcon("figs/hint1.PNG");
        Image redGemImage = RedGemIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        RedGemIcon = new ImageIcon(redGemImage);
        JLabel RedGemLabel = new JLabel(RedGemIcon);
        RedGemLabel.setBounds(200, 360,
                RedGemIcon.getIconWidth(), RedGemIcon.getIconHeight());

        RedGemLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RedGemLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                player.showParchmentHint("Three gems, \nor maybe more...?");
            }
        });

        layeredPane.add(RedGemLabel, Integer.valueOf(2));
    }

}
