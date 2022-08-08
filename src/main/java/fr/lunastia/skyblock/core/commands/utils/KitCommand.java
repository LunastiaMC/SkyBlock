package fr.lunastia.skyblock.core.commands.utils;


import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import fr.lunastia.skyblock.core.gui.KitGUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Command("kit")
public class KitCommand {
    public static String List;
    @Default
    public static void kitList(Player player)
    {
        Manager.getGUIManager().open(player, KitGUI.class);
    }
    @Subcommand("add")
    public static void addKIt(Player player, @AGreedyStringArgument String nom, @AGreedyStringArgument String permission) throws SQLException {
        if (player.isOp())
        {
            for (ItemStack item : player.getInventory().getContents()) {
                if (List == null)
                {
                    List = item.getItemMeta().getDisplayName() + " ";
                    return;
                }
                List = List + item.getItemMeta().getDisplayName() + " ";
            }
            Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
            PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO kit (nom, content, permission) VALUES (?,?,?)");
            statementCreation.setString(1, nom);
            statementCreation.setInt(2, Manager.getRankManager().getDefaultRank().id());
            statementCreation.setString(3, List);
            statementCreation.execute();
            ColorUtils.sendMessage(player, "Le kit " + nom + " vient d'être crée !", ColorUtils.PREFIX);
        }
    }
}
