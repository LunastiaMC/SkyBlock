package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("feed")
@Permission("skyblock.feed.command")
public class FeedCommand {
    @Default
    public static void feed(Player player)
    {
        player.setFoodLevel(20);
        ColorUtil.sendMessage(player, "§aVous avez été nourri !", ColorUtil.PREFIX);
    }
}
