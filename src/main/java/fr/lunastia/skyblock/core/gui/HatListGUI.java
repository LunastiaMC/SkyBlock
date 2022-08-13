package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Hats;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class HatListGUI implements GUI {
    @Override
    public String getName() {
        return "Liste des chapeaux disponibles";
    }

    @Override
    public int getSize() {
        return 18;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {
        Hats[] hats = Hats.getHats();
        int i = 0;
        for (Hats hat : hats) {
            ArrayList<String> lore = new ArrayList<>();
            if (!player.hasPermission(hat.getPermission())) {
                lore.add("§c§l✘ §r§cVous n'avez pas débloqué ce chapeau");
                continue;
            } else {
                lore.add("§a§l✔ §r§aVous avez débloqué ce chapeau");
            }

            ItemStack itemStack = hat.getItemStack();

            NamespacedKey key = new NamespacedKey(Core.getInstance(), "hatId");
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, hat.getUniqueId().toString());
            itemStack.setItemMeta(itemMeta);

            inventory.setItem(i, itemStack);
            i++;
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
        Hats hat = Hats.getHat(itemStack);
        if (hat == null) {
            return;
        }

        if (!player.hasPermission(hat.getPermission())) {
            ColorUtils.sendMessage(player, "Vous n'avez pas débloqué ce chapeau", Colors.HAT, true);
            return;
        }

        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        if (session.getHat() != null) {
            ColorUtils.sendMessage(player, "Vous avez déjà un chapeau, veuillez le retirer pour en choisir un autre", Colors.HAT, true);
            return;
        }

        session.setHat(hat, hat.getItemStack());
        ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau §d" + hat.getDisplayName(), Colors.HAT);
        player.closeInventory();
    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        return;
    }

    @Override
    public void onOpen(Player player, Inventory inventory) {
        return;
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
        return;
    }
}
