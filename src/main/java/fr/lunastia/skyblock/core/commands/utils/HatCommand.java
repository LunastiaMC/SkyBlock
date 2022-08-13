package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.gui.HatListGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeCommon;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Material;
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
        if (player.getItemInHand().getType() != Material.AIR) {
            if (player.getInventory().getHelmet() == null) {
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                ColorUtils.sendMessage(player, "Vous venez de mettre l'item §d" + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " §7en tant que chapeau !", Colors.HAT);
            } else {
                ColorUtils.sendMessage(player, "Vous avez déjà un chapeau !", Colors.HAT);
            }
        } else {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en ayant la main vide !", Colors.HAT);
        }
    }

    @Subcommand("remove")
    public static void hatRemove(Player player) {
        if (player.getInventory().getHelmet() != null) {
            if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Chapeau")) {
                player.getInventory().setHelmet(null);
                ColorUtils.sendMessage(player, "Vous venez de retirer votre chapeau !", Colors.HAT);
                LogTypeCommon log = new LogTypeCommon(EnumLogs.PLAYER_CHANGE_HAT_TO_AIR, player);
                log.send();
            } else {
                ColorUtils.sendMessage(player, "Vous devez avoir un chapeau pour executer cette commande !", Colors.HAT);
            }
        } else {
            ColorUtils.sendMessage(player, "Vous n'avez pas d'item dans votre case \"§dCasque§7\" !", Colors.HAT);
        }
    }
}
