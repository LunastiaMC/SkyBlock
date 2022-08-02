package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.AOfflinePlayerArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import fr.lunastia.skyblock.core.utils.discord.DiscordWebhook;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@Command("report")
public class ReportCommand {
    @Default
    public static void report(Player player) {
        ColorUtil.sendMessage(player, "Si vous shouaitez signaler un joueur executez §f/report player§c, si vous avez besoins de signaler une faille, un bug executez §f/report bug", ColorUtil.PREFIX, true);
    }

    @Subcommand("bug")
    public static void bug(Player player, @AMultiLiteralArgument({"Duplication", "Ile", "Autre"}) String category, @AGreedyStringArgument String description) {
        // TODO: https://todoist.com/showTask?id=6052819635&sync_id=6052819635
        String timeStamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.FRANCE).format(new Date());

        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1003757892878471259/YrwPWzdmrKvZrIMJNpafKKq0nYkipjvP_2rMyLcbXELqWUBLuc_i8AeYSRe90OJfy0rm");
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .addField("Catégorie:", category,false)
                .addField("Description du signalement:", description,true)
                .setFooter(timeStamp, null)
                .setAuthor("Auteur du signalement: " + player.getName(), null, null)
                .setColor(new Color(0, 255, 0))
                .setThumbnail("https://mc-heads.net/avatar/" + player.getName())
        );

        try {
            webhook.execute();
            ColorUtil.sendMessage(player, "Votre report a bien été prit en compte !", ColorUtil.PREFIX);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Subcommand("player")
    public static void player(Player player, @APlayerArgument Player player_reported, @AGreedyStringArgument String description) {
        if (player.getName().equals(player_reported.getName())) {
            ColorUtil.sendMessage(player, "Vous ne pouvez pas vous signaler vous-même !", ColorUtil.PREFIX,true);
            return;
        }

        // TODO: https://todoist.com/showTask?id=6052819635&sync_id=6052819635
        String timeStamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy", Locale.FRANCE).format(new Date());

        DiscordWebhook webhook = new DiscordWebhook("https://discord.com/api/webhooks/1003778487389262005/Tv8dDl5n-s-7lfKSzNRR6aLXwy2p3p46iAVF4yk4GgdeXimmnChPwOFltE5bjE-7vIiJ");
        webhook.addEmbed(
                new DiscordWebhook.EmbedObject()
                        .addField("Joueur signalé:", player_reported.getName(),false)
                        .addField("Description du signalement:", description,true)
                        .setFooter(timeStamp, null)
                        .setAuthor("Auteur du signalement: " + player.getName(), null, null)
                        .setColor(new Color(255, 0, 0))
                        .setThumbnail("https://mc-heads.net/avatar/" + player.getName())
        );

        try {
            webhook.execute();
            ColorUtil.sendMessage(player, "Votre report a bien été prit en compte !", ColorUtil.PREFIX);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
