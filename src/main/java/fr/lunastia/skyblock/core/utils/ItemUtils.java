package fr.lunastia.skyblock.core.utils;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class ItemUtils {

    public static ItemStack customizedItem(ItemStack item, String name, ArrayList<String> lines) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RESET + ColorUtils.colorize(ChatColor.translateAlternateColorCodes('&', name)));
        meta.setLore(lines);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack customizedItem(ItemStack item, String name, ArrayList<String> lines, String nbtKey, UUID nbtValue) {
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RESET + ColorUtils.colorize(ChatColor.translateAlternateColorCodes('&', name)));
        meta.setLore(lines);

        NamespacedKey key = new NamespacedKey(Core.getInstance(), nbtKey);
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, nbtValue.toString());

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

    /**
     * @deprecated Use {@link #customizedItem(ItemStack, String, ArrayList)} instead
     */
    public static void setLore(ItemStack itemStack, ArrayList<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }
}
