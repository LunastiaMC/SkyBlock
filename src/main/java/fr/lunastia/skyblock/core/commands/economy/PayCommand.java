package fr.lunastia.skyblock.core.commands.economy;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.arguments.ALongArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import fr.lunastia.skyblock.core.utils.TextUtils;
import org.bukkit.entity.Player;

@Command("pay")
public class PayCommand {
    @Default
    public static void pay(Player player, @APlayerArgument Player target, @ALongArgument Long amount) {
        if (target.getName().equals(player.getName())) {
            ColorUtil.sendMessage(player, "Vous ne pouvez pas vous payer vous-même", ColorUtil.BANK);
            return;
        }

        Session targetSession = Manager.getSessionManager().getSession(target);
        if (targetSession == null) {
            return;
        }

        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        if (session.getMoney() > amount) {
            targetSession.addMoney(amount);
            session.reduceMoney(amount);
            ColorUtil.sendMessage(player, "Vous venez d'envoyer §e" + TextUtils.formatValue(amount) + " §7de §e*pièces §7à §e" + target.getName() + " §7, il vous reste, §e" + TextUtils.formatValue(session.getMoney()) + " pièces §7dans votre compte", ColorUtil.BANK);
            ColorUtil.sendMessage(target, "Vous venez de recevoir §e" + TextUtils.formatValue(amount) + " §7de §epièces §7de la part de §e" + player.getName(), ColorUtil.BANK);
        } else {
            ColorUtil.sendMessage(player, "Vous n'avez pas assez d'argent sur votre compte pour effectuer ce paiement.", ColorUtil.BANK);
        }
    }
}