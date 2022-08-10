package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command("announce")
@Permission("skyblock.announce.command")
public class AnnounceCommand {
    @Default
    public static void announce(Player player, @AMultiLiteralArgument({"green", "red", "blue", "aqua", "orange", "yellow", "white"}) String type, @AGreedyStringArgument String message) {
        /*
        String prefix = Colors.colorize(switch (type) {
            case "green" ->
                    "&#18ff7b&l[&#1dfc7b&lA&#22f87c&lN&#27f57c&lN&#2df27c&lO&#32ee7c&lN&#37eb7d&lC&#3ce77d&lE&#41e47d&l] §r§7";
            case "red" ->
                    "&#ff3c3c&l[&#fc3d3d&lA&#f83d3d&lN&#f53e3e&lN&#f23f3f&lO&#ee3f3f&lN&#eb4040&lC&#e74040&lE&#e44141&l] §r§7";
            case "blue" ->
                    "&#314cff&l[&#334ffc&lA&#3551f8&lN&#3754f5&lN&#3956f2&lO&#3b59ee&lN&#3d5beb&lC&#3f5ee7&lE&#4160e4&l] §r§7";
            case "aqua" ->
                    "&#31d4ff&l[&#33ccfc&lA&#35c3f8&lN&#37bbf5&lN&#39b3f2&lO&#3baaee&lN&#3da2eb&lC&#3f99e7&lE&#4191e4&l] §r§7";
            case "orange" ->
                    "&#ff5739&l[&#fc5839&lA&#f85939&lN&#f55a39&lN&#f25b3a&lO&#ee5b3a&lN&#eb5c3a&lC&#e75d3a&lE&#e45e3a&l] §r§7";
            case "yellow" ->
                    "&#e6ff1b&l[&#e4fc20&lA&#e1f825&lN&#dff52a&lN&#ddf22f&lO&#daee34&lN&#d8eb39&lC&#d5e73e&lE&#d3e443&l] §r§7";
            case "white" ->
                    "&#ffecec&l[&#fce9e9&lA&#f8e6e6&lN&#f5e3e3&lN&#f2e1e1&lO&#eedede&lN&#ebdbdb&lC&#e7d8d8&lE&#e4d5d5&l] §r§7";
            default -> Colors.PREFIX;
        });

        String coloredText = Colors.colorize(message);
        */

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(" ");
            // p.sendMessage(prefix + coloredText);
            p.sendMessage(" ");
        });
    }
}
