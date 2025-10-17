
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

import backend.*;
public class Signin extends JFrame implements ActionListener {
    
    private JTextField t1, t2;
    private JPasswordField t3, t4;
    private JButton B,back;
    JPanel p = new JPanel();

    private String name, uName, pass, coPass;

    public Signin(){
        this.setTitle("Sign in");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        

        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground(new Color(74, 0, 0));
        p.setLayout(null);

        JLabel l = new JLabel("Sign In");
        l.setBounds(150, 10, 120, 40);
        l.setForeground(Color.WHITE);
        l.setFont( new Font("Times New Roman",Font.BOLD | Font.ITALIC,35));

        JLabel l1 = new JLabel("Name");
        l1.setBounds(100, 45, 100, 100);
        l1.setForeground(Color.WHITE);
        l1.setFont( new Font(null,Font.PLAIN,15));
        t1 = new JTextField();
        t1.setBounds(150, 80, 150, 30);

        JLabel l2 = new JLabel("User name");
        l2.setBounds(70, 95, 100, 100);
        l2.setForeground(Color.WHITE);
        l2.setFont( new Font(null,Font.PLAIN,15));
        t2 = new JTextField();
        t2.setBounds(150, 130, 150, 30);

        JLabel l3 = new JLabel("password");
        l3.setBounds(70, 145, 100, 100);
        l3.setForeground(Color.WHITE);
        l3.setFont( new Font(null,Font.PLAIN,15));
        t3 = new JPasswordField();
        t3.setBounds(150, 180, 150, 30);

        /*JLabel l4 = new JLabel("Company pas");
        l4.setBounds(40, 195, 100, 100);
        l4.setForeground(Color.WHITE);
        l4.setFont( new Font(null,Font.PLAIN,15));
        t4 = new JPasswordField();
        t4.setBounds(150, 230, 150, 30);*/

        B = new JButton("Sign in");
        B.addActionListener(this);
        B.setForeground(Color.WHITE);
        B.setBackground(new Color(106, 142, 111));
        B.setFont( new Font(null,Font.PLAIN,15));
        B.setBounds(160, 280, 80, 40);

        back= new JButton("<");
        back.addActionListener(this);
        back.setBounds(5,10,80,30);

        mb.add(back);
        p.add(B);
        p.add(t1); p.add(t2);p.add(t3);
        p.add(l1);p.add(l2);p.add(l3);p.add(l);

        //this.add(back);
        this.add(p);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(e.getSource()==B){
            name = t1.getText();
            uName = t2.getText();
            pass = String.valueOf(t3.getPassword());

            //Logic logic = new Logic();
            UsersDAO usersDAO = new UsersDAO();
            Person P = new Person(name, uName, pass);

            try{
                if(pass.length() < 8){
                    JLabel l5 = new JLabel("Password can not be less than 8 characters");
                    l5.setBounds(100, 158, 250, 130);
                    l5.setFont(new Font(null, Font.PLAIN,10));
                    l5.setForeground(Color.RED);
                    p.add(l5);
                    p.revalidate();
                    p.repaint();
                    t3.setText("");
                    return;
                }
                if(usersDAO.validateUser(P)){
                    JOptionPane.showMessageDialog(this, "Information already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    Login logIn = new Login();
                    this.dispose();
                    return;
                }

                if(!usersDAO.validateUser(P)){
                    usersDAO.insertUser(P);
                    //JOptionPane.showMessageDialog(this, "sign in Successful!", "Signed in", JOptionPane.INFORMATION_MESSAGE);
                    Login logIn = new Login();
                    this.dispose();
                }

            }catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource()==back){
            Choice choice = new Choice();
            this.dispose();

        }
    }

}
