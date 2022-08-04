package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;

import java.sql.SQLException;
import java.util.*;

public class RepairGUI implements GUIBuilder {

    private final HashMap<Integer, Integer> repairTiers;

    public RepairGUI() {
        this.repairTiers = new HashMap<>();
        repairTiers.put(20, 20);
        repairTiers.put(22, 50);
        repairTiers.put(24, 100);
    }

    @Override
    public String getName() {
        return "Réparation";
    }

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {
        ItemStack itemStack = player.getInventory().getItemInMainHand().clone();

        for (int i = 0; i < inventory.getSize(); i++) {
            Integer[] intArray = new Integer[]{20, 22, 24};
            List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));
            if (intList.contains(i)) {
                if (itemStack.getItemMeta() instanceof Repairable){
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("§7Réparer l'item avec §f" + repairTiers.get(i) + "% §7de durabilité supplémentaire");
                    assert itemStack instanceof Repairable;
                    lore.add("§7Coût: §e" + ((Repairable) itemStack.getItemMeta()).getRepairCost() + " pièces");

                    ItemStack newItem = ItemUtils.customizedItem(itemStack, lore);
                    inventory.setItem(i, newItem);
                }
            } else {
                if (i >= 10 && i <= 16) inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                else if (i >= 19 && i <= 25) inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                else if (i >= 28 && i <= 34) inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
                else inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }
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


}
