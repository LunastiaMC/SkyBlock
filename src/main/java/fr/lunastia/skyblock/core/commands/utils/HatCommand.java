package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.gui.HatListGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("hat")
@Permission("skyblock.hat.command")
public class HatCommand {
    @Default
    public static void hat(Player player) {
        try {
            Manager.getGUIManager().open(player, HatListGUI.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Subcommand("hand")
    @Permission("skyblock.hat.hand.command")
    public static void hand(Player player) {
        // TODO: Refaire correctement la commande /hat hand
    }

    @Subcommand("remove")
    public static void hatRemove(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        if (session.hasHat()) {
            ColorUtils.sendMessage(player, "Vous venez de retirer le chapeau " + session.getHat().getDisplayName(), Colors.PREFIX);
            session.setHat(null);
        } else {
            ColorUtils.sendMessage(player, "Vous n'avez pas de chapeau", Colors.PREFIX);
        }
    }
}
