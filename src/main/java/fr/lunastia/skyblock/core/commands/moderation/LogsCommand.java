package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import fr.lunastia.skyblock.core.gui.LogsGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeLogs;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;

@Command("logs")
@Permission("skyblock.logs.command")
public class LogsCommand {
    @Default
    public static void logs(Player player) {
        try {
            HashMap<Integer, String> args = new HashMap<>();
            args.put(0, "null");
            args.put(1, "0");
            args.put(2, "false");
            System.out.println(args);
            Manager.getGUIManager().open(player, LogsGUI.class, args);

            LogTypeLogs log = new LogTypeLogs(EnumLogs.LOGS_OPEN, player, null);
            log.send();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("find")
    public static void find(Player player, @AStringArgument String target) {
        try {
            HashMap<Integer, String> args = new HashMap<>();
            args.put(0, target);
            args.put(1, "0");
            args.put(2, "false");
            Manager.getGUIManager().open(player, LogsGUI.class, args);

            LogTypeLogs log = new LogTypeLogs(EnumLogs.LOGS_OPEN, player, target);
            log.send();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("archiveds")
    public static void archiveds(Player player) {
        try {
            HashMap<Integer, String> args = new HashMap<>();
            args.put(0, "null");
            args.put(1, "0");
            args.put(2, "true");
            Manager.getGUIManager().open(player, LogsGUI.class, args);

            LogTypeLogs log = new LogTypeLogs(EnumLogs.LOGS_OPEN, player, null);
            log.send();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
