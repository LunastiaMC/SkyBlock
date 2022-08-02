package fr.lunastia.skyblock.core.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class HatListGUI implements GUIBuilder{
    @Override
    public String getName() {
        return "Liste des chapeaux disponibles";
    }

    @Override
    public int getSize() {
        return 16;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {

    }
}
