package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.gui.RepairGUI;
import fr.lunastia.skyblock.core.manager.GUIManager;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.Repairable;

@Command("repair")
public class RepairCommand {
    @Default
    public static void repair(Player player) {
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            ColorUtils.sendMessage(player, "Vous n'avez pas d'objet dans votre main !", ColorUtils.PREFIX, true);
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!(itemStack.getItemMeta() instanceof Damageable) || !(itemStack.getItemMeta() instanceof Repairable) || itemStack.getType().isBlock()) {
            ColorUtils.sendMessage(player, "Cet item ne peut pas être réparé", ColorUtils.PREFIX, true);
            return;
        }

        if (player.getInventory().getItemInMainHand().getDurability() == 0) {
            ColorUtils.sendMessage(player, "Votre objet est déjà impeccable !", ColorUtils.PREFIX, true);
            return;
        }

        Manager.getGUIManager().open(player, RepairGUI.class);
    }
}
