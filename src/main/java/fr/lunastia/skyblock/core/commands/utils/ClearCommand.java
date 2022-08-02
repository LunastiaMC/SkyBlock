package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

import java.util.Objects;

@Command("clear")
@Permission("skyblock.clear.command")
public class ClearCommand {
    @Default
    public static void clear(Player player, @APlayerArgument Player target) {
        ColorUtil.sendMessage(player, "Vous venez de vider l'inventaire de §f" + target.getName() + " §7qui contenait un total de §f" + getCount(target) + " items", ColorUtil.PREFIX);
        target.getInventory().clear();
    }

    @Default
    public static void clear(Player player) {
        ColorUtil.sendMessage(player, "Vous venez de vider votre inventaire qui contenait un total de §f" + getCount(player) + " §7items", ColorUtil.PREFIX);
        player.getInventory().clear();
    }

    private static int getCount(Player player) {
        int count = 0;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) != null) {
                count += Objects.requireNonNull(player.getInventory().getItem(i)).getAmount();
            }
        }
        return count;
    }
}
