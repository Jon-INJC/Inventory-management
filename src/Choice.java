import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
public class Choice extends JFrame implements ActionListener {

    private JButton B1, B2;

    public Choice (){
    this.setTitle("Choice");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        

        

    JPanel p = new JPanel();
    p.setPreferredSize(new Dimension(300,300));
    p.setBackground (new Color(74, 0, 0));
    p.setVisible(true);
    p.setLayout(null); 

    JLabel l1 = new JLabel("Welcome");
    l1.setBounds(65, 55, 170, 60);
    l1.setForeground(Color.WHITE);
    l1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC,40));

    B1 = new JButton("Sign in");
    B1.addActionListener(this);
    B1.setForeground(Color.WHITE);
    B1.setBackground(new Color(106, 142, 111));
    B1.setFont( new Font(null,Font.PLAIN,15));
    B1.setBounds(40,155,100, 30);
    B1.setAlignmentX(10);

    B2 = new JButton("Log in");
    B2.addActionListener(this);
    B2.setForeground(Color.WHITE);
    B2.setBackground(new Color(106, 142, 111));
    B2.setFont( new Font(null,Font.PLAIN,15));
    B2.setBounds(160,155,100, 30);

    p.add(B1);p.add(B2);
    p.add(l1);
    this.add(p);

     this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==B1){
            Signin signIn = new Signin();
            this.dispose();
        }else{
            Login logIn = new Login();
            this.dispose();
        }

    }
}
