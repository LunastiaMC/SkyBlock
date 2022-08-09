package fr.lunastia.skyblock.core.commands.island;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import org.bukkit.entity.Player;

@Command("island")
public class IslandCommand {
    @Default
    public void island(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
    }

    @Subcommand("create")
    public static void create(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
    }

    @Subcommand("disband")
    public static void disband(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
    }
}
