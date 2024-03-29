package fr.lunastia.skyblock.core.listeners;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeCommon;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Objects;

public class PlayerListeners implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();

        if (!Manager.getModerationManager().isBanned(player)) {
            Manager.getSessionManager().loadSession(player);

            LogTypeCommon log = new LogTypeCommon(EnumLogs.PLAYER_JOIN, player);
            log.send();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        Session session = Manager.getSessionManager().getSession(player);

        if (Manager.getSessionManager().hasSession(player)) {
            Manager.getSessionManager().saveSession(player);

            if (session.hasHat()) {
                player.getInventory().setHelmet(null);
            }
        }

        if (!session.wasKicked()) {
            LogTypeCommon log = new LogTypeCommon(EnumLogs.PLAYER_QUIT, player);
            log.send();
        }
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
    public void onHatRemove(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();

        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
            return;
        }

        if (player.getInventory().getHelmet() == null || player.getInventory().getHelmet().getType() == Material.AIR) {
            return;
        }

        assert item != null;
        String inventoryName = event.getView().getTitle();
        // TODO: Mettre dans un array puis .contains() pour ne pas avoir à tester toutes les valeurs.

        if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().contains("Chapeau")) {
            event.setCancelled(true);
            ColorUtils.sendMessage(player, "Vous devez retirer le chapeau en faisant §d/hat remove §7!", Colors.HAT);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getInventory().getHelmet() == null || event.getEntity().getInventory().getHelmet().getType() == Material.AIR) {
            return;
        }

        Session session = Manager.getSessionManager().getSession(event.getEntity());
        if (session.hasHat()) {
            event.getDrops().remove(event.getEntity().getInventory().getHelmet());
        }
    }
}
