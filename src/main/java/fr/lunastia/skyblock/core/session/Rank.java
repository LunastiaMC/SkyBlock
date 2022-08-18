package fr.lunastia.skyblock.core.session;

import com.nametagedit.plugin.NametagEdit;
import fr.lunastia.skyblock.core.Core;
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

    public void applyPermissions(Session session) {
        PermissionAttachment attachment = session.getPlayer().addAttachment(Core.getInstance());
        for (String permission : permissions) {
            attachment.setPermission(permission, true);
        }

        NametagEdit.getApi().setPrefix(session.getPlayer(), ColorUtils.colorize(session.getRank().nametagName()) + "§7");
        session.getPlayer().setPlayerListName(ColorUtils.colorize(session.getRank().nametagName()) + session.getPlayer().getName());
    }
}