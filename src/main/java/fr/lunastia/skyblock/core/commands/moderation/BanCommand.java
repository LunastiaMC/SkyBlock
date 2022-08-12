package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeModeration;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Command("ban")
@Permission("skyblock.ban.command")
public class BanCommand {
    @Default
    public static void ban(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({"days", "weeks", "months", "years"}) String type, @AIntegerArgument(min = 1) int duration, @AGreedyStringArgument String reason) {
        try {
            Manager.getModerationManager().addBan(target, getDate(type, duration, true), reason);
            ColorUtils.sendMessage(player, "Vous venez de bannir le joueur " + Colors.MOD_RED.color() + target.getName() + " §7pour " + Colors.MOD_RED.color() + duration + " " + translateDuration(type) + "§7, pour la raison: " + Colors.MOD_RED.color() + reason + "\n§7Il pourra se reconnecter le: " + Colors.MOD_RED.color() + getDate(type, duration, false), Colors.PREFIX);
            String[] message = new String[]{
                    ColorUtils.colorize(Colors.MOD_RED.color() + "§lVous êtes banni(e) du serveur\n\n"),
                    ColorUtils.colorize("§7§l➤§r§7 Pour la raison suivante: " + Colors.MOD_RED.color() + reason) + "\n",
                    ColorUtils.colorize("§7§l➤§r§7 Pendant " + Colors.MOD_RED.color() + duration + " " + translateDuration(type)) + "\n",
                    "\n",
                    ColorUtils.colorize("§r§7Si vous pensez qu'il y a une erreur") + "\n",
                    ColorUtils.colorize("§r§7vous pouvez ouvrir un ticket sur le discord") + "\n",
                    ColorUtils.colorize(Colors.DISCORD_COLOR.color() + "discord.gg/F9aQyQZxQr")
            };
            target.kickPlayer(String.join("", message));

            LogTypeModeration log = new LogTypeModeration(EnumLogs.PLAYER_BANNED, target, player, reason);
            log.setExpireAt(getDate(type, duration, true));
            log.send();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String translateDuration(String time) {
        return switch (time) {
            case "days" -> "jour(s)";
            case "weeks" -> "semaine(s)";
            case "months" -> "moi(s)";
            case "years" -> "année(s)";
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