package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

@Command("kick")
@Permission("skyblock.kick.command")
public class KickCommand {
    @Default
    public static void kick(Player player, @APlayerArgument Player target, @AGreedyStringArgument String reason) {
        ColorUtils.sendMessage(player, "Vous venez d'expulser le joueur " + Colors.MOD_RED.color() + target.getName() + " §7pour la raison: " + Colors.MOD_RED.color() + reason, Colors.PREFIX);
        String[] message = new String[]{
                ColorUtils.colorize(Colors.MOD_RED.color() + "§lVous êtes expulsé(e) du serveur\n\n"),
                ColorUtils.colorize("§7§l➤§r§7 Pour la raison suivante: " + Colors.MOD_RED.color() + reason) + "\n",
                "\n",
                ColorUtils.colorize("§r§7Si vous pensez qu'il y a un abus de pouvoir") + "\n",
                ColorUtils.colorize("§r§7vous pouvez ouvrir un ticket sur le discord") + "\n",
                ColorUtils.colorize(Colors.DISCORD_COLOR.color() + "discord.gg/F9aQyQZxQr")
        };
    }
}
