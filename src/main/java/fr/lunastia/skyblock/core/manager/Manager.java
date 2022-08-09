package fr.lunastia.skyblock.core.manager;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.commands.admin.GUICommand;
import fr.lunastia.skyblock.core.commands.admin.RankCommand;
import fr.lunastia.skyblock.core.commands.economy.MoneyCommand;
import fr.lunastia.skyblock.core.commands.economy.PayCommand;
import fr.lunastia.skyblock.core.commands.utils.*;
import fr.lunastia.skyblock.core.database.DatabaseManager;
import fr.lunastia.skyblock.core.listeners.FreezeListeners;
import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import fr.lunastia.skyblock.core.utils.repair.RepairUtils;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    public static final HashMap<String, String> GAMEMODES = new HashMap<>();
    private static SessionManager sessionManager;
    private static RankManager rankManager;
    private static DatabaseManager databaseManager;
    private static GUIManager guiManager;
    private static RepairUtils repairUtils;
    private static HeadDatabaseAPI headDatabase;

    public Manager() {
        repairUtils = new RepairUtils();

        GAMEMODES.put("0", "Survie");
        GAMEMODES.put("1", "Créatif");
        GAMEMODES.put("2", "Aventure");
        GAMEMODES.put("3", "Spectateur");
        GAMEMODES.put("survival", "Survie");
        GAMEMODES.put("creative", "Créatif");
        GAMEMODES.put("adventure", "Aventure");
        GAMEMODES.put("spectator", "Spectateur");
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
    public static HeadDatabaseAPI getHeadDatabaseAPI() {
        return headDatabase;
    }

    public void init() {
        sessionManager = new SessionManager();
        databaseManager = new DatabaseManager();
        rankManager = new RankManager();
        guiManager = new GUIManager();
        headDatabase = new HeadDatabaseAPI();

        // Suppression des commandes de base
        CommandAPI.unregister("clear", true);
        CommandAPI.unregister("gamemode", true);

        // Chargement des commandes
        CommandAPI.registerCommand(RankCommand.class);
        CommandAPI.registerCommand(MoneyCommand.class);
        CommandAPI.registerCommand(PayCommand.class);
        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.registerCommand(ReportCommand.class);
        CommandAPI.registerCommand(ClearCommand.class);
        CommandAPI.registerCommand(EnderInventoryCommand.class);
        CommandAPI.registerCommand(HealCommand.class);
        CommandAPI.registerCommand(FeedCommand.class);
        CommandAPI.registerCommand(FlyCommand.class);
        CommandAPI.registerCommand(HatCommand.class);
        CommandAPI.registerCommand(GameModeCommand.class);
        CommandAPI.registerCommand(CraftCommand.class);
        CommandAPI.registerCommand(VanishCommand.class);
        CommandAPI.registerCommand(InventoryOpenCommand.class);
        CommandAPI.registerCommand(RepairCommand.class);
        CommandAPI.registerCommand(FreezeCommand.class);
        CommandAPI.registerCommand(TrashCommand.class);
        CommandAPI.registerCommand(GUICommand.class);

        // Chargement des évènements
        Core.getInstance().getServer().getPluginManager().registerEvents(new PlayerListeners(), Core.getInstance());
        Core.getInstance().getServer().getPluginManager().registerEvents(new FreezeListeners(), Core.getInstance());
    }
}
