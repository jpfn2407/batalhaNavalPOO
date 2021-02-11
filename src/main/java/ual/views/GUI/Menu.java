package ual.views.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{
    JFrame frame = new JFrame();
    private JPanel mainPanel;
    private JButton findGameButton;
    private JButton startGameButton;
    private JTextField defaultTextField;
    private JButton setNameButton;
    private JLabel nameLabel;
    private String newName = "New name set";

    public Menu(){
        frame.setTitle("BattleSimps Menu");
        frame.setResizable( false );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setUndecorated(true);
        frame.setContentPane(mainPanel);
        frame.setSize(350, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

        this.findGameButton.addActionListener(this);

    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==findGameButton){
            ConnectGUI connectGUI = new ConnectGUI();
        }
    }

}
