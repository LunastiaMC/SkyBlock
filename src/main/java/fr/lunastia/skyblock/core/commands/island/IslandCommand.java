package fr.lunastia.skyblock.core.commands.island;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.gui.island.IslandCreationGUI;
import fr.lunastia.skyblock.core.manager.GUIManager;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("island")
public class IslandCommand {
    @Default
    public static void island(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
    }

    @Subcommand("create")
    public static void create(Player player) throws SQLException {
        Session session = Manager.getSessionManager().getSession(player);
        Manager.getGUIManager().open(player, IslandCreationGUI.class);
    }

    @Subcommand("disband")
    public static void disband(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
    }
}
