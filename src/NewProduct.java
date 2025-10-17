import java.awt.event.*;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

import backend.*;

public class NewProduct extends JFrame implements ActionListener {

    private JTextField t1, t2, t3, t4, t5, t6;
    JMenuItem newItem, addItem, subItem, findItem, tableItem, backItem;
    private JButton B1;
        


    private String pName, batchNo, pId;
    private int sAmount;
    private String sDate, expDate;


    public NewProduct(){
        this.setTitle("New product");
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground (new Color(74, 0, 0));
        p.setLayout(null); 
        this.add(p);

        int x= 150;

        JLabel l1 = new JLabel("Product Name");
        l1.setBounds(30, 15, 100, 100);
        l1.setForeground(Color.WHITE);
        t1 = new JTextField();
        t1.setBounds(x, 50, 150, 30);
        t1.setForeground(Color.BLACK);

        JLabel l2 = new JLabel("Product Id");
        l2.setBounds(40, 65, 100, 100);
        l2.setForeground(Color.WHITE);
        t2 = new JTextField();
        t2.setBounds(x, 100, 150, 30);
        t2.setForeground(Color.BLACK);

        JLabel l3 = new JLabel("Batch Number");
        l3.setBounds(30, 115, 100, 100);
        l3.setForeground(Color.WHITE);
        t3 = new JTextField();
        t3.setBounds(x, 150, 150, 30);
        t3.setForeground(Color.BLACK);

        JLabel l4 = new JLabel("Number of stock");
        l4.setBounds(15, 165, 100, 100);
        l4.setForeground(Color.WHITE);
        t4 = new JTextField();
        t4.setBounds(x, 200, 150, 30);
        t4.setForeground(Color.BLACK);

        JLabel l5 = new JLabel("Stock In date");
        l5.setBounds(30, 215, 100, 100);
        l5.setForeground(Color.WHITE);
        t5 = new JTextField();
        t5.setBounds(x, 250, 150, 30);
        t5.setForeground(Color.BLACK);

        JLabel l6 = new JLabel("Exp Date");
        l6.setBounds(50, 265, 100, 100);
        l6.setForeground(Color.WHITE);
        t6 = new JTextField();
        t6.setBounds(x, 300, 150, 30);
        t6.setForeground(Color.BLACK);

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

        B1 = new JButton("Add Product");
        B1.addActionListener(this);
        B1.setBounds(120, 350, 150, 40);


        p.add(B1);
        p.add(t1); p.add(t2);p.add(t3);p.add(t4);p.add(t5);p.add(t6);
        p.add(l1);p.add(l2);p.add(l3);p.add(l4);p.add(l5);p.add(l6);
        this.setJMenuBar(mb);
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

        if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t5.getText().isEmpty() || t6.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(e.getSource() == B1){
            pName = t1.getText();
            pId = t2.getText();
            batchNo = t3.getText();
             try{
                sAmount = Integer.valueOf(t4.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Stock amount must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
                t4.setText("");
                return;
            }
            sDate = t5.getText();
            expDate = t6.getText();

            try {
                java.time.LocalDate.parse(sDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                java.time.LocalDate.parse(expDate, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (java.time.format.DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Stock In date must be in YYYY-MM-DD format.", "Error", JOptionPane.ERROR_MESSAGE);
                t5.setText("");
                t6.setText("");
                return;   
            }

            TransactionDAO transactionDAO = new TransactionDAO();
            ProductsDAO productsDAO = new ProductsDAO();
            Product p = new Product(pName, pId, batchNo, sAmount, sDate, expDate);

            try{
                if(!productsDAO.checkProduct(p)){
                    productsDAO.insertProduct(p);
                    //logic.addDate(p, sDate, sAmount);
                    JOptionPane.showMessageDialog(this, "Product Add Successful!");
                    transactionDAO.addTransaction(p, new Transaction(sDate, sAmount));
                    MainChoice mainChoice = new MainChoice();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Product already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(SQLDataException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid data format. Please check your inputs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }
}
