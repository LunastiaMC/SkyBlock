package fr.lunastia.skyblock.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Skyblock is now enabled !");
    }
    @Override
    public void onDisable() {
        getLogger().info("Skyblock is now disabled !");
    }
}
