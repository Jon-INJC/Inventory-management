import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;
import backend.*;
public class DeleteProduct extends JFrame implements ActionListener {

    JTextField t1;
    JButton B;
    JMenuItem newItem, addItem, subItem, findItem, tableItem, backItem;
    JMenuBar m;
    String id;

    public DeleteProduct(){
       this.setTitle("Delete Products");
       
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

        JLabel l = new JLabel("Delete Product");
        l.setBounds(100, 30, 200, 60);
        l.setForeground(Color.WHITE);
        l.setFont( new Font("Times New Roman",Font.BOLD | Font.ITALIC,25));

        JLabel l1 = new JLabel("Product ID");
        l1.setBounds(20, 80, 100, 100);
        l1.setForeground(Color.WHITE);
        l1.setFont( new Font(null,Font.PLAIN,15));

        t1 = new JTextField();
        t1.setBounds(120, 110, 150, 30);

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

        if(t1.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please enter a product name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(e.getSource() == B){
            id = t1.getText();
            Product p = new Product();
            p.setProductId(id);
            ProductsDAO pDao = new ProductsDAO();
            TransactionDAO transactionDAO = new TransactionDAO();
            try{
                if(pDao.DeleteAProduct(id) ){
                   JOptionPane.showMessageDialog(this, "Product deleted successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                    MainChoice mainChoice = new MainChoice();
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "No product in stock", "Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(SQLException ex){
                ex.printStackTrace();
                 JOptionPane.showMessageDialog(this, "An error occurred while accessing the user data.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
