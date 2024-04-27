package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class level1 extends JFrame{
    private JFrame frame;
    private JPanel vasePanel; // Panel to display the vase image
    private JLabel hintLabel; // Label to display hints at the bottom of the screen
    private final String correctPassword = "secret"; // Correct password to proceed to level2

    public level1(JFrame frame) {
        this.frame = frame;
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setupGameArea();
        frame.setVisible(true);
    }

    private void setupGameArea() {
        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("figs/level1.PNG");
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image to cover the entire panel
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        panel.setLayout(new BorderLayout());

        // Setup hint label but don't add it yet
        hintLabel = new JLabel(" ", SwingConstants.CENTER);
        hintLabel.setOpaque(true);
        hintLabel.setBackground(Color.WHITE);
        hintLabel.setPreferredSize(new Dimension(frame.getWidth(), 30));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Assuming the clickable area is in the center of the screen
                Rectangle vaseArea = new Rectangle(350, 250, 100, 100);
                if (vaseArea.contains(e.getPoint())) {
                    showVaseImage(); // Show vase image on the same frame
                }
            }
        });

        panel.setPreferredSize(new Dimension(800, 600));
        frame.setContentPane(panel); // Set the panel as content pane of the frame
        frame.pack();
    }

    private void showVaseImage() {
        // Load the vase image
        ImageIcon vaseImageIcon = new ImageIcon("figs/image.png");

        // Calculate the size and position for the vase image (let's say it's 200x200 pixels)
        int width = 200;
        int height = 200;
        Image vaseImage = vaseImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        vaseImageIcon = new ImageIcon(vaseImage); // Convert back to ImageIcon for the label

        // Create a label to show the vase image
        JLabel vaseLabel = new JLabel(vaseImageIcon);

        // Create a panel to contain the vase label and the input field, with a little padding
        vasePanel = new JPanel(new BorderLayout(10, 10));
        vasePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vasePanel.setBackground(Color.WHITE); // Set the background to white

        // Set the size and position of the panel (in the center of the frame)
        vasePanel.setBounds((frame.getWidth() - width) / 2 - 10, (frame.getHeight() - height) / 2 - 10, width + 20, height + 60);

        // Create the input field and submit button
        JTextField passwordField = new JTextField(10); // Field for entering password
        JButton submitButton = new JButton("Submit");

        // Add listener to the submit button
        submitButton.addActionListener(e -> verifyPassword(passwordField.getText()));

        // Add mouse listener to the panel to close it on click
        vasePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeVasePanel();
            }
        });

        // Create a panel for the input field and submit button
        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);
        inputPanel.add(passwordField);
        inputPanel.add(submitButton);

        // Add components to the vase panel
        vasePanel.add(vaseLabel, BorderLayout.CENTER);
        vasePanel.add(inputPanel, BorderLayout.SOUTH);

        // Add the vase panel to the frame's layered pane so it can float over other components
        frame.getLayeredPane().add(vasePanel, JLayeredPane.MODAL_LAYER);
        frame.getLayeredPane().moveToFront(vasePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void closeVasePanel() {
        frame.getLayeredPane().remove(vasePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void goToLevel2(){
        level2 level2 = new level2(frame);
        this.dispose();
    }

    private void verifyPassword(String enteredPassword) {
        if (enteredPassword.equals(correctPassword)) {
            // Correct password entered, proceed to level2
            closeVasePanel();
            JOptionPane.showMessageDialog(frame, "Yes, indeed...", "Wow", JOptionPane.WARNING_MESSAGE);
            goToLevel2();
        } else {
            // Incorrect password, show error message
            JOptionPane.showMessageDialog(frame, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
