package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class HatListGUI implements GUIBuilder {
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
        YamlConfiguration config = YamlConfiguration.loadConfiguration(Core.getInstance().getHatConfig());
        ConfigurationSection section = config.getConfigurationSection("hats");
        for (String string : section.getKeys(false)) {
            ArrayList<String> lore = new ArrayList<>();
            if (player.hasPermission(section.getString(string + ".permission"))) {
                lore.add("§a§l✔ §r§aVous avez débloqué ce chapeau");
            } else {
                lore.add("§c§l✘ §r§cVous n'avez pas débloqué ce chapeau");
                // TODO: lore.add("§cDébloquer ce chapeau avec le grade ...");
            }
            inventory.setItem(Integer.parseInt(string), ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(string + ".id"))), section.getString(string + ".displayName"), lore));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(Core.getInstance().getHatConfig());
        ConfigurationSection section = config.getConfigurationSection("hats");

        assert section != null;
        if (section.contains(String.valueOf(slot))) {
            if (player.hasPermission(section.getString(String.valueOf(slot) + ".permission"))) {
                if (player.getInventory().getHelmet() != null) {
                    if (player.getInventory().getHelmet().getItemMeta().getDisplayName().contains("Chapeau"))
                    {
                        String displayName = section.getString(slot + ".displayName");
                        if (player.getInventory().getHelmet().getItemMeta().getDisplayName() == section.getString(slot + ".displayName"))
                        {
                            ColorUtils.sendMessage(player, "Vous avez deja le chapeau §d" + displayName, ColorUtils.HAT);
                            return;
                        }
                        ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau §d" + displayName, ColorUtils.HAT);
                        ItemStack item = ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(String.valueOf(slot) + ".id"))), section.getString(String.valueOf(slot) + ".displayName"), new ArrayList<>());
                        player.getInventory().setHelmet(item);
                        return;
                    }
                    ColorUtils.sendMessage(player, "Veuillez enlevez votre casque avant de mettre un chapeau !", ColorUtils.HAT);
                    return;
                }

                String displayName = section.getString(slot + ".displayName");
                ItemStack item = ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(String.valueOf(slot) + ".id"))), section.getString(String.valueOf(slot) + ".displayName"), new ArrayList<>());

                ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau §d" + displayName, ColorUtils.HAT);
                player.getInventory().setHelmet(item);
            } else {
                ColorUtils.sendMessage(player, "Vous n'avez pas encore débloqué ce chapeau !", ColorUtils.HAT, true);
            }
        }
    }
}
