package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class LogsGUI implements GUI {

    @Override
    public String getName() {
        return "Fichier journeaux";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void getContents(Player player, Inventory inventory) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("SELECT * FROM logs LIMIT 10");
        final ResultSet resultSet = statement.executeQuery();
        int slot = 0;
        while (resultSet.next()) {
            ItemStack item = ItemUtils.customizedItem(getItem(resultSet.getString("target_name")), new ArrayList<>());
            inventory.setItem(slot, item);
            slot++;
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {
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

    public ItemStack getItem(String player) {
        boolean isNewVersion = Arrays.stream(Material.values()).map(Material::name).toList().contains("PLAYER_HEAD");
        Material type = Material.matchMaterial(isNewVersion ? "PLAYER_HEAD" : "SKULL_ITEM");
        assert type != null;
        ItemStack item = new ItemStack(type);
        if (!isNewVersion) {
            item.setDurability((short) 3);
        }

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        assert meta != null;
        meta.setOwner(player);
        item.setItemMeta(meta);
        return item;
    }
}
