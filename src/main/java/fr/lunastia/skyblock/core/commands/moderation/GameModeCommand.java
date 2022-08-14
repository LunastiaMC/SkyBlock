package fr.lunastia.skyblock.core.commands.moderation;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

@Command("gamemode")
@Alias("gm")
@Permission("skyblock.gamemode.command")
public class GameModeCommand {

    @Default
    public static void gamemode(Player player, @AMultiLiteralArgument({"creative", "1", "survival", "0", "adventure", "2", "spectator", "3"}) String mode) {
        ColorUtils.sendMessage(player, "Vous avez changé votre mode de jeu en §f" + getNameGameMode(mode), Colors.PREFIX);
        changeGamemode(player, mode);
    }

    @Default
    @Permission("skyblock.gamemode.other.command")
    public static void gamemode(Player player, @APlayerArgument Player target, @AMultiLiteralArgument({"creative", "1", "survival", "0", "adventure", "2", "spectator", "3"}) String mode) {
        changeGamemode(target, mode);

        ColorUtils.sendMessage(target, "Votre mode de jeu a été modifié en §f" + getNameGameMode(mode) + "§7 par §f" + player.getName(), Colors.PREFIX);
        ColorUtils.sendMessage(player, "Vous avez changé le mode de jeu de §f" + target.getName() + " §7en §f" + getNameGameMode(mode), Colors.PREFIX);
    }

    public static String getNameGameMode(String mode) {
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Core.getInstance().getDataFolder(), "config.yml"));
        ConfigurationSection file = config.getConfigurationSection("gamemodes");
        assert file != null;
        return file.getString(mode);
    }

    private static void changeGamemode(Player target, String mode) {
        target.setGameMode(switch (mode) {
            case "creative", "1" -> org.bukkit.GameMode.CREATIVE;
            case "adventure", "2" -> org.bukkit.GameMode.ADVENTURE;
            case "spectator", "3" -> org.bukkit.GameMode.SPECTATOR;
            default -> org.bukkit.GameMode.SURVIVAL;
        });
    }
}