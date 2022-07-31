package fr.lunastia.skyblock.core;

import fr.lunastia.skyblock.core.listeners.PlayerListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    public static Core instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Skyblock is now enabled !");

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
