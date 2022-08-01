package fr.lunastia.skyblock.core.commands.economy;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("money")
public class MoneyCommand {
    @Default
    public static void money(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        ColorUtil.sendMessage(player, "Il y à un total de §e" + session.getMoney() + " pièces §7sur votre compte", ColorUtil.BANK);
    }
}
