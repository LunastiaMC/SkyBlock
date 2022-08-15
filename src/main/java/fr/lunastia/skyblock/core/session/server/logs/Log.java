package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.UUID;

public interface Log {
    default void sendModLog(EnumLogs type, Player target, Player moderator, String reason, String startAt, String expireAt) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (uuid, type, target, target_name, moderator, moderator_name, reason, startAt, expireAt) VALUES (?,?,?,?,?,?,?,?,?)");
                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, type.toString());
                if (target == null) {
                    statement.setNull(3, Types.NULL);
                    statement.setNull(4, Types.NULL);
                } else {
                    statement.setString(3, target.getUniqueId().toString());
                    statement.setString(4, target.getName());
                }
                if (moderator == null) {
                    statement.setNull(5, Types.NULL);
                    statement.setNull(6, Types.NULL);
                } else {
                    statement.setString(5, moderator.getUniqueId().toString());
                    statement.setString(6, moderator.getName());
                }
                if (reason == null) {
                    statement.setNull(7, Types.NULL);
                } else {
                    statement.setString(7, reason);
                }
                statement.setString(8, startAt);
                if (expireAt == null) {
                    statement.setNull(9, Types.NULL);
                } else {
                    statement.setString(9, expireAt);
                }
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
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (uuid, type, target, target_name, startAt, hat_name) VALUES (?,?,?,?,?,?)");
                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, type.toString());
                statement.setString(3, target.getUniqueId().toString());
                statement.setString(4, target.getName());
                statement.setString(5, startAt);
                statement.setString(6, hat);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendLogs(EnumLogs type, Player player, String startAt, String openedPlayer, boolean a) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (uuid,type, target, target_name, startAt, logs_opened_player) VALUES (?,?,?,?,?,?)");
                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, type.toString());
                statement.setString(3, player.getUniqueId().toString());
                statement.setString(4, player.getName());
                statement.setString(5, startAt);
                if (openedPlayer != null) {
                    statement.setString(6, openedPlayer);
                } else {
                    statement.setNull(6, Types.NULL);
                }
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendCommonLog(EnumLogs type, Player target, Player moderator, String startAt, Integer oldGamemode, Integer newGamemode) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, moderator_name, startAt, old_gamemode, new_gamemode) VALUES (?,?,?,?,?,?,?,?,?)");
                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, type.toString());
                statement.setString(3, target.getUniqueId().toString());
                statement.setString(4, target.getName());
                if (moderator != null) {
                    statement.setString(5, moderator.getUniqueId().toString());
                    statement.setString(6, moderator.getName());
                } else {
                    statement.setNull(5, Types.NULL);
                    statement.setNull(6, Types.NULL);
                }
                statement.setString(7, startAt);
                statement.setInt(8, oldGamemode);
                statement.setInt(9, newGamemode);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendEconomyLog(EnumLogs type, Player target, Player moderator, Integer oldBalance, Integer newBalance, Integer amount, String transactionTarget, EnumLogs transactionType, String startAt) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (uuid,type, target, target_name, moderator, moderator_name, startAt, balance_old, balance_new, balance_transaction, balance_transaction_target, balance_transaction_type) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                statement.setString(1, UUID.randomUUID().toString());
                statement.setString(2, type.toString());
                statement.setString(3, target.getUniqueId().toString());
                statement.setString(4, target.getName());
                if (moderator != null) {
                    statement.setString(5, moderator.getUniqueId().toString());
                    statement.setString(6, moderator.getName());
                } else {
                    statement.setNull(5, Types.NULL);
                    statement.setNull(6, Types.NULL);
                }
                statement.setString(7, startAt);
                statement.setInt(8, oldBalance);
                statement.setInt(9, newBalance);
                statement.setInt(10, amount);
                if (transactionTarget == null) {
                    statement.setNull(11, Types.NULL);
                } else {
                    statement.setString(11, transactionTarget);
                }
                statement.setString(12, transactionType.toString());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
