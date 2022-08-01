package fr.lunastia.skyblock.core.listeners;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        Manager.getSessionManager().loadSession(player);
    }

    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        Manager.getSessionManager().saveSession(player);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equals("/help")) {
            event.setCancelled(true);
        }

        // TODO: Ajouter un message qui envoi vers le wiki du serveur, ou un GUI qui permet de naviguer dans le wiki.
    }
}
