package fr.lunastia.skyblock.core.commands.economy;

import dev.jorel.commandapi.annotations.*;
import dev.jorel.commandapi.annotations.arguments.ALongArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import fr.lunastia.skyblock.core.utils.TextUtils;
import org.bukkit.entity.Player;

@Command("money")
public class MoneyCommand {
    @Default
    public static void money(Player player) {
        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        ColorUtil.sendMessage(player, "Il y à un total de §e" + TextUtils.formatValue(session.getMoney()) + " pièce(s) §7sur votre compte", ColorUtil.BANK);
    }

    @Subcommand("balance")
    @Permission("skyblock.economy.balance")
    public static void balance(Player player, @APlayerArgument Player target) {
        Session targetSession = Manager.getSessionManager().getSession(target);
        if (targetSession == null) {
            return;
        }

        ColorUtil.sendMessage(player, "Le joueur §e" + target.getName() + " §7a un total de §e" + TextUtils.formatValue(targetSession.getMoney()) + " §7de §epièce(s)", ColorUtil.BANK);
    }

    @Subcommand("add")
    @Permission("skyblock.economy.add")
    public static void add(Player player, @APlayerArgument Player target, @ALongArgument(min = 1, max = 1000000000) Long amount) {
        Session session = Manager.getSessionManager().getSession(target);
        if (session == null) {
            return;
        }

        session.addMoney(amount);
        ColorUtil.sendMessage(player, "Vous venez d'ajouter un total de §e" + TextUtils.formatValue(amount) + " pièce(s) §7à " + target.getName(), ColorUtil.BANK);
        ColorUtil.sendMessage(target, "Vous venez de recevoir un total de §e" + TextUtils.formatValue(amount) + " pièce(s) §7de " + player.getName(), ColorUtil.BANK);
    }

    @Subcommand("del")
    @Permission("skyblock.economy.reduce")
    public static void reduce(Player player, @APlayerArgument Player target, @ALongArgument(min = 1, max = 1000000000) Long amount) {
        Session session = Manager.getSessionManager().getSession(target);
        if (session == null) {
            return;
        }

        session.reduceMoney(amount);
        ColorUtil.sendMessage(player, "Vous venez de retirer un total de §e" + TextUtils.formatValue(amount) + " pièce(s) §7à " + target.getName(), ColorUtil.BANK);
        ColorUtil.sendMessage(target, "Votre compte à été déduit de §e" + TextUtils.formatValue(amount) + " pièce(s) §7de " + player.getName(), ColorUtil.BANK);
    }

    @Subcommand("set")
    @Permission("skyblock.economy.set")
    public static void set(Player player, @APlayerArgument Player target, @ALongArgument(min = 1, max = 1000000000) Long amount) {
        Session session = Manager.getSessionManager().getSession(target);
        if (session == null) {
            return;
        }

        session.setMoney(amount);
        ColorUtil.sendMessage(player, "Vous venez de définir le compte de §e" + target.getName() + "§fà §e" + TextUtils.formatValue(amount) + " pièce(s)", ColorUtil.BANK);
    }
}
