package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.repair.RepairUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RepairGUI implements GUIBuilder {

    private final HashMap<Integer, Integer> repairTiers;
    private HashMap<Integer, Integer> repairCost;
    private HashMap<Integer, Boolean> items;

    public RepairGUI() {
        this.repairTiers = new HashMap<>();
        this.repairCost = new HashMap<>();
        this.items = new HashMap<>();

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

                    if (player.getInventory().getItemInMainHand().getDurability() == 0) {
                        items.put(i, true);
                    } else {
                        items.put(i, false);
                    }

                    if (items.get(i - 2)) {
                        ItemStack replaceBy = new ItemStack(Material.BARRIER);
                        ArrayList<String> lore = new ArrayList<>();
                        lore.add("§cL'amélioration précédente répare déjà cet item");
                        ItemUtils.setLore(replaceBy, lore);
                        inventory.setItem(i, replaceBy);
                        return;
                    }

                    ArrayList<String> lore = new ArrayList<>();
                    repairCost.put(i, Manager.getRepairUtils().getPriceByItem(itemStack, i));

                    if (itemStack.getEnchantments().size() >= 1) {
                        lore.add(" ");
                    }

                    lore.add("§7Coût de réparation: §e" + RepairUtils.defaultCost.get(i) + " pièces §7(" + repairTiers.get(i) + "%)");
                    lore.add("§7Coût additionnels:");
                    lore.add("§7- Matériau: §e+" + Manager.getRepairUtils().getPriceByMaterial(itemStack.getType()) + " pièces");
                    Manager.getRepairUtils().getPriceByEnchantment(itemStack).forEach((enchantment, priceByEnchantment) -> {
                        lore.add("§7- §7Enchantement (§b" + Manager.getRepairUtils().getEnchantmentName(enchantment.getName()) + "§7): §e+" + priceByEnchantment + " pièces");
                    });
                    lore.add(" ");
                    lore.add("§7§l➤ §r§7Prix final: §e" + repairCost.get(i) + " pièces");
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
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
        if (slot == 20 || slot == 22 || slot == 24) {
            if (itemStack.getType() == Material.BARRIER) {
                player.closeInventory();
                return;
            }

            Session session = Manager.getSessionManager().getSession(player);
            if (session.getMoney() < repairCost.get(slot)) {
                ColorUtils.sendMessage(player, "Vous n'avez pas assez de pièces pour réparer cet objet.", ColorUtils.REPAIR, true);
                return;
            }

            if (player.getInventory().getItemInMainHand().getDurability() == 0) {
                ColorUtils.sendMessage(player, "Votre objet est déjà impeccable !", ColorUtils.REPAIR, true);
                return;
            }

            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = mainHand.getItemMeta();
            if (itemMeta instanceof Damageable) {
                double P_20 = 0.20 * (itemStack.getType().getMaxDurability() - (itemStack.getType().getMaxDurability() - ((Damageable) itemStack.getItemMeta()).getDamage()));
                double P_50 = 0.50 * (itemStack.getType().getMaxDurability() - (itemStack.getType().getMaxDurability() - ((Damageable) itemStack.getItemMeta()).getDamage()));

                session.reduceMoney(Long.valueOf(repairCost.get(slot)));
                ((Damageable) itemMeta).setDamage(switch (slot) {
                    case 20 -> ((Damageable) itemMeta).getDamage() - (int) P_20;
                    case 22 -> ((Damageable) itemMeta).getDamage() - (int) P_50;
                    default -> 0;
                });

                player.closeInventory();
                mainHand.setItemMeta(itemMeta);
                ColorUtils.sendMessage(player, "Vous venez de payer §e" + repairCost.get(slot) + " pièces §7pour réparer votre objet.", ColorUtils.REPAIR);
            } else {
                ColorUtils.sendMessage(player, "Vous ne pouvez pas réparer cet objet.", ColorUtils.REPAIR, true);
            }
        }

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
