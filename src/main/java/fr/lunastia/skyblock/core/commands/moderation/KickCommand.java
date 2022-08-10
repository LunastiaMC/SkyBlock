package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.Log;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeModeration;
import org.bukkit.entity.Player;

@Command("kick")
@Permission("skyblock.kick.command")
public class KickCommand {
    @Default
    public static void kick(Player player, @APlayerArgument Player target, @AGreedyStringArgument String reason) {
        Log log = new LogTypeModeration(EnumLogs.PLAYER_KICKED, target, player, reason);
    }
}
