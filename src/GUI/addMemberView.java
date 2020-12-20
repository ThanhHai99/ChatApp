package GUI;

import application.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class addMemberView extends JFrame {

    public static JPanel panel;
    public static JLabel userLabel;
    public static JTextField userText;

    public static JButton okButton;
    Font font = new Font("Open Sans", Font.BOLD, 12);

    public addMemberView() {

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
        
        userLabel = new JLabel("UserName");
        userLabel.setBounds(10, 10, 80, 25);
        userLabel.setFont(font);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setText("");
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);
        
        
        okButton = new JButton("OK");
        okButton.setBounds(100, 50, 80, 20);
        okButton.setBackground(new Color(244, 244, 244));
        okButton.setFocusPainted(false);
        okButton.setFont(font);
        okButton.addActionListener((ActionEvent e) -> {
            String username = userText.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "Không được để trống ");
            } else {
                Client.systemSendMessage("addmember#" + Client.chatTo + "#" + username);
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
