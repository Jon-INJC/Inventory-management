import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import backend.*;
public class SeeTable extends JFrame implements ActionListener {

    JTable t;
    JMenuItem newItem, addItem, subItem, findItem, tableItem, backItem;
    
    
    public SeeTable(){
        this.setTitle("Table");
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/frame.png"));
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(255, 240, 245));
        this.setSize(getMaximumSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
         

        ProductsDAO dao = new ProductsDAO();
        //Logic logic = new Logic();
        t = new JTable();
        try{
            t.setModel(dao.getAllProducts());
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }

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

        t.setBounds(100, 70, 300, 300);
        t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

         JScrollPane s = new JScrollPane(t);
         s.setPreferredSize(new Dimension(400,350));

        this.add(s);
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