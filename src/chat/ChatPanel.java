package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel {

    JPanel panel;
    JTextArea messageArea;
    JTextField inputField;
    JScrollBar scrollBar;

    public ChatPanel(JPanel _panel) {
        panel = _panel;
        panel.removeAll();
        createDisplay();
        panel.revalidate();
        panel.repaint();
    }

    private void createDisplay() {
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollBar = scrollPane.getVerticalScrollBar();

        inputField = new JTextField();
        inputField.addActionListener(new inputListener());

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputField, BorderLayout.SOUTH);
    }


    class inputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputText = inputField.getText();
            messageArea.append(inputText +"\n");
            scrollBar.setValue(scrollBar.getMaximum());
            inputField.setText("");
        }
    }
}
