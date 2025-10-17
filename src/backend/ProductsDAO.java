package backend;
import java.sql.*;

import javax.swing.table.DefaultTableModel;
public class ProductsDAO {
    
    public void insertProduct(Product p) throws SQLException{
        String sql = "INSERT INTO Products (product_id, name, batch_no, stock_amount, stock_in_date, exp_date) VALUES (?,?,?,?,?,?)";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setString(1, p.getId());
                    ps.setString(2, p.getName());
                    ps.setString(3, p.getBatchNo());
                    ps.setInt(4, p.getSAmount());
                    ps.setString(5, p.getStockDate());
                    ps.setString(6, p.getExpDate());
                    ps.executeUpdate();
        }

    }

    public boolean checkProduct(Product p) throws SQLException{
        String sql = "SELECT * FROM Products WHERE Product_id = ?";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, p.getId());
                try(ResultSet rs = ps.executeQuery()){
                    return rs.next();
                }
        }
    }

    public void updateAddAmount(Product p) throws SQLException{
        String sql = "SELECT stock_amount FROM Products WHERE product_id = ?";
        String updateSql = "UPDATE Products SET stock_amount = ? WHERE product_id = ?";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        int currentAmount = 0;
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, p.getId());
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        currentAmount = rs.getInt("stock_amount");
                    }
                }
        }
        int newAmount = currentAmount + p.getSAmount();
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(updateSql)){

                ps.setInt(1, newAmount);
                ps.setString(2, p.getId());
                ps.executeUpdate();
        }
    }

    public void updateSubAmount(Product p) throws SQLException{
        String sql = "SELECT stock_amount FROM Products WHERE product_id = ?";
        String updateSql = "UPDATE Products SET stock_amount = ? WHERE product_id = ?";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        int currentAmount = 0;
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, p.getId());
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        currentAmount = rs.getInt("stock_amount");
                    }
                }
        }
        int newAmount = currentAmount - p.getSAmount();
        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(updateSql)){

                ps.setInt(1, newAmount);
                ps.setString(2, p.getId());
                ps.executeUpdate();
        }
    }

    public DefaultTableModel getAllProducts() throws SQLException {
        String[] columnNames = {"Product ID", "Name", "Batch No", "Stock Amount", "Stock In Date", "Exp Date"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        String sql = "SELECT * FROM Products";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("product_id"),
                    rs.getString("name"),
                    rs.getString("batch_no"),
                    rs.getInt("stock_amount"),
                    rs.getString("stock_in_date"),
                    rs.getString("exp_date")
                };
                model.addRow(row);
            }
        }
        return model;
    }

    public int getNetAmount(String id) throws SQLException {
        String sql = "select stock_amount from products where product_id = "+ id;

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        int netAmount = 0;
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                netAmount = rs.getInt("stock_amount");
            }
        }
        return netAmount;
    }

}
