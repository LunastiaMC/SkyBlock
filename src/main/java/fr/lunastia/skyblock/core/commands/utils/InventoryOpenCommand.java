package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("invsee")
@Permission("skyblock.invsee.command")
public class InventoryOpenCommand {
    @Default
    public static void invsee(Player player, @APlayerArgument Player target) {
        if (target.getName().equals(player.getName())) {
            ColorUtils.sendMessage(player, "Vous ne pouvez ouvrir votre propre inventaire !", ColorUtils.PREFIX,true);
            return;
        }

        player.openInventory(target.getInventory());
    }
}
