package fr.lunastia.skyblock.core;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.commands.utils.AnnounceCommand;
import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Core extends JavaPlugin {
    public static Core instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Skyblock is now enabled !");

        // Fichiers de configuration
        this.saveResource("ranks.yml", true);
        this.saveResource("config.yml", false);

        Manager.init();

        // Évènements
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        // Commandes
        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.unregister("clear");

        // On charge les sessions des joueurs, si le plugin à été reload
        this.getServer().getOnlinePlayers().forEach(player -> {
            try {
                Manager.getSessionManager().loadSession(player);
            } catch (SQLException e) {
                e.printStackTrace();
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

    public static Core getInstance() {
        return instance;
    }
}
