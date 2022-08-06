package fr.lunastia.skyblock.core.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class ItemUtils {
    public static ItemStack customizedItem(ItemStack item, String name, ArrayList<String> lines) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RESET + ColorUtils.colorize(ChatColor.translateAlternateColorCodes('&', name)));
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack customizedItem(ItemStack item, ArrayList<String> lines) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static int countInventory(Inventory inventory) {
        int count = 0;
        for (int i = 0; i < inventory.getSize(); i++) {
            if (inventory.getItem(i) != null) {
                count += Objects.requireNonNull(inventory.getItem(i)).getAmount();
            }
        }
        return count;
    }

    public static void getPriceByMaterial(Material material) {
        System.out.println(material.toString());
        System.out.println(material.name());
    public static int getPriceByMaterial(Material material) {
        if (material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS) {
            return 50;
    }
}
