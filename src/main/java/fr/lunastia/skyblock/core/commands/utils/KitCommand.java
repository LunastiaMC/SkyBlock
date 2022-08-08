package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import fr.lunastia.skyblock.core.gui.KitGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("kit")
public class KitCommand {
    public static String List;

    @Default
    public static void kitList(Player player) {
        Manager.getGUIManager().open(player, KitGUI.class);
    }

    @Subcommand("add")
    public static void addKIt(Player player, @AGreedyStringArgument String nom, @AGreedyStringArgument String permission) throws SQLException {
    }
}
