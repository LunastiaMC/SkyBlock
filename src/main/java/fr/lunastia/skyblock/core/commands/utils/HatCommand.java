package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.gui.HatListGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@Command("hat")
@Permission("skyblock.hat.command")
public class HatCommand {
    @Default
    public static void hat(Player player) {
        Manager.getGUIManager().open(player, HatListGUI.class);

    }

    @Subcommand("hand")
    @Permission("skyblock.hat.hand.command")
    public static void hand(Player player) {
        if (player.getItemInHand().getType() != Material.AIR) {
            if (player.getInventory().getHelmet() == null) {
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                ColorUtils.sendMessage(player, "Vous venez de mettre l'item §d" + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " §7en tant que chapeau !", ColorUtils.HAT);
            } else {
                ColorUtils.sendMessage(player, "Vous avez déjà un chapeau !", ColorUtils.HAT);
            }
        } else {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en ayant la main vide !", ColorUtils.HAT);
        }
    }

    @Subcommand("remove")
    public static void hatRemove(Player player) {
        if (player.getInventory().getHelmet() != null) {
            if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Chapeau")) {
                player.getInventory().setHelmet(null);
                ColorUtils.sendMessage(player, "Vous venez de retirer votre chapeau !", ColorUtils.HAT);
            } else {
                ColorUtils.sendMessage(player, "Vous devez avoir un chapeau pour executer cette commande !", ColorUtils.HAT);
            }
        } else {
            ColorUtils.sendMessage(player, "Vous n'avez pas d'item dans votre case \"§dCasque§7\" !", ColorUtils.HAT);
        }
    }
}
