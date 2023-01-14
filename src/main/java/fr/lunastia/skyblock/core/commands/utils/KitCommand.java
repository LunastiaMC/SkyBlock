package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import fr.lunastia.skyblock.core.gui.KitGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.Kit;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.sql.SQLException;

@Command("kit")
public class KitCommand {
    @Default
    public static void kit(Player player) {
        try {
            Manager.getGUIManager().open(player, KitGUI.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("add")
    @Permission("skyblock.kit.add")
    public static void add(Player player, @AStringArgument String identifier, @AIntegerArgument Integer headId, @AGreedyStringArgument String displayName) {
        if (Manager.getKitManager().exist(identifier)) {
            ColorUtils.sendMessage(player, "Cet équipement existe déjà !", Colors.PREFIX, true);
            return;
        }

        final Kit kit = new Kit(identifier, displayName, headId, "kit.default", player.getInventory());
        try {
            Manager.getKitManager().addKit(kit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ColorUtils.sendMessage(player, "L'équipement a bien été ajouté !", Colors.PREFIX);
    }

    @Subcommand("edit")
    @Permission("skyblock.kit.update")
    public static void permission(Player player, @AStringArgument String
            identifier, @AMultiLiteralArgument({"displayName", "headId", "permission"}) String type, @AStringArgument String
                                          value) {
        if (!Manager.getKitManager().exist(identifier)) {
            ColorUtils.sendMessage(player, "Cet équipement n'existe pas !", Colors.PREFIX, true);
            return;
        }

        Kit kit = Manager.getKitManager().getKit(identifier);
        switch (type) {
            case "displayName" -> {
                try {
                    kit.setDisplayName(value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "headId" -> {
                try {
                    kit.setHeadId(Integer.parseInt(value));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case "permission" -> {
                try {
                    kit.setPermission(value);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ColorUtils.sendMessage(player, "L'équipement a bien été modifié !", Colors.PREFIX);

    }

    @Subcommand("inventory")
    @Permission("skyblock.kit.inventory")
    public static void inventory(Player player, @AStringArgument String identifier) {
        if (!Manager.getKitManager().exist(identifier)) {
            ColorUtils.sendMessage(player, "Cet équipement n'existe pas !", Colors.PREFIX, true);
            return;
        }

        Kit kit = Manager.getKitManager().getKit(identifier);
        try {
            kit.setInventory(player.getInventory());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ColorUtils.sendMessage(player, "L'équipement a bien été modifié !", Colors.PREFIX);
    }
}
