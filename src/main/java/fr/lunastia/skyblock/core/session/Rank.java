package fr.lunastia.skyblock.core.session;

import fr.lunastia.skyblock.core.Core;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;

public record Rank(int id, String name, String coloredName, String nametagName, String arrow,
                   ArrayList<String> permissions) {

    public static void applyPermissions(Player player, String[] permissions) {
        PermissionAttachment attachment = player.addAttachment(Core.getInstance());
        for (String permission : permissions) {
            attachment.setPermission(permission, true);
        }
    }

    public void applyPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(Core.getInstance());
        for (String permission : permissions) {
            attachment.setPermission(permission, true);
        }
    }
}