package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Command("ban")
@Permission("skyblock.ban.command")
public class BanCommand {
    @Default
    public static void ban(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({"hours", "days", "weeks", "months", "years"}) String type, @AIntegerArgument(min = 1) int duration, @AGreedyStringArgument String reason) {
        DateFormat format = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm:ss a");
        ColorUtils.sendMessage(player, format.format(getDate(type, duration,false)), Colors.PREFIX);
    }

    @Default
    public static void ban(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({"hours", "days", "weeks", "months", "years"}) String type, @AIntegerArgument(min = 1) int duration) {
        ColorUtils.sendMessage(player, getDate(type, duration,false), Colors.PREFIX);
    }

    public static String translateDuration(String time) {
        return switch (time) {
            case "hours" -> "heures";
            case "days" -> "jours";
            case "weeks" -> "semaines";
            case "months" -> "mois";
            case "years" -> "années";
            default -> "";
        };
    }

    public static String getDate(String type, int number, boolean full) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE d MMM y");

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        if (Objects.equals(type, "years")) c.add(Calendar.YEAR, number);
        if (Objects.equals(type, "months")) c.add(Calendar.MONTH, number);
        if (Objects.equals(type, "weeks")) c.add(Calendar.WEEK_OF_YEAR, number);
        if (Objects.equals(type, "days")) c.add(Calendar.DAY_OF_YEAR, number);
        if (Objects.equals(type, "hours")) c.add(Calendar.HOUR, number);

        if (full) {
            Date currentDatePlusOne = c.getTime();
            return currentDatePlusOne.toString();
        }

        Date currentDatePlusOne = c.getTime();
        String dateLongStr = dateFormat.format(currentDatePlusOne);
        String[] words = dateLongStr.split(" ");
        words[0] = words[0].substring(0, 1).toUpperCase() + words[0].substring(1);
        words[2] = words[2].substring(0, 1).toUpperCase() + words[2].substring(1);
        return String.join(" ", words);
    }
}