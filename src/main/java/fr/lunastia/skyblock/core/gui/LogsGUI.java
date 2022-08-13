package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
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
import java.util.HashMap;

public class LogsGUI implements GUI {
    private String argument;

    public LogsGUI() {

    }

    public LogsGUI(HashMap<Integer, String> argument) {
        this.argument = argument;
    }

    @Override
    public String getName() {
        if (argument != null) {
            return "Journeaux de " + argument;
        }

        return "Fichier journeaux";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void getContents(Player player, Inventory inventory) throws SQLException {
        // TODO: Add page system
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        PreparedStatement statement = null;
        if (argument != null) {
            statement = connection.prepareStatement("SELECT * FROM logs WHERE target_name = ? ORDER BY logged_at DESC LIMIT 53;");
            statement.setString(1, argument);
        } else {
            statement = connection.prepareStatement("SELECT * FROM logs ORDER BY logged_at DESC LIMIT 53;");
        }

        final ResultSet resultSet = statement.executeQuery();
        int slot = 0;
        while (resultSet.next()) {
            EnumLogs logType = EnumLogs.valueOf(resultSet.getString("type"));

            ItemStack item = null;
            item = ItemUtils.customizedItem(
                    Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(logType.getItemHead())),
                    ColorUtils.colorize(logType.getItemColor().color() + logType.getItemTitle()),
                    logType.getItemLore(logType, resultSet, player)
            );

            inventory.setItem(slot, item);
            slot++;
        }

        // TODO
        inventory.setItem(54, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("50509"), "§cPage suivante", new ArrayList<>()));
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

    @Override
    public void setArgument(HashMap<Integer, String> arguments) {
        this.argument = argument;
    }
}
