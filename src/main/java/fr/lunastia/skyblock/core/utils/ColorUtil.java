package fr.lunastia.skyblock.core.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtil {

    public static final String HELP = "&#d0d0d0&l[&#d9d9d9&lA&#e2e2e2&li&#ececec&ld&#f5f5f5&le&#fefefe&l]§r§7";
    public static final String PREFIX = "&#229dc3&l[&#2796c6&lS&#2b8ec9&lk&#3087cc&ly&#357fcf&lB&#3978d1&ll&#3e70d4&lo&#4369d7&lc&#4761da&lk&#4c5add&l]§r§7";
    public static final String DISCORD = "&#5865f2&l[&#586af2&lD&#5770f2&li&#5775f2&ls&#567af2&lc&#567ff2&lo&#5585f2&lr&#558af2&ld&#548ff2&l]§r§7";
    public static final String ERREUR = "&#f20000&l[&#f21807&lE&#f2310e&lr&#f24915&lr&#f2611d&le&#f27924&lu&#f2922b&lr&#f2aa32&l]§r§7";
    public static String colorize(String message) {
        message = matchHexaReplace("&#", message);
        message = matchHexaReplace("#", message);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(List<String> messages) {
        List<String> color_messages = new ArrayList<>();
        for (String message : messages)
            color_messages.add(ColorUtil.colorize(message));
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

    public static void sendMessage(Player player, String message, String prefix) {
        player.sendMessage(colorize(prefix + "§7 " + message));
    }

    public static void sendMessage(Player player, String message, String prefix, Boolean isError) {
        player.sendMessage(colorize(prefix + (isError ? "§c " : "§7 ") + message));
    }

    public static void sendMessageComponent(Player player, TextComponent message, String prefix) {
        player.sendMessage(colorize(prefix + "§7 " + message));
    }
}