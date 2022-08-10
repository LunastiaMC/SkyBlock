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
        ColorUtils.sendMessage(player, "Vous venez de bannir &#ff3c3c" + target.getName() + "§7 pour une durée de &#ff3c3c" + duration + " " + translateDuration(time) + "§7 pour la raison: &#ff3c3c" + reason, Colors.MOD);
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
}