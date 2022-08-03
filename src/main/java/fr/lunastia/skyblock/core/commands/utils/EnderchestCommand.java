package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.*;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("enderchest")
@Alias("ec")
@Permission("skyblock.ec.command")
public class EnderChestCommand {
    @Default
    public static void enderchest(Player player) {
        player.openInventory(player.getEnderChest());
    }

    @Default
    @Permission("skyblock.ec.other.command")
    public static void see(Player player, @APlayerArgument Player target) {
        if (target.getName().equals(player.getName())) {
            ColorUtils.sendMessage(player, "Vous ne pouvez ouvrir votre propre inventaire !", ColorUtils.PREFIX,true);
            return;
        }

        player.openInventory(target.getEnderChest());
    }
}
