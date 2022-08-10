package fr.lunastia.skyblock.core.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public interface GUIBuilder {
    String getName();

    int getSize();

    void getContents(Player player, Inventory inventory);

    void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException;

    void onClose(Player player, Inventory inventory);

    void onOpen(Player player, Inventory inventory);

    boolean clickCancelled();
}