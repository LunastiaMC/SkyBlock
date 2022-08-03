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

import java.util.Objects;

@Command("vanish")
@Permission("skyblock.vanish.command")
public class VanishCommand {
    @Default
    public static void vanish(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
        if (Manager.getSessionManager().isVanished(session)) {
            Manager.getSessionManager().setVanish(session,false);
            ColorUtils.sendMessage(player, "Vous venez de vous révéler !", ColorUtils.PREFIX);
        } else {
            Manager.getSessionManager().setVanish(session,true);
            ColorUtils.sendMessage(player, "Vous venez de vous cacher !", ColorUtils.PREFIX);
        }
    }

    @Subcommand("list")
    @Permission("skyblock.vanish.list.command")
    public static void list(Player player) {
        StringBuilder list = new StringBuilder();
        list.append("§fListe des joueurs cachés :\n");
        for (Session session : Manager.getSessionManager().getVanished()) {
            if (Manager.getSessionManager().isVanished(session)) {
                list.append("-" + session.getPlayer().getName()).append("\n");
            }
        }
        ColorUtils.sendMessage(player, list.toString(), ColorUtils.PREFIX);
    }
}
