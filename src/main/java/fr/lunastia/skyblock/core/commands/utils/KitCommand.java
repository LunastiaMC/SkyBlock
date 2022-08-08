package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import fr.lunastia.skyblock.core.gui.KitGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("kit")
public class KitCommand {
    @Default
    public static void kit(Player player) {
        Manager.getGUIManager().open(player, KitGUI.class);
    }

    @Subcommand("add")
    @Permission("skyblock.kit.add")
    public static void add(Player player, @AStringArgument String identifier, @AIntegerArgument Integer headId, @AGreedyStringArgument String displayName) {
    }
}
