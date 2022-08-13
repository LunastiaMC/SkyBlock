package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.gui.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GUIManager implements Listener {
    private final Map<Class<? extends GUI>, GUI> registeredGUIs;

    public GUIManager() {
        this.registeredGUIs = new HashMap<>();

        this.addMenu(new HatListGUI());
        this.addMenu(new TrashGUI());
        this.addMenu(new RepairGUI());
        this.addMenu(new LogsGUI());

        Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
    }

    private Map<Class<? extends GUI>, GUI> getRegisteredGUIs() {
        return registeredGUIs;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack item = event.getCurrentItem();

        if (event.getCurrentItem() == null) return;

        getRegisteredGUIs().values().stream()
                .filter(menu -> event.getView().getTitle().equals(menu.getName()))
                .forEach(gui -> {
                    try {
                        gui.onClick(player, inventory, item, event.getSlot(), event.getClick());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    event.setCancelled(gui.clickCancelled());
                });
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        getRegisteredGUIs().values().stream()
                .filter(menu -> event.getView().getTitle().equals(menu.getName()))
                .forEach(gui -> {
                    gui.onOpen(player, inventory);
                });
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        getRegisteredGUIs().values().stream()
                .filter(menu -> event.getView().getTitle().equals(menu.getName()))
                .forEach(gui -> {
                    gui.onClose(player, inventory);
                });
    }

    public void addMenu(GUI builder) {
        getRegisteredGUIs().put(builder.getClass(), builder);
    }

    public void open(Player player, Class<? extends GUI> GUIClass) throws SQLException {
        if (!getRegisteredGUIs().containsKey(GUIClass)) return;
        GUI gui = getRegisteredGUIs().get(GUIClass);

        Inventory inventory = null;

        if (gui.getInventoryType() == null) inventory = Bukkit.createInventory(null, gui.getSize(), gui.getName());
        else inventory = Bukkit.createInventory(null, gui.getInventoryType(), gui.getName());

        gui.getContents(player, inventory);
        player.openInventory(inventory);
    }

    public void open(Player player, Class<? extends GUI> GUIClass, String argument) throws SQLException {
        if (!getRegisteredGUIs().containsKey(GUIClass)) return;
        GUI gui = getRegisteredGUIs().get(GUIClass);
        gui.setArgument(argument);
        System.out.println(argument);

        Inventory inventory = null;

        if (gui.getInventoryType() == null) inventory = Bukkit.createInventory(null, gui.getSize(), gui.getName());
        else inventory = Bukkit.createInventory(null, gui.getInventoryType(), gui.getName());

        gui.getContents(player, inventory);
        player.openInventory(inventory);
    }
}
