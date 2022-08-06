package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.utils.ColorUtils;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RepairGUI implements GUIBuilder {

    private final HashMap<Integer, Integer> repairTiers;
    private HashMap<Integer, Integer> repairCost;

    public RepairGUI() {
        this.repairTiers = new HashMap<>();
        this.repairCost = new HashMap<>();
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
        for (int i = 0; i < inventory.getSize(); i++) {
            Integer[] intArray = new Integer[]{20, 22, 24};
            List<Integer> intList = new ArrayList<>(Arrays.asList(intArray));
            if (intList.contains(i)) {
                ItemStack itemStack = player.getInventory().getItemInMainHand().clone();
                ItemMeta itemMeta = itemStack.getItemMeta();

                if (itemMeta instanceof Damageable) {
                    double P_20 = 0.20 * (itemStack.getType().getMaxDurability() - (itemStack.getType().getMaxDurability() - ((Damageable) itemStack.getItemMeta()).getDamage()));
                    double P_50 = 0.50 * (itemStack.getType().getMaxDurability() - (itemStack.getType().getMaxDurability() - ((Damageable) itemStack.getItemMeta()).getDamage()));

                    Double p20 = P_20;
                    Double p50 = P_50;

                    ((Damageable) itemMeta).setDamage(switch (i) {
                        case 20 -> ((Damageable) itemMeta).getDamage() - p20.intValue();
                        case 22 -> ((Damageable) itemMeta).getDamage() - p50.intValue();
                        default -> 0;
                    });

                    itemStack.setItemMeta(itemMeta);
                    inventory.setItem(i, itemStack);
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
        ColorUtils.sendMessage(player, "La réparation va vous coûter §f" + ItemUtils.getPriceByMaterial(itemStack.getType()) + "§7 points d'expérience", ColorUtils.REPAIR);
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
