package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("vanish")
@Permission("skyblock.vanish.command")
public class VanishCommand {
    @Default
    public static void vanish(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
        session.setVanished(!session.isVanished());
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
        ColorUtils.sendMessage(player, list.toString(), ColorUtils.PREFIX);
    }
}
