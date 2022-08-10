package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Command("fly")
public class FlyCommand {
    @Default
    @Permission("skyblock.fly.command")
    public static void fly(Player player) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en créatif !", Colors.PREFIX);
            return;
        }

        if (player.getWorld().getName().equals(player.getUniqueId() + "") || player.isOp()) {
            HashMap<Boolean, String> map = new HashMap<>();
            map.put(false, "d'§factiver§7");
            map.put(true, "de §fdésactiver§7");

            boolean fly = player.getAllowFlight();
            player.setAllowFlight(!fly);
            if (!fly) player.setFlying(false);
            ColorUtils.sendMessage(player, "Vous venez " + map.get(fly) + " le §fvol §7!", Colors.PREFIX);
        } else {
            ColorUtils.sendMessage(player, "Vous devez effectuer cette commande dans votre île !", Colors.PREFIX);
        }
    }
}
