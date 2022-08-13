package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import fr.lunastia.skyblock.core.gui.LogsGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("logs")
@Permission("skyblock.logs.command")
public class LogsCommand {
    @Default
    public static void logs(Player player) {
        try {
            Manager.getGUIManager().open(player, LogsGUI.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("find")
    public static void find(Player player, @AStringArgument String target) {
        System.out.println(target);
        try {
            Manager.getGUIManager().open(player, LogsGUI.class, target);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
