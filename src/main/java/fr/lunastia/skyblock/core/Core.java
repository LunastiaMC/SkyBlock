package fr.lunastia.skyblock.core;

import dev.jorel.commandapi.CommandAPI;
import fr.lunastia.skyblock.core.commands.utils.AnnounceCommand;
import fr.lunastia.skyblock.core.commands.utils.DiscordCommand;
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

        this.saveResource("ranks.yml", true);
        this.saveResource("config.yml", false);

        Manager.init();
        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.registerCommand(DiscordCommand.class);
        CommandAPI.unregister("clear");
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
