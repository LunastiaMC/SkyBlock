package fr.lunastia.skyblock.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final DatabaseCredentials databaseCredentials;
    private Connection connection;

    public DatabaseConnection(DatabaseCredentials databaseCredentials) {
        this.databaseCredentials = databaseCredentials;
    }

    private void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.databaseCredentials.toURI(), this.databaseCredentials.getUser(), this.databaseCredentials.getPassword());
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.isClosed()) this.connection.close();
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.connection != null) {
            if (this.connection.isClosed()) {
                return this.connection;
            }
        }
        connect();
        return this.connection;
    }
}