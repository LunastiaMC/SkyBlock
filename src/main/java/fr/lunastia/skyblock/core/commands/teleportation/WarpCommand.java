package fr.lunastia.skyblock.core.commands.teleportation;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AGreedyStringArgument;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.entity.Player;

@Command("warp")
public class WarpCommand {
    @Default
    public static void warp(Player player) {

    }

    @Subcommand("set")
    @Permission("skyblock.warp.set.command")
    public static void set(Player player, @AStringArgument String name, @AIntegerArgument int headId, @AGreedyStringArgument String displayName) {

    }
}
