package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeModeration;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

@Command("freeze")
@Permission("skyblock.freeze.command")
public class FreezeCommand {
    @Default
    public static void freeze(Player player, @APlayerArgument Player target) {
        if (player.getName().equals(target.getName())) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas vous immobiliser vous-même.", Colors.PREFIX, true);
            return;
        }

        Session session = Manager.getSessionManager().getSession(target);
        if (session == null) {
            ColorUtils.sendMessage(player, "Ce joueur n'est pas dans un Skyblock.", Colors.PREFIX, true);
            return;
        }

        LogTypeModeration log = new LogTypeModeration(!session.isFreezed() ? EnumLogs.PLAYER_UNFREEZED : EnumLogs.PLAYER_FREEZED, target, player, null);
        log.setStartAt(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        log.send();

        session.setFreezed(!session.isFreezed(), true);
    }

    @Subcommand("list")
    @Permission("skyblock.freeze.list.command")
    public static void list(Player player) {
        StringBuilder list = new StringBuilder();
        list.append("Liste des joueurs immobilisée :\n");

        Manager.getSessionManager().getSessions().forEach((uuid, session) -> {
            if (session.isFreezed()) {
                list.append("§7- ").append(session.getPlayer().getName()).append("\n");
            }
        });
        ColorUtils.sendMessage(player, list.toString(), Colors.PREFIX);
    }
}
