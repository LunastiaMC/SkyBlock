package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command("announce")
@Permission("skyblock.announce.command")
public class AnnounceCommand {
    @Default
    public static void announce(Player player, @AMultiLiteralArgument({"good", "bad", "help"}) String type, @AGreedyStringArgument String message) {
        String prefix = ColorUtil.colorize(switch (type) {
            case "good" -> ColorUtil.ANNOUNCE_TYPE_GOOD;
            case "bad" -> ColorUtil.ANNOUNCE_TYPE_BAD;
            default -> ColorUtil.PREFIX;
        });

        String coloredText = ColorUtil.colorize(message);

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(prefix + coloredText);
        });
    }
}
