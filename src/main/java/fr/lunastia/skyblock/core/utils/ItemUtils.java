package fr.lunastia.skyblock.core.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemUtils {
    public static ItemStack customizedItem(ItemStack item, String name, ArrayList<String> lines) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RESET + ColorUtils.colorize(ChatColor.translateAlternateColorCodes('&', name)));
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }
}
