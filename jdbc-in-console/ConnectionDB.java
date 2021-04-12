import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/test_db";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            System.out.println("\n\t-------Driver ok!-------");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\t-------Geen driver!-------");
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("\n\t---------Fout bij connecting!-------", e);
        }

        if (connection != null) {
            System.out.println("\n\t-------Connection ok!-------\n");
        } else {
            System.out.println("\n\t-------Geen connection!-------");
        }
        return connection;
    }

}
