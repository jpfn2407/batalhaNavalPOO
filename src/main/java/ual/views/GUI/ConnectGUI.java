package ual.views.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectGUI extends JFrame  {
    JFrame frame = new JFrame();
    private JTextField textField1;
    private JPanel mainPanel;
    private JButton connectButton;


    public ConnectGUI(){
        frame.setTitle("Connect");
        //this.setUndecorated(true);
        frame.setSize(300,75);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setContentPane(mainPanel);
        frame.setResizable( false );
        frame.setLocationRelativeTo(null);
    }



}
