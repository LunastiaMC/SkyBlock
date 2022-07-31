package fr.lunastia.skyblock.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equals("/help")) {
            event.setCancelled(true);
        }

        // TODO: Ajouter un message qui envoi vers le wiki du serveur, ou un GUI qui permet de naviguer dans le wiki.
    }
}
