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
import fr.lunastia.skyblock.core.session.server.Kit;
import fr.lunastia.skyblock.core.utils.ColorUtils;
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
        if(Manager.getKitManager().exist(identifier)) {
            ColorUtils.sendMessage(player, "Cet équipement existe déjà !", ColorUtils.PREFIX, true);
            return;
        }

        final Kit kit = new Kit(identifier, displayName, headId, player.getInventory());
        try {
            Manager.getKitManager().addKit(kit);
            ColorUtils.sendMessage(player, "L'équipement a bien été ajouté !", ColorUtils.PREFIX);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Subcommand("inventory")
    @Permission("skyblock.kit.inventory")
    public static void inventory(Player player, @AStringArgument String identifier) throws SQLException {
        if (!Manager.getKitManager().exist(identifier)) {
            ColorUtils.sendMessage(player, "Cet équipement n'existe pas !", ColorUtils.PREFIX, true);
            return;
        }

        Kit kit = Manager.getKitManager().getKit(identifier);
        kit.setInventory(player.getInventory());
        ColorUtils.sendMessage(player, "L'équipement a bien été modifié !", ColorUtils.PREFIX);
    }
}
