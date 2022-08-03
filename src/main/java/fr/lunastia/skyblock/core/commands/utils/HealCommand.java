package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("heal")
@Permission("skyblock.heal.command")

public class HealCommand {
    @Default
    public static void heal(Player player) {
        player.setHealth(20);
        ColorUtils.sendMessage(player, "Vous avez été soigné(e)", ColorUtils.PREFIX);
    }

    @Default
    public static void heal(Player player, @APlayerArgument Player target) {
        target.setHealth(20);
        ColorUtils.sendMessage(target, "Vous avez été soigné(e) par §f" + player.getName() + "§7 !", ColorUtils.PREFIX);
        ColorUtils.sendMessage(player, "Vous avez soigné(e) §f" + target.getName() + "§7 !", ColorUtils.PREFIX);
    }
}
