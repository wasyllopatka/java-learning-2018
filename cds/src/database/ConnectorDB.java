package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB{

static {
    try {
        DriverManager.registerDriver(new org.postgresql.Driver());
    } catch (SQLException e) {
        throw new ExceptionInInitializerError("No driver");
    }
}

public static Connection getConnection() {
    Connection connection;
    try {
        String url = "jdbc:postgresql://127.0.0.1:5432/cds";
        String user = "postgres";
        String pass = "root";
        connection = DriverManager.getConnection(url, user, pass);
    } catch (SQLException e) {
        throw new ExceptionInInitializerError("No connection");
    }
    return connection;
    }
}
