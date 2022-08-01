package fr.lunastia.skyblock.core.commands.utils;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@Command("announce")
@Permission("skyblock.announce.command")
public class AnnounceCommand {
    @Default
    public static void announce(Player player, @AMultiLiteralArgument({"green", "red", "blue", "skyblock", "orange", "help"}) String type, @AGreedyStringArgument String message) {
        String prefix = ColorUtil.colorize(switch (type) {
            case "green" -> "&#079809&l[ &#079009&lS&#068808&lk&#068008&ly&#067807&lb&#057107&ll&#056906&lo&#056106&lc&#045905&lk &#045105&l]";
            case "red" -> "&#8e0000&l[ &#8e0000&lS&#8e0000&lk&#8e0000&ly&#8e0000&lb&#8e0000&ll&#8e0000&lo&#8e0000&lc&#8e0000&lk &#8e0000&l]";
            case "blue" -> "&#000098&l[ &#070f99&lS&#0f1d99&lk&#162c9a&ly&#1e3b9a&lb&#25499b&ll&#2d589b&lo&#34679c&lc&#3c759c&lk &#43849d&l]";
            case "orange" -> "&#985b33&l[ &#915731&lS&#8a532e&lk&#834e2c&ly&#7c4a2a&lb&#744627&ll&#6d4225&lo&#663d23&lc&#5f3920&lk &#58351e&l]";
            
            default -> ColorUtil.PREFIX;
        });

        String coloredText = ColorUtil.colorize(message);

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(prefix + coloredText);
        });
    }
}

// Couleur Skyblock : &#04267f&l[ &#0d2f7f&lS&#16387e&lk&#1f417e&ly&#284a7e&lb&#31537d&ll&#3a5c7d&lo&#43657d&lc&#4c6e7c&lk &#55777c&l]
// Couleur Rouge : &#8e0000&l[ &#8e0000&lS&#8e0000&lk&#8e0000&ly&#8e0000&lb&#8e0000&ll&#8e0000&lo&#8e0000&lc&#8e0000&lk &#8e0000&l]
// Couleur Bleu : &#000098&l[ &#070f99&lS&#0f1d99&lk&#162c9a&ly&#1e3b9a&lb&#25499b&ll&#2d589b&lo&#34679c&lc&#3c759c&lk &#43849d&l]
// Couleur Verte : &#079809&l[ &#079009&lS&#068808&lk&#068008&ly&#067807&lb&#057107&ll&#056906&lo&#056106&lc&#045905&lk &#045105&l]
// Couleur Orange : &#985b33&l[ &#915731&lS&#8a532e&lk&#834e2c&ly&#7c4a2a&lb&#744627&ll&#6d4225&lo&#663d23&lc&#5f3920&lk &#58351e&l]
