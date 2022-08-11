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
import java.time.LocalDateTime;
import java.util.Date;

@Command("ban")
@Permission("skyblock.ban.command")
public class BanCommand {
    @Default
    public static void ban(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({
            "hours",
            "days",
            "weeks",
            "months",
            "years"
    }) String time, @AIntegerArgument(min = 1) int duration, @AGreedyStringArgument String reason
    ) {
        Date dt = new Date();
        Date tomorrow = new Date(dt.getTime() + durationToFormat(time, duration));
        DateFormat format = new SimpleDateFormat("EEEE yyyy-MM-dd HH:mm:ss a");
        ColorUtils.sendMessage(player, format.format(tomorrow), Colors.PREFIX);
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

    public static Integer durationToFormat(String type, int duration) {
        return switch (type) {
            case "hours" -> duration * 60 * 60;
            case "days" -> duration * 60 * 60 * 24;
            case "weeks" -> duration * 60 * 60 * 24 * 7;
            case "months" -> duration * 60 * 60 * 24 * 30;
            case "years" -> duration * 60 * 60 * 24 * 365;
            default -> 0;
        };
    }
}