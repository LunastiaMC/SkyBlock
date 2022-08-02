package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
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
            inventory.setItem(Integer.parseInt(string), ItemUtils.customizedItem(new ItemStack(Material.matchMaterial(section.getString(string + ".id"))), section.getString(string + ".displayName"), new ArrayList<>()));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {

    }
}
