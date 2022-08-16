package fr.lunastia.skyblock.core.gui.island;

import fr.lunastia.skyblock.core.gui.GUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class IslandCreationGUI implements GUI {
    private HashMap<Integer, String> arguments;

    @Override
    public String getName() {
        return "Création d'une île";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void getContents(Player player, Inventory inventory) throws SQLException {
        inventory.setItem(2, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("53343"),"§aNaturel"));
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
        return true;
    }

    @Override
    public InventoryType getInventoryType() {
        return null;
    }

    @Override
    public void setArgument(HashMap<Integer, String> argument) {
        this.arguments = argument;
    }
}
