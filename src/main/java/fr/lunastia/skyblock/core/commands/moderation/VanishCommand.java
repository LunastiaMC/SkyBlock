package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeModeration;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

@Command("vanish")
@Permission("skyblock.vanish.command")
public class VanishCommand {
    @Default
    public static void vanish(Player player) {
        Session session = Manager.getSessionManager().getSession(player);

        LogTypeModeration log = new LogTypeModeration(session.isVanished() ? EnumLogs.PLAYER_UNVANISHED : EnumLogs.PLAYER_VANISHED, player, null, null);
        log.setStartAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        log.send();

        session.setVanished(!session.isVanished(), true);
    }

    @Subcommand("list")
    @Permission("skyblock.vanish.list.command")
    public static void list(Player player) {
        StringBuilder list = new StringBuilder();
        list.append("Liste des joueurs cachés :\n");

        Manager.getSessionManager().getSessions().forEach((uuid, session) -> {
            if (session.isVanished()) {
                list.append("§7- ").append(session.getPlayer().getName()).append("\n");
            }
        });
        ColorUtils.sendMessage(player, list.toString(), Colors.PREFIX);
    }
}
