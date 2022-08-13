package fr.lunastia.skyblock.core.commands.economy;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.arguments.ALongArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeEconomy;
import fr.lunastia.skyblock.core.utils.TextUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

@Command("pay")
public class PayCommand {
    @Default
    public static void pay(Player player, @APlayerArgument Player target, @ALongArgument Long amount) {
        if (target.getName().equals(player.getName())) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas vous payer vous-même", Colors.BANK);
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
            LogTypeEconomy log = new LogTypeEconomy(EnumLogs.PLAYER_MONEY_UPDATED, player, session.getMoney().intValue(), session.getMoney().intValue() - amount.intValue(), amount.intValue());
            log.setMoneyType(EnumLogs.MONEY_PAY);
            log.send();
            targetSession.addMoney(amount);
            ColorUtils.sendMessage(player, "Vous venez d'envoyer §e" + TextUtils.formatValue(amount) + " §7de §epièces §7à §e" + target.getName() + " §7, il vous reste, §e" + TextUtils.formatValue(session.getMoney()) + " §7de §epièces §7dans votre compte", Colors.BANK);

            LogTypeEconomy log1 = new LogTypeEconomy(EnumLogs.PLAYER_MONEY_UPDATED, targetSession.getPlayer(), targetSession.getMoney().intValue(), targetSession.getMoney().intValue() + amount.intValue(), amount.intValue());
            log1.setMoneyType(EnumLogs.MONEY_RECEIVE);
            log1.send();
            session.reduceMoney(amount);
            ColorUtils.sendMessage(target, "Vous venez de recevoir §e" + TextUtils.formatValue(amount) + " §7de §epièces §7de la part de §e" + player.getName(), Colors.BANK);
        } else {
            ColorUtils.sendMessage(player, "Vous n'avez pas assez d'argent sur votre compte pour effectuer ce paiement.", Colors.BANK);
        }
    }
}
