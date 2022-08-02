package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

@Command("discord")

public class DiscordCommand {
    @Default
    public static void discord(Player player) {
        ColorUtil.sendMessage(player, "Voici le lien du discord : https://discord.gg/TRAkAV6PC4", ColorUtil.DISCORD);
    }

    @Subcommand("link")
    public static void link(Player player) {
        // TODO
    }
}
