package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;

@Command("craft")
@Permission("skyblock.craft.command")
public class CraftCommand {
    @Default
    public static void enderchest(Player player) {
        Inventory inventory = Bukkit.createInventory(player,InventoryType.WORKBENCH,"Établi portatif");
        player.openInventory(inventory);
    }
}
