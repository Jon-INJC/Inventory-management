package backend;
import java.sql.*;
public class UsersDAO {
    
    public void insertUser(Person p) throws SQLException {
       String sql = "INSERT INTO Users (name, User_Name, pass) VALUES (?,?,?)";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

      try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, p.getName());
                ps.setString(2, p.getUName());
                ps.setString(3, p.getPass());
                ps.executeUpdate();
        }
    }

    public boolean validateUser(Person p) throws SQLException {
        String sql = "SELECT * FROM Users WHERE Name = ? AND User_Name = ? AND pass = ?";

        String url = "jdbc:mysql://localhost:3306/stock";
        String user = "root";
        String password = "#Fafyon123";

        try(Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, p.getName());
                ps.setString(2, p.getUName());
                ps.setString(3, p.getPass());
                try(ResultSet rs = ps.executeQuery()){
                    return rs.next();
                }
            }
    }
}