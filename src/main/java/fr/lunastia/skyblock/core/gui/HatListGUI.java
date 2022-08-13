package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

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
            ArrayList<String> lore = new ArrayList<>();
                lore.add("§c§l✘ §r§cVous n'avez pas débloqué ce chapeau");
                // TODO: lore.add("§cDébloquer ce chapeau avec le grade ...");
            }
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
}
