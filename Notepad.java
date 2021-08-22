package com.hassanRaza;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener
{
    JTextArea area;
    JScrollPane pane;
    String text;
    Notepad()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.getHeight();
        screenSize.getWidth();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setBounds(0, 0, screenWidth, screenHeight);
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newDoc = new JMenuItem("New");
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK ));
        newDoc.addActionListener(this);
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK ));
        open.addActionListener(this);
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK ));
        save.addActionListener(this);
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK ));
        print.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0 ));
        exit.addActionListener(this);

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);


        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK ));
        copy.addActionListener(this);
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK ));
        paste.addActionListener(this);
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK ));
        cut.addActionListener(this);
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK ));
        selectAll.addActionListener(this);

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About Notepad");
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.SHIFT_MASK ));
        about.addActionListener(this);

        help.add(about);

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN, 20 ));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setForeground(Color.WHITE);
        area.setBackground(Color.BLACK);
        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open"))
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION)
            {
                return;
            }

            File file = chooser.getSelectedFile();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }
            catch (Exception e)
            {}
        }
        else if(ae.getActionCommand().equals("Save"))
        {
            JFileChooser save = new JFileChooser();
            save.setApproveButtonText("Save");
            int action = save.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION)
            {
                return;
            }

            File filename = new File(save.getSelectedFile() + ".txt");
            BufferedWriter writer = null;

            try
            {
                writer = new BufferedWriter(new FileWriter(filename));
                area.write(writer);
            }
            catch (Exception e)
            {}
        }
        else if(ae.getActionCommand().equals("Print"))
        {
            try{
                area.print();
            }
            catch (Exception e){};
        }
        else if(ae.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy"))
        {
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste"))
        {
            area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut"))
        {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All"))
        {
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About Notepad"))
        {
            new About().setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        new Notepad().setVisible(true);
    }
}
