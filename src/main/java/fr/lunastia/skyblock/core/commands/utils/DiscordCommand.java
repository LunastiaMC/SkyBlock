package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import fr.lunastia.skyblock.core.utils.ColorUtil;
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
        ColorUtil.sendMessage(player, String.valueOf(message), "&#229dc3&l[&#2796c6&lS&#2b8ec9&lk&#3087cc&ly&#357fcf&lB&#3978d1&ll&#3e70d4&lo&#4369d7&lc&#4761da&lk&#4c5add&l]§r§7");
    }
}
