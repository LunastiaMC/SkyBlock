package fr.lunastia.skyblock.core;

import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

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
    }

    @Override
    public void onDisable() {
        getLogger().info("Skyblock is now disabled !");
    }

    public static Core getInstance() {
        return instance;
    }
}
