package GUI;

import application.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateGroupView extends JFrame {

    public static JPanel panel;
    public static JLabel inputLabel;
    public static JTextField inputText;

    public static JButton okButton;
    Font font = new Font("Open Sans", Font.BOLD, 12);

    public CreateGroupView() {

        this.setTitle("Add");
        this.setResizable(false);
        this.setSize(300, 120);
        this.centreWindow(this);
        this.setLayout(null);
        
    }

    public void displayGUI() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, 120);
        inputLabel = new JLabel("Input");
        inputLabel.setBounds(10, 10, 80, 25);
        inputLabel.setFont(font);
        panel.add(inputLabel);

        inputText = new JTextField(20);
        inputText.setText("");
        inputText.setBounds(100, 10, 160, 25);
        panel.add(inputText);

        okButton = new JButton("OK");
        okButton.setBounds(100, 50, 80, 20);
        okButton.setBackground(new Color(244, 244, 244));
        okButton.setFocusPainted(false);
        okButton.setFont(font);
        okButton.addActionListener((ActionEvent e) -> {
            String groupname = inputText.getText();
            if (groupname.equals("")) {
                JOptionPane.showMessageDialog(null, "Không được để trống ");
            } else {
                Client.systemSendMessage("addgroup#" + groupname);
            }
        });
        panel.add(okButton);

        this.add(panel);
    }

    public static void alert(String string) {
        JOptionPane.showMessageDialog(null, string);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

//    public static void main(String[] args) {
//        InputView t = new InputView();
//        t.displayGUI();
//        t.setVisible(true);
//    }
}
