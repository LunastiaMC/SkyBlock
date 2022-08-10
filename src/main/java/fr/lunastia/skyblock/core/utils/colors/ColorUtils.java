package fr.lunastia.skyblock.core.utils.colors;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    public static String colorize(String message) {
        message = matchHexaReplace("&#", message);
        message = matchHexaReplace("#", message);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(List<String> messages) {
        List<String> color_messages = new ArrayList<>();
        for (String message : messages)
            color_messages.add(ColorUtils.colorize(message));
        return color_messages;
    }

    private static String matchHexaReplace(String match, String message) {
        final Pattern hexPattern = Pattern.compile(match + "([A-Fa-f0-9]{6})");
        Matcher matcher = hexPattern.matcher(message);
        StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);
        while (matcher.find()) {
            matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
        }
        return matcher.appendTail(buffer).toString();
    }

    public static void sendMessage(Player player, String message, Colors prefix) {
        player.sendMessage(colorize(prefix + "§7 " + message));
    }

    public static void sendMessage(Player player, String message, Colors prefix, Boolean isError) {
        player.sendMessage(colorize(prefix + (isError ? "§c " : "§7 ") + message));
    }
}