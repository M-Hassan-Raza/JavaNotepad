package com.hassanRaza;

import  javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class About extends JFrame implements ActionListener
{
    JButton okayButton;
    About()
    {
        setBounds(600, 200, 700, 600);
        setLayout(null);

        ImageIcon imgIcon = new ImageIcon(ClassLoader.getSystemResource("com/hassanRaza/images/windows.png"));
        Image scaledImage = imgIcon.getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(scaledImage);
        JLabel iIcon = new JLabel(i3);
        iIcon.setBounds(225, 40, 240, 240);
        add(iIcon);

        ImageIcon imgIcon2 = new ImageIcon(ClassLoader.getSystemResource("com/hassanRaza/images/notepad.png"));
        Image scaledImage2 = imgIcon2.getImage().getScaledInstance(72, 72, Image.SCALE_DEFAULT);
        ImageIcon i4 = new ImageIcon(scaledImage2);
        JLabel iIcon2 = new JLabel(i4);
        iIcon2.setBounds(50, 225, 72, 72);
        add(iIcon2);

        JLabel aboutLabel = new JLabel("<html>Muhammad Hassan Raza<br>Version 1.0<br>All Rights Reserved</html>");
        aboutLabel.setBounds(250, 200, 500, 300);
        aboutLabel.setFont(new Font("SAN_SARIF", Font.PLAIN, 18));
        add(aboutLabel);

        okayButton = new JButton("OK");
        okayButton.setBounds(280,500,100,30);
        okayButton.addActionListener(this);
        add(okayButton);
    }

    public void actionPerformed(ActionEvent ae)
    {
        this.setVisible(false);

    }

    public static void main(String[] args)
    {
        new About().setVisible(true);
    }
}

