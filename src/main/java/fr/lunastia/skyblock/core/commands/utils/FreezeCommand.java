package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("freeze")
@Permission("skyblock.freeze.command")
public class FreezeCommand {
    @Default
    public static void freeze(Player player, @APlayerArgument Player target) {
        if (player.getName().equals(target.getName())) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas vous immobiliser vous-même.", ColorUtils.PREFIX, true);
            return;
        }

        Session session = Manager.getSessionManager().getSession(target);
        if (session == null) {
            ColorUtils.sendMessage(player, "Ce joueur n'est pas dans un Skyblock.", ColorUtils.PREFIX, true);
            return;
        }

        if (Manager.getSessionManager().isFreezed(session)) {
            Manager.getSessionManager().setFreeze(session, false);
            ColorUtils.sendMessage(target, "Vous vous pouvez à nouveau vous dégourdir les pieds.", ColorUtils.PREFIX);
        } else {
            Manager.getSessionManager().setFreeze(session, true);
            ColorUtils.sendMessage(target, "Vous venez d'être immobilisé, par §f" + player.getName(), ColorUtils.PREFIX);
        }
    }

    @Subcommand("list")
    @Permission("skyblock.freeze.list.command")
    public static void list(Player player) {
        StringBuilder list = new StringBuilder();
        list.append("Liste des joueurs immobilisée :\n");
        for (Session session : Manager.getSessionManager().getFreezed()) {
            if (Manager.getSessionManager().isFreezed(session)) {
                list.append("§7- ").append(session.getPlayer().getName()).append("\n");
            }
        }
        ColorUtils.sendMessage(player, list.toString(), ColorUtils.PREFIX);
    }
}
