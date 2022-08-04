package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.gui.TrashGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;

@Command("trash")
@Permission("skyblock.trash.command")
public class TrashCommand {
    @Default
    public static void trash(Player player) {
        Manager.getGUIManager().open(player, TrashGUI.class);
    }
}
