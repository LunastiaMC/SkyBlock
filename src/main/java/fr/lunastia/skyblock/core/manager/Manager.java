package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.database.DatabaseManager;

public class Manager {
    private static SessionManager sessionManager;
    private static RankManager rankManager;
    private static DatabaseManager databaseManager;

    public static void init() {
        sessionManager = new SessionManager();
        databaseManager = new DatabaseManager();
        rankManager = new RankManager();
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }

    public static RankManager getRankManager() {
        return rankManager;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
