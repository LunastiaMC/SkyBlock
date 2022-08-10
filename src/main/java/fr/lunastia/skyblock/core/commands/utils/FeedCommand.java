package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.entity.Player;

@Command("feed")
@Permission("skyblock.feed.command")
public class FeedCommand {
    @Default
    public static void feed(Player player) {
        player.setFoodLevel(20);
        ColorUtils.sendMessage(player, "Vous avez été nourri !", ColorUtils.PREFIX);
    }

    @Default
    public static void feed(Player player, @APlayerArgument Player target) {
        target.setFoodLevel(20);
        ColorUtils.sendMessage(target, "Vous avez été nourri par §f" + player.getName() + "§7 !", ColorUtils.PREFIX);
        ColorUtils.sendMessage(player, "Vous avez nourri §f" + target.getName() + "§7 !", ColorUtils.PREFIX);
    }
}
