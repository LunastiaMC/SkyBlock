package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("feed")
@Permission("skyblock.feed.command")
public class FeedCommand {
    @Default
    public static void feed(Player player) {
        player.setFoodLevel(20);
        ColorUtil.sendMessage(player, "§aVous avez été nourri !", ColorUtil.PREFIX);
    }

    @Default
    public static void feed(Player player, @APlayerArgument Player target) {
        target.setFoodLevel(20);
        ColorUtil.sendMessage(target, "Vous avez été nourri par §f" + player.getName() + "§7 !", ColorUtil.PREFIX);
        ColorUtil.sendMessage(player, "Vous avez nourri §f" + target.getName() + "§7 !", ColorUtil.PREFIX);
    }
}
