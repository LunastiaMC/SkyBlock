package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.entity.Player;

@Command("enderchest")
@Alias("ec")
@Permission("skyblock.ec.command")
public class EnderchestCommand {
    @Default
    public static void enderchest(Player player) {
        player.openInventory(player.getEnderChest());
    }
}