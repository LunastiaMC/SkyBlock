package fr.lunastia.skyblock.core.manager;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.commands.economy.MoneyCommand;
import fr.lunastia.skyblock.core.commands.economy.PayCommand;
import fr.lunastia.skyblock.core.commands.utils.*;
import fr.lunastia.skyblock.core.database.DatabaseManager;
import fr.lunastia.skyblock.core.listeners.PlayerListeners;

import java.util.HashMap;

public class Manager {
    public static final HashMap<String, String> GAMEMODES = new HashMap<>();
    private static SessionManager sessionManager;
    private static RankManager rankManager;
    private static DatabaseManager databaseManager;
    private static GUIManager guiManager;

    public Manager() {
        GAMEMODES.put("0", "Survie");
        GAMEMODES.put("1", "Créatif");
        GAMEMODES.put("2", "Aventure");
        GAMEMODES.put("3", "Spectateur");
        GAMEMODES.put("survival", "Survie");
        GAMEMODES.put("creative", "Créatif");
        GAMEMODES.put("adventure", "Aventure");
        GAMEMODES.put("spectator", "Spectateur");
        System.out.println(GAMEMODES);
    }

    public void init() {
        sessionManager = new SessionManager();
        databaseManager = new DatabaseManager();
        rankManager = new RankManager();
        guiManager = new GUIManager();

        // Suppression des commandes de base
        CommandAPI.unregister("clear", true);
        CommandAPI.unregister("gamemode", true);

        // Chargement des commandes
        CommandAPI.registerCommand(MoneyCommand.class);
        CommandAPI.registerCommand(PayCommand.class);
        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.registerCommand(ReportCommand.class);
        CommandAPI.registerCommand(ClearCommand.class);
        CommandAPI.registerCommand(EnderchestCommand.class);
        CommandAPI.registerCommand(HealCommand.class);
        CommandAPI.registerCommand(FeedCommand.class);
        CommandAPI.registerCommand(FlyCommand.class);
        CommandAPI.registerCommand(HatCommand.class);
        CommandAPI.registerCommand(GameModeCommand.class);

        // Chargement des évènements
        Core.getInstance().getServer().getPluginManager().registerEvents(new PlayerListeners(), Core.getInstance());
    }

    public static HashMap<String, String> getStringGamemodes() {
        return GAMEMODES;
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

    public static GUIManager getGUIManager() {
        return guiManager;
    }
}
