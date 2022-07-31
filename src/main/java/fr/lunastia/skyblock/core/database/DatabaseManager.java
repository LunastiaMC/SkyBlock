package fr.lunastia.skyblock.core.database;

import fr.lunastia.skyblock.core.Core;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.SQLException;

public class DatabaseManager {

    private final DatabaseConnection database;

    public DatabaseManager() {
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Core.getInstance().getDataFolder(), "config.yml"));
        ConfigurationSection file = config.getConfigurationSection("database");

        assert file != null;
        this.database = new DatabaseConnection(new DatabaseCredentials(file.getString("host"), file.getString("username"), file.getString("password"), file.getString("database")));
    }

    public DatabaseConnection getDatabase() {
        return database;
    }

    public void close() {
        try {
            this.database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}