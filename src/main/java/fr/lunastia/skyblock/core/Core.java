package fr.lunastia.skyblock.core;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.commands.economy.MoneyCommand;
import fr.lunastia.skyblock.core.commands.economy.PayCommand;
import fr.lunastia.skyblock.core.commands.utils.*;
import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;

public class Core extends JavaPlugin {
    public static final HashMap<String, String> GAMEMODES = new HashMap<>();
    public static Core instance;
    private File hat;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Skyblock is now enabled !");
        this.hat = new File(this.getDataFolder(), "hats.yml");

        // Fichiers de configuration
        this.saveResource("config.yml", false);
        this.saveResource("ranks.yml", true);
        this.saveResource("hats.yml", true);

        Manager.init();

        // Évènements
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        // Commandes
        // TODO: Mettre le chargement de commandes ainsi que des évènements dans un gestionnaire
        CommandAPI.unregister("clear", true);
        CommandAPI.unregister("gamemode", true);
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

        GAMEMODES.put("0", "Survie");
        GAMEMODES.put("1", "Créatif");
        GAMEMODES.put("2", "Aventure");
        GAMEMODES.put("3", "Spectateur");
        GAMEMODES.put("survival", "Survie");
        GAMEMODES.put("creative", "Créatif");
        GAMEMODES.put("adventure", "Aventure");
        GAMEMODES.put("spectator", "Spectateur");

        // On charge les sessions des joueurs, si le plugin à été reload
        this.getServer().getOnlinePlayers().forEach(player -> {
            try {
                Manager.getSessionManager().loadSession(player);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onDisable() {
        Manager.getSessionManager().getSessions().forEach((string, session) -> {
            try {
                Manager.getSessionManager().saveSession(session.getPlayer());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public File getHatConfig() {
        return hat;
    }
}
