package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import fr.lunastia.skyblock.core.utils.Discord.DiscordWebhook;
import org.bukkit.entity.Player;

import java.io.IOException;

@Command("report")

public class ReportCommand {
    @Default
    public static void erreur(Player player) {
        ColorUtil.sendMessage(player, "Tu dois indiquer si tu veux report un §nbug §r§7 ou un §nplayer", ColorUtil.ERREUR);
    }

    @Subcommand("bug")
    public static void bug(Player player, @AMultiLiteralArgument({ "Duplication", "Ile", "Autre" }) String categorie, @AGreedyStringArgument String description)
    {
        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1003757892878471259/YrwPWzdmrKvZrIMJNpafKKq0nYkipjvP_2rMyLcbXELqWUBLuc_i8AeYSRe90OJfy0rm");
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setDescription(description).setTitle(categorie));
        try {
            webhook.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
