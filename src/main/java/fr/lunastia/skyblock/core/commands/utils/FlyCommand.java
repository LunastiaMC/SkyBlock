package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@Command("fly")
public class FlyCommand {
    @Default
    @Permission("skyblock.fly.use")
    public static void fly(Player player) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
            ColorUtils.sendMessage(player, "Vous ne pouvez pas effectuer cette commande en créatif !", ColorUtils.PREFIX);
            return;
        }
        if (player.getWorld().getName().equals(player.getUniqueId() + "") || player.isOp()) {
            if (player.isFlying()) {
                player.setFlying(false);
                ColorUtils.sendMessage(player, "Vous venez de désactiver le fly !", ColorUtils.PREFIX);
            } else {
                player.setFlying(true);
                ColorUtils.sendMessage(player, "Vous devez d'activer le fly !", ColorUtils.PREFIX);
            }
        } else {
            ColorUtils.sendMessage(player, "Vous devez effectuer cette commande dans votre île !", ColorUtils.PREFIX);
        }
    }
}
