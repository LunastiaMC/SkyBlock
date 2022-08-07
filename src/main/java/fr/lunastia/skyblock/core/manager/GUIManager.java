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
    private final Map<Class<? extends GUIBuilder>, GUIBuilder> registeredGUIs;

    public GUIManager() {
        this.registeredGUIs = new HashMap<>();

        this.addMenu(new HatListGUI());
        this.addMenu(new TrashGUI());
        this.addMenu(new RepairGUI());
        this.addMenu(new KitGUI());

        Bukkit.getPluginManager().registerEvents(this, Core.getInstance());
    }

    private Map<Class<? extends GUIBuilder>, GUIBuilder> getRegisteredGUIs() {
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

    public void addMenu(GUIBuilder builder) {
        getRegisteredGUIs().put(builder.getClass(), builder);
    }

    public void open(Player player, Class<? extends GUIBuilder> GUIClass) {
        if (!getRegisteredGUIs().containsKey(GUIClass)) return;

        GUIBuilder builder = getRegisteredGUIs().get(GUIClass);
        Inventory inventory = Bukkit.createInventory(null, builder.getSize(), builder.getName());
        builder.getContents(player, inventory);
        player.openInventory(inventory);
    }
}
