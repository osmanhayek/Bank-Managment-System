package database.operations;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DbConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bank_app";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
