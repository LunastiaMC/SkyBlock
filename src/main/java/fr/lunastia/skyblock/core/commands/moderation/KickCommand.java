package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import org.bukkit.entity.Player;

@Command("kick")
@Permission("skyblock.kick.command")
public class KickCommand {
    @Default
    public static void kick(Player player, Player target, @AGreedyStringArgument String reason) {
    }
}
