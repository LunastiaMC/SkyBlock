package fr.lunastia.skyblock.core.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class KitGUI implements GUIBuilder{
    @Override
    public String getName() {
        return "Liste des kits";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {

    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {

    }

    @Override
    public void onClose(Player player, Inventory inventory) {

    }

    @Override
    public void onOpen(Player player, Inventory inventory) {

    }

    @Override
    public boolean clickCancelled() {
        return false;
    }
}
