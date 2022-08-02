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

public class Core extends JavaPlugin {
    public static Core instance;
    private File hat;

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
        CommandAPI.registerCommand(MoneyCommand.class);
        CommandAPI.registerCommand(PayCommand.class);
        CommandAPI.registerCommand(AnnounceCommand.class);
        CommandAPI.registerCommand(ReportCommand.class);
        CommandAPI.registerCommand(ClearCommand.class);
        CommandAPI.registerCommand(EnderchestCommand.class);
        CommandAPI.registerCommand(HealCommand.class);
        CommandAPI.registerCommand(FeedCommand.class);
        CommandAPI.registerCommand(HatCommand.class);
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

    public static Core getInstance() {
        return instance;
    }

    public File getHatConfig() {
        return hat;
    }
}
