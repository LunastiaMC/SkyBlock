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
            case "green" -> "&#2db725&l[&#30b62c&lA&#32b633&lN&#35b53a&lN&#37b441&lO&#3ab348&lN&#3cb34f&lC&#3fb256&lE&#41b15d&l]";
            default -> ColorUtil.PREFIX;
        });

        String coloredText = ColorUtil.colorize(message);

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(prefix + coloredText);
        });
    }
}
