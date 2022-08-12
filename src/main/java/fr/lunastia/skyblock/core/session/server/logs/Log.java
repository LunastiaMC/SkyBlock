package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Log {
    default void sendModLog(EnumLogs type, Player target, Player moderator, String reason, String expireAt) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, reason, expireAt) VALUES (?,?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                statement.setString(4, moderator.getUniqueId().toString());
                statement.setString(5, reason);
                statement.setString(6, expireAt);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendCommonLog(EnumLogs type, Player target) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name) VALUES (?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
