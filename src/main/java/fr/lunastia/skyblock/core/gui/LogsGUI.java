package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeLogs;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LogsGUI implements GUI {
    private HashMap<Integer, String> argument = new HashMap<>();

    public LogsGUI() {

    }

    public LogsGUI(HashMap<Integer, String> argument) {
        this.argument = argument;
    }

    @Override
    public String getName() {
        if (!Objects.equals(argument.get(0), "null")) {
            return "Journeaux de " + argument.get(0);
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
        final ResultSet resultSet = getByPage(Integer.parseInt(argument.get(1)));
        setInventory(resultSet, inventory, player);
    }

    private void setInventory(ResultSet resultSet, Inventory inventory, Player player) throws SQLException {
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

        // remove all items when all displayed
        for (int i = slot; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }

        if (Integer.parseInt(argument.get(1)) >= 1) {
            inventory.setItem(45, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("7789"), "§cPage précédente (" + argument.get(1) + ")", new ArrayList<>()));
        } else {
            inventory.setItem(45, ItemUtils.customizedItem(new ItemStack(Material.BARRIER), "§cPremière page atteinte", new ArrayList<>()));
        }

        inventory.setItem(49, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("32582"), "§cRecharger", new ArrayList<>()));

        if (inventory.getItem(44) != null) {
            inventory.setItem(53, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("7786"), "§cPage suivante (" + (Integer.parseInt(argument.get(1)) + 1) + ")", new ArrayList<>()));
        } else {
            inventory.setItem(53, ItemUtils.customizedItem(new ItemStack(Material.BARRIER), "§cDernière page atteinte", new ArrayList<>()));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {
        if (itemStack.getType() == Material.BARRIER) return;

        switch (slot) {
            case 45 -> {
                argument.put(1, String.valueOf(Integer.parseInt(argument.get(1)) - 1));
                setInventory(getByPage(Integer.parseInt(argument.get(1))), inventory, player);
            }
            case 49 -> {
                setInventory(getByPage(Integer.parseInt(argument.get(1))), inventory, player);
            }
            case 53 -> {
                argument.put(1, Integer.toString(Integer.parseInt(argument.get(1)) + 1));
                setInventory(getByPage(Integer.parseInt(argument.get(1))), inventory, player);
            }
        }

        getName();
    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        LogTypeLogs log = null;
        if (argument.get(0) != null) {
            log = new LogTypeLogs(EnumLogs.LOGS_OPEN, player, argument.get(1));
        } else {
            log = new LogTypeLogs(EnumLogs.LOGS_OPEN, player, null);
        }
        log.send();
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

    @Override
    public void setArgument(HashMap<Integer, String> arguments) {
        this.argument = arguments;
    }

    public ResultSet getByPage(int page) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        PreparedStatement statement = null;
        if (!Objects.equals(argument.get(0), "null")) {
            statement = connection.prepareStatement("SELECT * FROM logs WHERE target_name = ? ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
            statement.setString(1, argument.get(0));
            statement.setInt(2, page == 0 ? 0 : page * 45);
        } else {
            statement = connection.prepareStatement("SELECT * FROM logs ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
            statement.setInt(1, page == 0 ? 0 : page * 45);
        }
        return statement.executeQuery();
    }
}
