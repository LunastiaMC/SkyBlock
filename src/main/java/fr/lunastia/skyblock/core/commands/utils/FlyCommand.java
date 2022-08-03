package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command("fly")
public class FlyCommand {
    @Default
    @Permission("skyblock.fly.use")
    public static void fly(Player player)
    {
        if (player.getGameMode().equals(GameMode.CREATIVE))
        {
            ColorUtil.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en créatif !", ColorUtil.PREFIX);
            return;
        }
        if (player.getWorld().getName().equals(player.getUniqueId() + "") || player.isOp())
        {
            if (player.isFlying())
            {
                player.setFlying(false);
                ColorUtil.sendMessage(player, "Vous venez de désactiver le fly !", ColorUtil.PREFIX);
            } else
            {
                player.setFlying(true);
                ColorUtil.sendMessage(player, "Vous devez d'activer le fly !", ColorUtil.PREFIX);
            }
        } else
        {
            ColorUtil.sendMessage(player, "Vous devez effectuer cette commande dans votre île !", ColorUtil.PREFIX);
        }
    }
}
