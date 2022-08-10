package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import fr.lunastia.skyblock.core.gui.RepairGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.Repairable;

@Command("repair")
public class RepairCommand {
    @Default
    public static void repair(Player player) {
        if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
            ColorUtils.sendMessage(player, "Vous n'avez pas d'objet dans votre main !", Colors.REPAIR, true);
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (!(itemStack.getItemMeta() instanceof Damageable) || !(itemStack.getItemMeta() instanceof Repairable) || itemStack.getType().isBlock()) {
            ColorUtils.sendMessage(player, "Cet item ne peut pas être réparé", Colors.REPAIR, true);
            return;
        }

        if (player.getInventory().getItemInMainHand().getDurability() == 0) {
            ColorUtils.sendMessage(player, "Votre objet est déjà impeccable !", Colors.REPAIR, true);
            return;
        }

        Manager.getGUIManager().open(player, RepairGUI.class);
    }
}
