package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Command("hat")
@Permission("skyblock.hat.use")
public class HatCommand {
    @Default
    public static void hat(Player player)
    {
        if (player.getItemInHand().getType() != Material.AIR)
        {
            if (player.getInventory().getHelmet() != null)
            {
                ItemStack item = player.getInventory().getHelmet().clone();
                player.getInventory().addItem(item);
                player.getInventory().removeItem(player.getInventory().getHelmet());
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                ColorUtil.sendMessage(player, "Vous venez de mettre l'item " + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " en tant que chapeau !", ColorUtil.HAT);
            } else
            {
                player.getInventory().setHelmet(player.getInventory().getItemInMainHand());
                player.getInventory().removeItem(player.getInventory().getItemInMainHand());
                ColorUtil.sendMessage(player, "Vous venez de mettre l'item " + player.getInventory().getHelmet().getItemMeta().getDisplayName() + " en tant que chapeau !", ColorUtil.HAT);
            }
        } else
        {
            ColorUtil.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en ayant la main vide !", ColorUtil.HAT);
        }
    }
}
