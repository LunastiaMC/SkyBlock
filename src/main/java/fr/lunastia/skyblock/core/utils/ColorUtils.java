package fr.lunastia.skyblock.core.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    public static final String HELP = "&#d0d0d0&l[&#d9d9d9&lA&#e2e2e2&li&#ececec&ld&#f5f5f5&le&#fefefe&l]§r§7";
    public static final String PREFIX = "&#229dc3&l[&#2796c6&lS&#2b8ec9&lk&#3087cc&ly&#357fcf&lB&#3978d1&ll&#3e70d4&lo&#4369d7&lc&#4761da&lk&#4c5add&l]§r§7";
    public static final String DISCORD = "&#5865f2&l[&#586af2&lD&#5770f2&li&#5775f2&ls&#567af2&lc&#567ff2&lo&#5585f2&lr&#558af2&ld&#548ff2&l]§r§7";
    public static final String BANK = "&#fbf800&l[&#f6f004&lB&#f1e808&la&#ece00c&ln&#e7d70f&lq&#e2cf13&lu&#ddc717&le&#d8bf1b&l]§r§7";
    public static final String HAT = "&#fb5cf1&l[&#f058e6&lC&#e654da&lh&#db50cf&la&#d04cc3&lp&#db4dbf&le&#e74fbc&la&#f250b8&lu&#fd51b4&l]§r§7";
    public static final String TRASH = "&#868484&l[&#888787&lP&#8b8a8a&lo&#8d8d8c&lu&#8f908f&lb&#929492&le&#949795&ll&#969a97&ll&#999d9a&le&#9ba09d&l]§r§7";
    public static final String REPAIR = "&#8f9873&l[&#919972&lE&#929a72&ln&#949b71&lc&#959c70&ll&#979d70&lu&#999e6f&lm&#9a9f6e&le &#9ca06e&la&#9da16d&lm&#9fa26d&lé&#a0a36c&ll&#a2a46b&li&#a4a56b&lo&#a5a66a&lr&#a7a769&lé&#a8a869&le&#aaa968&l]";

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

    public static void sendMessage(Player player, String message, String prefix) {
        player.sendMessage(colorize(prefix + "§7 " + message));
    }

    public static void sendMessage(Player player, String message, String prefix, Boolean isError) {
        player.sendMessage(colorize(prefix + (isError ? "§c " : "§7 ") + message));
    }
}