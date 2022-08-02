package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("clear")
@Permission("skyblock.clear.command")
public class ClearCommand {
    @Default
    public static void clear(Player player , @APlayerArgument Player playerargs)
    {
        final int inventairesize = playerargs.getInventory().getContents().length;
        playerargs.getInventory().clear();
        ColorUtil.sendMessage(player, "Vous avez clear" + playerargs.getName() + " avec " + inventairesize + " items", ColorUtil.PREFIX);
    }
}
