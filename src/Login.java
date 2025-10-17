import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import backend.*;
public class Login extends JFrame implements ActionListener{

    private JTextField t1, t2;
    private JPasswordField t3;
    private JButton B, back;

    private String name, uName, pass;
    
    Login(){
        this.setTitle("Log In");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(300,300));
        p.setBackground(new Color(74, 0, 0));

        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        JLabel l = new JLabel("Log In");
        l.setBounds(90, 10, 120, 40);
        l.setForeground(Color.WHITE);
        l.setFont( new Font("Times New Roman",Font.BOLD | Font.ITALIC,35));
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(30, 60, 80, 25);
        l1.setForeground(Color.WHITE);
        l1.setFont( new Font(null,Font.PLAIN,15));

        t1 = new JTextField();
        t1.setBounds(110, 60, 150, 25);


        JLabel l2 = new JLabel("User name");
        l2.setBounds(30, 100, 80, 25);
        l2.setForeground(Color.WHITE);
        l2.setFont( new Font(null,Font.PLAIN,15));

        t2 = new JTextField();
        t2.setBounds(110, 100, 150, 25);


        JLabel l3 = new JLabel(" Password");
        l3.setBounds(30, 140, 80, 25);
        l3.setForeground(Color.WHITE);
        l3.setFont( new Font(null,Font.PLAIN,15));

        t3 = new JPasswordField();
        t3.setBounds(110, 140, 150, 25);
        B = new JButton("Log in");
        B.addActionListener(this);
        B.setForeground(Color.WHITE);
        B.setBackground(new Color(106, 142, 111));
        B.setFont( new Font(null,Font.PLAIN,15));
        B.setBounds(110, 190, 80, 30);

        back =new JButton("<");
        back.addActionListener(this);
        back.setBounds(5,10,80,30);

        mb.add(back);
        p.add(B);
        p.add(t1); p.add(t2);p.add(t3);
        p.add(l1);p.add(l2);p.add(l3);p.add(l);

        p.setLayout(new BorderLayout(5,5));

        this.add(p, BorderLayout.CENTER);
        //this.add(back, BorderLayout.NORTH);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());

    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource()==back){
            Choice choice = new Choice();
            this.dispose();
            return;
        }

        if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getPassword().length == 0){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(e.getSource() == B ){
            name = t1.getText();
            uName = t2.getText();
            pass = new String(t3.getPassword());

            //Logic logic = new Logic();
            UsersDAO usersDAO = new UsersDAO();
            Person p = new Person(name, uName, pass);

            try{
                if(usersDAO.validateUser(p)){
                    //JOptionPane.showMessageDialog(this, "Login Successful!");
                    MainChoice mainChoice = new MainChoice();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Login Failed. Incorrect name, username, or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch (SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}