package fr.lunastia.skyblock.core.listeners;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerListeners implements Listener {
    // TODO: Ajouter un évèment quand le joueur se connecte pour charger sa session
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        Manager.getSessionManager().loadSession(player);
    }

    // TODO: Ajouter un évèment quand le joueur se déconnecte pour sauvegarder sa session

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equals("/help")) {
            event.setCancelled(true);
        }

        // TODO: Ajouter un message qui envoi vers le wiki du serveur, ou un GUI qui permet de naviguer dans le wiki.
    }
}
