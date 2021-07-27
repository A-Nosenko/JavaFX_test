package patient.registration.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private ConnectionFactory() {
    }

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:sqlite:test.db";
            try {
                connection = DriverManager.getConnection(url);
                connection.createStatement().execute("PRAGMA foreign_keys = ON");
            } catch (SQLException exception) {
                System.err.println(exception.toString());
            }
        }
        return connection;
    }
}
