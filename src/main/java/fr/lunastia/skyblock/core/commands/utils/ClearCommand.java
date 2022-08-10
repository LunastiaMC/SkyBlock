package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

@Command("clear")
@Permission("skyblock.clear.command")
public class ClearCommand {
    @Default
    public static void clear(Player player, @APlayerArgument Player target) {
        ColorUtils.sendMessage(player, "Vous venez de vider l'inventaire de §f" + target.getName() + " §7qui contenait un total de §f" + ItemUtils.countInventory(target.getInventory()) + " items", Colors.PREFIX);
        target.getInventory().clear();
    }

    @Default
    public static void clear(Player player) {
        ColorUtils.sendMessage(player, "Vous venez de vider votre inventaire qui contenait un total de §f" + ItemUtils.countInventory(player.getInventory()) + " §7items", Colors.PREFIX);
        player.getInventory().clear();
    }
}
