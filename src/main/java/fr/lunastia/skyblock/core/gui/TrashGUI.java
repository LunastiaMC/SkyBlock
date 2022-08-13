package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TrashGUI implements GUI {
    @Override
    public String name() {
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

    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        Integer count = ItemUtils.countInventory(inventory);
        if (count > 0) {
            ColorUtils.sendMessage(player, "Vous venez de jeter un total de §f" + count + " items §7à la poubelle", Colors.TRASH);
        }
        return;
    }

    @Override
    public void onOpen(Player player, Inventory inventory) {
        return;
    }

    @Override
    public boolean clickCancelled() {
        return false;
    }

    @Override
    public InventoryType getInventoryType() {
        return null;
    }

    @Override
    public void setArgument(String argument) {
        return;
    }
}
