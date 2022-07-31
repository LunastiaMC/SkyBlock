package fr.lunastia.skyblock.core.database;

public class DatabaseCredentials {
    private final String host;
    private final String user;
    private final String pass;
    private final String databaseName;

    public DatabaseCredentials(String host, String user, String pass, String databaseName) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.databaseName = databaseName;
    }

    public String toURI() {
        int port = 3306;
        return "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?user=" + user + "&password=" + pass;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return pass;
    }
}