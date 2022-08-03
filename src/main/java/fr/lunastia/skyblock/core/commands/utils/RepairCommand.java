package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import fr.lunastia.skyblock.core.utils.ColorUtils;
import org.bukkit.entity.Player;

@Command("repair")
@Permission("skyblock.repair.command")
public class RepairCommand {
    @Default
    public static void repair(Player player) {
        if (player.getInventory().getItemInMainHand() == null) {
            ColorUtils.sendMessage(player, "Vous n'avez pas d'objet dans votre main !", ColorUtils.PREFIX, true);
            return;
        }

        if (player.getInventory().getItemInMainHand().getDurability() == 0) {
            ColorUtils.sendMessage(player, "Votre objet est déjà impeccable !", ColorUtils.PREFIX, true);
            return;
        }

        player.getInventory().getItemInMainHand().setDurability((short) 0);
        ColorUtils.sendMessage(player, "Votre objet a été réparé !", ColorUtils.PREFIX);
    }
}
