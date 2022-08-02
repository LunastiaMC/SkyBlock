package fr.lunastia.skyblock.core.listeners;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        Manager.getSessionManager().loadSession(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        Manager.getSessionManager().saveSession(player);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Session session = Manager.getSessionManager().getSession(player);
        event.setFormat(session.getRank().coloredName() + " §7" + player.getName() + " " + session.getRank().arrow() + " §7" + event.getMessage());
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equals("/help")) {
            event.setCancelled(true);
        }

        // TODO: Ajouter un message qui envoi vers le wiki du serveur, ou un GUI qui permet de naviguer dans le wiki.
    }

    @EventHandler
    public void onHatRemove(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        assert item != null;
        if (item.getItemMeta().getDisplayName().contains("Chapeau"))
        {
            event.setCancelled(true);
            ColorUtils.sendMessage(player, "Vous devez retirer le chapeau en faisant /hat remove !", ColorUtils.HAT);
        }
    }
}
