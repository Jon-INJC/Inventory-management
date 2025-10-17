package backend;
import java.sql.*;

import javax.swing.table.DefaultTableModel;
public class TransactionDAO {

    public void addTransaction(Product p, Transaction t) throws SQLException{
        String sql = "INSERT INTO Transactions (product_id, transaction_date, amount, type) VALUES (?,?,?,?)";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setString(1, p.getId());
                    ps.setString(2, t.getDate());
                    ps.setInt(3, t.getAmount());
                    ps.setString(4, "IN");
                    ps.executeUpdate();
        }
    }

    public void subTransaction(Product p, Transaction t) throws SQLException{
        String sql = "INSERT INTO Transactions (product_id, transaction_date, amount, type) VALUES (?,?,?,?)";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = conn.prepareStatement(sql)){

                    ps.setString(1, p.getId());
                    ps.setString(2, t.getDate());
                    ps.setInt(3, t.getAmount());
                    ps.setString(4, "OUT");
                    ps.executeUpdate();
        }
    }
    public DefaultTableModel getProductInfo(String id) throws SQLException {
        String[] columnNames = {"Date", "Amount", "T-Type"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        String sql = "SELECT transaction_date, amount, type FROM Transactions t JOIN products p ON p.product_id = t.product_id WHERE p.product_id = "+ id +" ORDER BY t.transaction_date";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                    rs.getString("transaction_date"),
                    rs.getInt("amount"),
                    rs.getString("type")
                };
                model.addRow(row);
            }
        }
        return model;
    }
}