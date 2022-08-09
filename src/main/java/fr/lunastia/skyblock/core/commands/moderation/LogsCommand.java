package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.entity.Player;

@Command("logs")
@Permission("skyblock.logs.command")
public class LogsCommand {
    @Default
    public static void logs(Player player, @AStringArgument String target) {

    }
}
