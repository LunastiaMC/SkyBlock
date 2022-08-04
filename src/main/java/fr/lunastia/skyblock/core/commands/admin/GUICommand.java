package fr.lunastia.skyblock.core.commands.admin;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command("gui")
@Permission("skyblock.gui.test.command")
public class GUICommand {
    @Default
    public static void gui(Player player, @AMultiLiteralArgument({"9","18","27","36","45","54"}) int size, @AStringArgument String title) {
        player.openInventory(Bukkit.createInventory(player, size, title));
    }
}
