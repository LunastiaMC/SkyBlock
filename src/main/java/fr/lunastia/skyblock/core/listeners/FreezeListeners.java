package fr.lunastia.skyblock.core.listeners;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeListeners implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (player.isFrozen()) {
                Session session = Manager.getSessionManager().getSession(player);
                if (session.isFreezed()) event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.isFrozen()) {
            Session session = Manager.getSessionManager().getSession(player);
            if (session.isFreezed()) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§bVous ne pouvez pas vous déplacer lorsque vous êtes immobilisé."));
            }
        }
    }
}
