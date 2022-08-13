package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.manager.ModerationManager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public interface Log {
    default void sendModLog(EnumLogs type, Player target, Player moderator, String reason, String startAt, String expireAt) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, moderator_name, reason, startAt, expireAt) VALUES (?,?,?,?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                statement.setString(4, moderator.getUniqueId().toString());
                statement.setString(5, moderator.getName());
                statement.setString(6, reason);
                statement.setString(7, startAt);
                statement.setString(8, expireAt);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendCommonLog(EnumLogs type, Player target, String startAt, String hat) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, startAt, hat_name) VALUES (?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                statement.setString(4, ModerationManager.getMinimizedDate(startAt));
                statement.setString(5, hat);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
