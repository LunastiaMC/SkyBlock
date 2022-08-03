package fr.lunastia.skyblock.core;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public class Core extends JavaPlugin {
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

        final Manager manager = new Manager();
        manager.init();

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
