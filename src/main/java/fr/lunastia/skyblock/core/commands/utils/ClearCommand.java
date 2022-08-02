package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("clear")
@Permission("skyblock.clear.command")
public class ClearCommand {
    @Default
    public static void clear(Player player, @APlayerArgument Player target) {
        final int inventorySize = target.getInventory().getContents().length;
        target.getInventory().clear();
        ColorUtil.sendMessage(player, "Vous venez de vider l'inventaire de §f" + target.getName() + " §7qui contenait un total de §f" + inventorySize + " items", ColorUtil.PREFIX);
    }

    @Default
    public static void clear(Player player) {
        final int inventorySize = player.getInventory().getContents().length;
        player.getInventory().clear();
        ColorUtil.sendMessage(player, "Vous venez de vider votre inventaire qui contenait un total de §f" + inventorySize + " §7items", ColorUtil.PREFIX);
    }
}
