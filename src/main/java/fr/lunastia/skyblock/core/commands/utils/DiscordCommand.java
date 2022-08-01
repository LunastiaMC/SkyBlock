package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.entity.Player;
import net.md_5.bungee.api.chat.TextComponent;

@Command("discord")

public class DiscordCommand {
    @Default
    public static void discord(Player player)
    {
        TextComponent message = new TextComponent("Cliquez ici pour rejoindre notre discord !");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/TRAkAV6PC4"));
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("https://discord.gg/TRAkAV6PC4").bold(true).create()));
        player.spigot().sendMessage(message);
    }
}
