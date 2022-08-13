package fr.lunastia.skyblock.core.session;

import com.nametagedit.plugin.NametagEdit;
import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
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

        Session session = Manager.getSessionManager().getSession(player);
        NametagEdit.getApi().setPrefix(player, ColorUtils.colorize(session.getRank().nametagName()) + "§7");
        player.setPlayerListName(ColorUtils.colorize(session.getRank().nametagName()) + player.getName());
    }
}