package fr.lunastia.skyblock.core.commands.moderation;


@Command("ban")
@Permission("skyblock.ban.command")
public class BanCommand {
    public static void ban(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({
            "hours",
            "days",
            "weeks",
            "months",
            "years"
    }) String time, @AIntegerArgument(min = 1) int duration, @AGreedyStringArgument String reason
    ) {
    }
}