package fr.lunastia.skyblock.core.manager;
import fr.lunastia.skyblock.core.database.DatabaseManager;
public class Manager {
    private static DatabaseManager databaseManager;
    public static void init() {
        databaseManager = new DatabaseManager();
    }
    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
