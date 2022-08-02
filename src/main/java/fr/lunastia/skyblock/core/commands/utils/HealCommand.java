package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("heal")
@Permission("skyblock.heal.command")

public class HealCommand {
    @Default
    public static void heal(Player player)
    {
        player.setHealth(20);
        ColorUtil.sendMessage(player, "Vous avez été soigné(e)", ColorUtil.PREFIX);
    }

    @Default
    public static void heal(Player player ,@APlayerArgument Player target)
    {
        target.setHealth(20);
        ColorUtil.sendMessage(target, "§aVous avez été soigné(e) par §2" + player.getName() + "§a !", ColorUtil.PREFIX);
        ColorUtil.sendMessage(player, "§aVous avez soigné(e) §2" + target.getName() + "§a !", ColorUtil.PREFIX);
    }
}
