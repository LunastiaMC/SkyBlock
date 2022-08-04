package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.utils.ColorUtils;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TrashGUI implements GUIBuilder {
    @Override
    public String getName() {
        return "Poubelle";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {
        return;
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
        return;
    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        ColorUtils.sendMessage(player, "Vous venez de jeter un total de §f" + ItemUtils.countInventory(inventory) + " items §7à la poubelle", ColorUtils.TRASH);
        return;
    }

    @Override
    public void onOpen(Player player, Inventory inventory) {
        return;
    }
}
