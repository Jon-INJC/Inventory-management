import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
public class MainChoice extends JFrame implements ActionListener {

    private JButton B1, B2, B3, B4, B5, back;

    public MainChoice(){
        this.setTitle("Main board");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());

        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);
        
        JPanel p= new JPanel();
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground (new Color(74, 0, 0));
        p.setLayout(null); 
        this.add(p); 
        B1 = new JButton("Set new product");
        B1.setBounds (100, 70, 200, 30);
        B1.addActionListener(this);

        B2 = new JButton("Add stock To existig product");
        B2.setBounds(100, 120, 200, 30);
        B2.addActionListener(this);

        B3 = new JButton("Deduct from existing product");
        B3.setBounds(100, 170, 200, 30);
        B3.addActionListener(this);

        B4 = new JButton("Search for a product");
        B4.setBounds(100, 220, 200, 30);
        B4.addActionListener(this);

        B5 = new JButton("Stock Table");
        B5.setBounds(100, 270, 200, 30);
        B5.addActionListener(this);

        back= new JButton("<");
        back.addActionListener(this);

        back.setBounds(5,10,80,30);

        mb.add(back);
        p.add(B1);p.add(B2);p.add(B3);p.add(B4);p.add(B5);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == B1){
            NewProduct newProduct = new NewProduct();
            this.dispose();
        }
        if(e.getSource() == B2){
            AddToStock addToStock = new AddToStock();
            this.dispose();
        }
        if(e.getSource() == B3){
            SubFromStock subFromStock = new SubFromStock();
            this.dispose();
        }
        if(e.getSource() == B4){
            SearchProduct searchProduct = new SearchProduct();
            this.dispose();
        }
        if(e.getSource() == B5){
            SeeTable seeTable = new SeeTable();
            this.dispose();
        }
        if(e.getSource()==back){
            Login logIn = new Login();
            this.dispose();
        }

    }
}  