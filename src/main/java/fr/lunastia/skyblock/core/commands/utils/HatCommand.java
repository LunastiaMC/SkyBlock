package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.gui.HatListGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("hat")
@Permission("skyblock.hat.use")
public class HatCommand {
    @Default
    public static void hat(Player player) {
        Manager.getGUIManager().open(player, HatListGUI.class);
        //if (player.getItemInHand().getType() != Material.AIR)
        //{
        //if (player.getInventory().getHelmet() != null)
        //{
        //ItemStack item = player.getInventory().getHelmet().clone();
        //player.getInventory().addItem(item);
        //player.getInventory().removeItem(player.getInventory().getHelmet());
        //player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
        //player.getInventory().removeItem(player.getInventory().getItemInMainHand());
        //ColorUtil.sendMessage(player, "Vous venez de mettre l'item " + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " en tant que chapeau !", ColorUtil.HAT);
        //} else
        //{
        //player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
        //player.getInventory().removeItem(player.getInventory().getItemInMainHand());
        //ColorUtil.sendMessage(player, "Vous venez de mettre l'item " + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " en tant que chapeau !", ColorUtil.HAT);
        //}
        //} else
        //{
        //ColorUtil.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en ayant la main vide !", ColorUtil.HAT);
        //}
    }

    @Subcommand("remove")
    public static void hatRemove(Player player) {
        if (player.getInventory().getHelmet() != null) {
            if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Chapeau")) {
                player.getInventory().setHelmet(null);
                ColorUtils.sendMessage(player, "Vous venez de retirer votre chapeau !", ColorUtils.HAT);
            }else{
                ColorUtils.sendMessage(player, "Vous devez avoir un chapeau pour executer cette commande !", ColorUtils.HAT);
            }
        }
    }
}
