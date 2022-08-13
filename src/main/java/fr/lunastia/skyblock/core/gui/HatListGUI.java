package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.session.Hats;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;

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
        // Foreach all hats in Hats.java
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
            i++;

            // Get item
            ItemStack item = new ItemStack(Material.valueOf(hat.getIdentifier()));
            inventory.setItem(i, ItemUtils.customizedItem(item, "§d" + hat.getDisplayName(), lore));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(Core.getInstance().getHatConfig());
        ConfigurationSection section = config.getConfigurationSection("hats");

        assert section != null;
        if (section.contains(String.valueOf(slot))) {
            if (player.hasPermission(section.getString(slot + ".permission"))) {
                if (player.getInventory().getHelmet() != null) {
                    if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Chapeau")) {
                        String displayName = section.getString(slot + ".displayName");
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName() == section.getString(slot + ".displayName")) {
                            ColorUtils.sendMessage(player, "Vous avez deja le chapeau §d" + displayName, Colors.HAT);
                            return;
                        }
                        ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau §d" + displayName, Colors.HAT);
                        ItemStack item = ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(String.valueOf(slot) + ".id"))), section.getString(String.valueOf(slot) + ".displayName"), new ArrayList<>());
                        player.getInventory().setHelmet(item);

                        LogTypeCommon log = new LogTypeCommon(EnumLogs.PLAYER_CHANGE_HAT, player, displayName);
                        log.setStartDate(new Date().toString());
                        log.send();
                        return;
                    }
                    ColorUtils.sendMessage(player, "Veuillez enlevez votre casque avant de mettre un chapeau !", Colors.HAT);
                    return;
                }

                String displayName = section.getString(slot + ".displayName");
                ItemStack item = ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(String.valueOf(slot) + ".id"))), section.getString(String.valueOf(slot) + ".displayName"), new ArrayList<>());

                ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau §d" + displayName, Colors.HAT);
                player.getInventory().setHelmet(item);
            } else {
                ColorUtils.sendMessage(player, "Vous n'avez pas encore débloqué ce chapeau !", Colors.HAT, true);
            }
        }
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
}
