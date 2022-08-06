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
    private HashMap<Integer, Integer> defaultCost;

    public RepairGUI() {
        this.repairTiers = new HashMap<>();
        this.repairCost = new HashMap<>();
        this.defaultCost = new HashMap<>();
        defaultCost.put(20, 500);
        defaultCost.put(22, 1000);
        defaultCost.put(24, 1500);

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

                    ((Damageable) itemMeta).setDamage(switch (i) {
                        case 20 -> ((Damageable) itemMeta).getDamage() - (int) P_20;
                        case 22 -> ((Damageable) itemMeta).getDamage() - (int) P_50;
                        default -> 0;
                    });

                    itemStack.setItemMeta(itemMeta);

                    int price = defaultCost.get(i);
                    price += ItemUtils.getPriceByMaterial(itemStack.getType());
                    // TODO: Enchantments

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("§7Coût de réparation: §e" + defaultCost.get(i) + " pièces §7");
                    lore.add("§7Coût supplémentaire: §e+" + ItemUtils.getPriceByMaterial(itemStack.getType()) + " pièces §7(Matériau)");
                    lore.add(" ");
                    lore.add("§7§l➤ §r§7Prix final: §e+" + price + " pièces");
                    inventory.setItem(i, ItemUtils.customizedItem(itemStack, lore));
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
        ColorUtils.sendMessage(player, "La réparation va vous coûter §e" + ItemUtils.getPriceByMaterial(itemStack.getType()) + " pièces", ColorUtils.REPAIR);
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
