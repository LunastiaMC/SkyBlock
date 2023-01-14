package fr.lunastia.skyblock.core.manager;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.commands.admin.RankCommand;
import fr.lunastia.skyblock.core.commands.economy.MoneyCommand;
import fr.lunastia.skyblock.core.commands.economy.PayCommand;
import fr.lunastia.skyblock.core.commands.island.IslandCommand;
import fr.lunastia.skyblock.core.commands.moderation.*;
import fr.lunastia.skyblock.core.commands.utils.*;
import fr.lunastia.skyblock.core.database.DatabaseManager;
import fr.lunastia.skyblock.core.listeners.FreezeListeners;
import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import fr.lunastia.skyblock.core.utils.repair.RepairUtils;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import java.util.HashMap;

public class Manager {
    private static SessionManager sessionManager;
    private static RankManager rankManager;
    private static DatabaseManager databaseManager;
    private static GUIManager guiManager;
    private static RepairUtils repairUtils;
    private static WarpManager warpManager;
    private static HeadDatabaseAPI headDatabaseAPI;
    private static HeadDatabaseAPI headDatabase;
    private static IslandManager islandManager;
    private static ModerationManager moderationManager;

    public Manager() {
        repairUtils = new RepairUtils();
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

    public static RepairUtils getRepairUtils() {
        return repairUtils;
    }

    public static WarpManager getWarpManager() {
        return warpManager;
    }

    public static HeadDatabaseAPI getHeadDatabaseAPI() {
        return headDatabaseAPI;
    }

    public static IslandManager getIslandManager() {
        return islandManager;
    }

    public static ModerationManager getModerationManager() {
        return moderationManager;
    }

    public void init() {
        sessionManager = new SessionManager();
        databaseManager = new DatabaseManager();
        rankManager = new RankManager();
        guiManager = new GUIManager();
        warpManager = new WarpManager();
        HeadDatabaseAPI headDatabase = new HeadDatabaseAPI();
        moderationManager = new ModerationManager();

        loadCommandes();

        // Chargement des évènements
        Core.getInstance().getServer().getPluginManager().registerEvents(new PlayerListeners(), Core.getInstance());
        Core.getInstance().getServer().getPluginManager().registerEvents(new FreezeListeners(), Core.getInstance());
    }

    public void loadCommandes() {
        // Suppression des commandes de base
        CommandAPI.unregister("clear", true);
        CommandAPI.unregister("gamemode", true);
        CommandAPI.unregister("kick", true);
        CommandAPI.unregister("ban", true);
        CommandAPI.unregister("ban-ip", true);
        CommandAPI.unregister("pardon", true);
        CommandAPI.unregister("pardon-ip", true);

        // Hautes commandes
        CommandAPI.registerCommand(RankCommand.class);
        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.registerCommand(ClearCommand.class);
        CommandAPI.registerCommand(GameModeCommand.class);

        // Modération
        CommandAPI.registerCommand(KickCommand.class);
        CommandAPI.registerCommand(BanCommand.class);
        CommandAPI.registerCommand(LogsCommand.class);
        CommandAPI.registerCommand(VanishCommand.class);
        CommandAPI.registerCommand(FreezeCommand.class);
        CommandAPI.registerCommand(InventoryOpenCommand.class);

        // Economy
        CommandAPI.registerCommand(MoneyCommand.class);
        CommandAPI.registerCommand(PayCommand.class);

        // Grades
        CommandAPI.registerCommand(HealCommand.class);
        CommandAPI.registerCommand(FeedCommand.class);
        CommandAPI.registerCommand(TrashCommand.class);
        CommandAPI.registerCommand(HatCommand.class);
        CommandAPI.registerCommand(RepairCommand.class);
        CommandAPI.registerCommand(EnderInventoryCommand.class);
        CommandAPI.registerCommand(FlyCommand.class);
        CommandAPI.registerCommand(CraftCommand.class);

        // Autres
        CommandAPI.registerCommand(ReportCommand.class);
        CommandAPI.registerCommand(IslandCommand.class);
    }
}
