package fr.lunastia.skyblock.core.commands.admin;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Rank;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

@Command("rank")
public class RankCommand {
    @Default
    public static void rank(Player player) {
        Session session = Manager.getSessionManager().getSession(player);

        if (session == null) {
            ColorUtils.sendMessage(player, "Vous n'êtes pas connecté(e) à SkyBlock !", Colors.PREFIX, true);
            return;
        }

        ColorUtils.sendMessage(player, "Vous avez le grade " + session.getRank().nametagName(), Colors.PREFIX);
    }

    @Subcommand("set")
    @Permission("skyblock.rank.set.command")
    public static void set(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({
            "player",
            "premium",
            "youtuber",
            "streamer",
            "moderator",
            "manager",
            "developper",
            "administrator"
    }) String rank) {
        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            ColorUtils.sendMessage(player, "Vous n'êtes pas connecté(e) à SkyBlock !", Colors.PREFIX, true);
            return;
        }

        Session targetSession = Manager.getSessionManager().getSession(target);
        if (targetSession == null) {
            ColorUtils.sendMessage(player, "Le joueur n'est pas connecté(e) !", Colors.PREFIX, true);
            return;
        }

        Rank rankClass = Manager.getRankManager().getRank(rank);
        if (rankClass == null) {
            ColorUtils.sendMessage(player, "Ce grade n'existe pas !", Colors.PREFIX, true);
            return;
        }

        if (rankClass.id() > session.getRank().id()) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas définir un grade supérieur à votre grade actuel !", Colors.PREFIX, true);
            return;
        }

        targetSession.setRank(rankClass);
        ColorUtils.sendMessage(player, "Le grade de §f" + target.getName() + " §7a été changé en " + rankClass.nametagName(), Colors.PREFIX);
        ColorUtils.sendMessage(target, "Votre grade a été changer, vous êtes dorénavant " + rankClass.nametagName() + " §r§7!", Colors.PREFIX);
    }
}
