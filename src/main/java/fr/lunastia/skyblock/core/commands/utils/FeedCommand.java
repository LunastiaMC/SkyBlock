package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("feed")
@Permission("skyblock.feed.command")
public class FeedCommand {
    @Default
    public static void feed(Player player)
    {
        player.setFoodLevel(20);
        ColorUtils.sendMessage(player, "§aVous avez été nourri !", ColorUtils.PREFIX);
    }
    @Default
    public static void feed(Player player ,@APlayerArgument Player target)
    {
        target.setFoodLevel(20);
        ColorUtils.sendMessage(target, "§aVous avez été nourri par §2" + player.getName() + "§a !", ColorUtils.PREFIX);
        ColorUtils.sendMessage(player, "§aVous avez nourri §2" + target.getName() + "§a !", ColorUtils.PREFIX);
    }
}
