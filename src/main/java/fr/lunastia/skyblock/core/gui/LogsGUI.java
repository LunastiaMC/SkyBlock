package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeLogs;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

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
        final ResultSet resultSet = getByPage(Integer.parseInt(argument.get(1)), argument.get(2));
        setInventory(resultSet, inventory, player, Objects.equals(argument.get(2), "true"));
    }

    private void setInventory(ResultSet resultSet, Inventory inventory, Player player, boolean archiveds) throws SQLException {
        int slot = 0;
        while (resultSet.next()) {
            EnumLogs logType = EnumLogs.valueOf(resultSet.getString("type"));

            ItemStack item = null;
            item = ItemUtils.customizedItem(
                    Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(logType.getItemHead())),
                    ColorUtils.colorize(logType.getItemColor().color() + logType.getItemTitle()),
                    logType.getItemLore(logType, resultSet, player, archiveds),
                    "logId",
                    UUID.fromString(resultSet.getString("uuid"))
            );

            inventory.setItem(slot, item);
            slot++;
        }

        for (int i = slot; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }

        inventory.setItem(45, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("35128"), "§cJourneaux", new ArrayList<>()));
        inventory.setItem(46, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("50640"), "§cJourneaux archivés", new ArrayList<>()));
        // inventory.setItem(47, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("25058"), "§eAfficher les épinglées", new ArrayList<>()));

        if (Integer.parseInt(argument.get(1)) >= 1) {
            inventory.setItem(51, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("7789"), "§cPage précédente (" + argument.get(1) + ")", new ArrayList<>()));
        } else {
            inventory.setItem(51, ItemUtils.customizedItem(new ItemStack(Material.BARRIER), "§cPremière page atteinte", new ArrayList<>()));
        }
        inventory.setItem(52, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("32582"), "§cRecharger", new ArrayList<>()));
        if (inventory.getItem(44) != null) {
            inventory.setItem(53, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("7786"), "§cPage suivante (" + (Integer.parseInt(argument.get(1)) + 1) + ")", new ArrayList<>()));
        } else {
            inventory.setItem(53, ItemUtils.customizedItem(new ItemStack(Material.BARRIER), "§cDernière page atteinte", new ArrayList<>()));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {
        if (itemStack.getType() == Material.BARRIER) return;

        if (player.isOp()) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            assert itemMeta != null;
            NamespacedKey key = new NamespacedKey(Core.getInstance(), "logId");
            if (itemMeta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                String logUUID = itemMeta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                if (clickType == ClickType.LEFT) {
                    archive(logUUID, !Objects.equals(argument.get(2), "true"), inventory, player);
                }
                return;
            }
        }

        switch (slot) {
            case 45 -> {
                argument.put(2, "false");
                setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, false);
            }
            case 46 -> {
                argument.put(2, "true");
                setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, true);
            }
            case 51 -> {
                argument.put(1, String.valueOf(Integer.parseInt(argument.get(1)) - 1));
                setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, Objects.equals(argument.get(2), "true"));
            }
            case 52 -> {
                setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, Objects.equals(argument.get(2), "true"));
            }
            case 53 -> {
                argument.put(1, Integer.toString(Integer.parseInt(argument.get(1)) + 1));
                setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, Objects.equals(argument.get(2), "true"));
            }
        }
    }

    @Override
    public void onClose(Player player, Inventory inventory) {
        LogTypeLogs log = null;
        if (!Objects.equals(argument.get(0), "null")) {
            log = new LogTypeLogs(EnumLogs.LOGS_CLOSE, player, argument.get(0));
        } else {
            log = new LogTypeLogs(EnumLogs.LOGS_CLOSE, player, null);
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

    public ResultSet getByPage(int page, String a) throws SQLException {
        boolean archivedOnly = Objects.equals(a, "true");

        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        PreparedStatement statement = null;
        if (!Objects.equals(argument.get(0), "null")) {
            if (archivedOnly) {
                statement = connection.prepareStatement("SELECT * FROM logs WHERE target_name = ?, archived = ? ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
                statement.setBoolean(2, true);
            } else {
                statement = connection.prepareStatement("SELECT * FROM logs WHERE target_name = ?, archived = ? ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
                statement.setBoolean(2, false);
            }
            statement.setString(1, argument.get(0));
            statement.setInt(3, page == 0 ? 0 : page * 45);
        } else {
            if (archivedOnly) {
                statement = connection.prepareStatement("SELECT * FROM logs WHERE archived = 1 ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
            } else {
                statement = connection.prepareStatement("SELECT * FROM logs WHERE archived = 0 ORDER BY logged_at DESC LIMIT 45 OFFSET ?;");
            }
            statement.setInt(1, page == 0 ? 0 : page * 45);
        }
        return statement.executeQuery();
    }

    public void archive(String UUID, boolean archived, Inventory inventory, Player player) {
        try {
            Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE logs SET archived = " + archived + " WHERE uuid = ?;");
            statement.setString(1, UUID);
            statement.executeUpdate();
            // Reload inventory
            setInventory(getByPage(Integer.parseInt(argument.get(1)), argument.get(2)), inventory, player, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
