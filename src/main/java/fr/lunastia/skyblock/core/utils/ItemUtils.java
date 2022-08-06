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
        } else if (material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS || material == Material.IRON_SWORD || material == Material.IRON_PICKAXE || material == Material.IRON_AXE || material == Material.IRON_HOE || material == Material.IRON_SHOVEL) {
            return 150;
        } else if (material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS || material == Material.DIAMOND_SWORD || material == Material.DIAMOND_PICKAXE || material == Material.DIAMOND_AXE || material == Material.DIAMOND_HOE || material == Material.DIAMOND_SHOVEL) {
            return 250;
        } else if (material == Material.GOLDEN_HELMET || material == Material.GOLDEN_CHESTPLATE || material == Material.GOLDEN_LEGGINGS || material == Material.GOLDEN_BOOTS || material == Material.GOLDEN_SWORD || material == Material.GOLDEN_PICKAXE || material == Material.GOLDEN_AXE || material == Material.GOLDEN_HOE || material == Material.GOLDEN_SHOVEL) {
            return 100;
        } else if (material == Material.WOODEN_SWORD || material == Material.WOODEN_PICKAXE || material == Material.WOODEN_AXE || material == Material.WOODEN_HOE || material == Material.WOODEN_SHOVEL) {
            return 5;
        } else if (material == Material.TRIDENT) {
            return 500;
    }
}
