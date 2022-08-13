package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import fr.lunastia.skyblock.core.utils.repair.RepairUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class RepairGUI implements GUI {

    private HashMap<Integer, Integer> repairCost;

    @Override
    public String name() {
        return "Réparation";
    }

    @Override
    public int getSize() {
        return 45;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {
        repairCost = new HashMap<>();

        for (int i = 0; i < inventory.getSize(); i++) {
            if (i == 22) {
                ItemStack itemStack = player.getInventory().getItemInMainHand().clone();
                ItemMeta itemMeta = itemStack.getItemMeta();

                if (itemMeta instanceof Damageable) {
                    itemStack.setItemMeta(itemMeta);

                    ArrayList<String> lore = new ArrayList<>();
                    repairCost.put(i, Manager.getRepairUtils().getPriceByItem(itemStack, i));

                    if (itemStack.getEnchantments().size() >= 1) {
                        lore.add(" ");
                    }

                    lore.add("§7Coût de réparation: §e" + RepairUtils.defaultCost);
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
        if (slot == 22) {
            if (itemStack.getType() == Material.BARRIER) {
                player.closeInventory();
                return;
            }

            Session session = Manager.getSessionManager().getSession(player);
            if (session.getMoney() < repairCost.get(slot)) {
                ColorUtils.sendMessage(player, "Vous n'avez pas assez de pièces pour réparer cet objet.", Colors.REPAIR, true);
                return;
            }

            if (player.getInventory().getItemInMainHand().getDurability() == 0) {
                ColorUtils.sendMessage(player, "Votre objet est déjà impeccable !", Colors.REPAIR, true);
                return;
            }

            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = mainHand.getItemMeta();
            if (itemMeta instanceof Damageable) {
                session.reduceMoney(Long.valueOf(repairCost.get(slot)));
                ((Damageable) itemMeta).setDamage(0);

                player.closeInventory();
                mainHand.setItemMeta(itemMeta);
                ColorUtils.sendMessage(player, "Vous venez de payer §e" + repairCost.get(slot) + " pièces §7pour réparer votre objet.", Colors.REPAIR);
            } else {
                ColorUtils.sendMessage(player, "Vous ne pouvez pas réparer cet objet.", Colors.REPAIR, true);
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

    @Override
    public InventoryType getInventoryType() {
        return null;
    }

    @Override
    public void setArgument(String argument) {
        return;
    }
}
