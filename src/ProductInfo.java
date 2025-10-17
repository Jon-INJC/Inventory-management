import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import backend.*;
public class ProductInfo extends JFrame implements ActionListener{

    private JTable t;
    private JLabel l1, l2, l3, l4;
    JMenuItem newItem, addItem, subItem, findItem, tableItem, backItem;
    

    public ProductInfo(String id){

        this.setTitle("Product Info");
        
        //Logic logic = new Logic();
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t = new JTable();
        TransactionDAO transactionDAO = new TransactionDAO();
        ProductsDAO productsDAO = new ProductsDAO();

         try {
            t.setModel(transactionDAO.getProductInfo(id));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while accessing the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        l3 = new JLabel("Product Id:");
        l4 = new JLabel("Net Amount:");

        try{
            l2 = new JLabel(String.valueOf(productsDAO.getNetAmount(id)));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        l1 = new JLabel(id);


        l1.setBounds(240, 60, 100,30);
        l1.setForeground(Color.WHITE);
        l1.setFont( new Font(null,Font.PLAIN,15));
        l3.setBounds(135, 60, 150,30);
        l3.setForeground(Color.WHITE);
        l3.setFont( new Font(null,Font.PLAIN,15));

        l2.setBounds(225, 320, 100, 30);
        l2.setForeground(Color.WHITE);
        l2.setFont( new Font(null,Font.PLAIN,15));
        l4.setBounds(135, 320, 100, 30);
        l4.setForeground(Color.WHITE);
        l4.setFont( new Font(null,Font.PLAIN,15));

        t.setBounds(150, 50, 100, 100);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

    

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

        JScrollPane s = new JScrollPane(t);
         s.setBounds(100,100,200,200);

         JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground(new Color(74, 0, 0));
        p.setLayout(null);

        p.add(s);
       p.add(l3); 
       p.add(l4);
       p.add(l1); 
       p.add(l2);
        
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
    }
    
}