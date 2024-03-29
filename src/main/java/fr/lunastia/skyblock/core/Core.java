package fr.lunastia.skyblock.core;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Core extends JavaPlugin {
    public static Core instance;
    private File hat;
    private File island;

    public static Core getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Skyblock is now enabled !");

        // Fichiers de configuration
        this.saveResource("config.yml", false);
        this.saveResource("ranks.yml", true);
        this.saveResource("warps.yml", false);
        this.saveResource("islands.yml", true);

        this.hat = new File(this.getDataFolder(), "hats.yml");
        this.island = new File(this.getDataFolder(), "islands.yml");

        final Manager manager = new Manager();
        try {
            manager.init();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

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

    public File getIslandConfig() {
        return island;
    }
}
