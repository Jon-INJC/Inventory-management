import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import backend.*;

public class SubFromStock extends JFrame implements ActionListener {
    JTextField t1, t2, t3;
    JMenuItem newItem, addItem, subItem, findItem, tableItem, backItem;
    JButton B;
    JMenuBar m;
    String Id, Date;
    int amount;

    public SubFromStock(){
        this.setTitle("Deduct from stock");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        

        
    
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground(new Color(74, 0, 0));
        p.setLayout(null);

        JLabel l1 = new JLabel("Product ID");
        l1.setForeground(Color.WHITE);
        l1.setBounds(60, 85, 100, 30);

        t1 = new JTextField();
        t1.setBounds(170, 85, 150, 30);

        JLabel l2 = new JLabel("Stock in Date");
        l2.setForeground(Color.WHITE);
        l2.setBounds(60, 135, 100, 30);

        t2 = new JTextField();
        t2.setBounds(170, 135, 150, 30);

        JLabel l3 = new JLabel("Product amount");
        l3.setForeground(Color.WHITE);
        l3.setBounds(60, 185, 100, 30);

        t3 = new JTextField();
        t3.setBounds(170, 185, 150, 30);

        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        JMenu menu = new JMenu("Menu");
        mb.add(menu);

        newItem = new JMenuItem("New");
        addItem = new JMenuItem("Add");
        subItem = new JMenuItem("Sub");
        findItem = new JMenuItem("Find");
        tableItem = new JMenuItem("Table");
        backItem = new JMenuItem("Main");

        newItem.addActionListener(this);
        addItem.addActionListener(this);
        subItem.addActionListener(this);
        findItem.addActionListener(this);
        tableItem.addActionListener(this);
        backItem.addActionListener(this);

        menu.add(newItem);
        menu.add(addItem);
        menu.add(subItem);
        menu.add(findItem);
        menu.add(tableItem);
        menu.addSeparator();
        menu.add(backItem);

        B = new JButton("Submit");
        B.addActionListener(this);
        B.setBounds(170, 240, 100, 40);


        p.add(l1);p.add(l2);p.add(l3);
        p.add(t1);p.add(t2);p.add(t3);
        p.add(B);

        this.setJMenuBar(mb);
        this.add(p);
        this.setVisible(true);

}

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == newItem){
            NewProduct newProduct = new NewProduct();
            this.dispose();
            return;
        }
        if(e.getSource() == addItem){
            AddToStock addToStock = new AddToStock();
            this.dispose();
            return;
        }
        if(e.getSource() == subItem){
            SubFromStock subFromStock = new SubFromStock();
            this.dispose();
            return;
        }
        if(e.getSource() == findItem){
            SearchProduct searchProduct = new SearchProduct();
            this.dispose();
            return;
        }
        if(e.getSource() == tableItem){
            SeeTable seeTable = new SeeTable();
            this.dispose();
            return;
        }
         else if (e.getSource() == backItem){ 
            MainChoice mainChoice = new MainChoice();
            this.dispose();
            return;
        }

        if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(e.getSource() == B){
            Id = t1.getText();
            Date = t2.getText();
            try{
                amount = Integer.valueOf(t3.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stock amount must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                t3.setText("");
                return;
            }
            try {
                java.time.LocalDate.parse(Date, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Stock Out date must be in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
                t2.setText("");
                return;   
            }

            Transaction t = new Transaction(Date, amount);
            Product p = new Product();
            TransactionDAO transactionDAO = new TransactionDAO();
            ProductsDAO productsDAO = new ProductsDAO();

            p.setProductId(Id);
            p.setProductAmount(amount);

            try{
                transactionDAO.subTransaction(p, t);
                productsDAO.updateSubAmount(p);
                JOptionPane.showMessageDialog(this, "Added seccsesfully!", "Message", JOptionPane.INFORMATION_MESSAGE);
                MainChoice mainChoice = new MainChoice();
                this.dispose();
            }catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}