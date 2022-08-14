package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.manager.ModerationManager;
import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

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
                if (target == null) {
                    System.out.println("aa");
                    statement.setNull(2, Types.NULL);
                    statement.setNull(3, Types.NULL);
                } else {
                    System.out.println("bb");
                    statement.setString(2, target.getUniqueId().toString());
                    statement.setString(3, target.getName());
                }
                if (moderator == null) {
                    statement.setNull(4, Types.NULL);
                    statement.setNull(5, Types.NULL);
                } else {
                    statement.setString(4, moderator.getUniqueId().toString());
                    statement.setString(5, moderator.getName());
                }
                if (reason == null) {
                    statement.setNull(6, Types.NULL);
                } else {
                    statement.setString(6, reason);
                }
                statement.setString(7, startAt);
                if (expireAt == null) {
                    statement.setNull(8, Types.NULL);
                } else {
                    statement.setString(8, expireAt);
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

    default void sendLogs(EnumLogs type, Player player, String startAt, boolean a) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, startAt) VALUES (?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, player.getUniqueId().toString());
                statement.setString(3, player.getName());
                statement.setString(4, startAt);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    default void sendLogs(EnumLogs type, Player player, String target, String startAt, boolean a) {
        Core.getInstance().getServer().getScheduler().runTaskAsynchronously(Core.getInstance(), () -> {
            try {
                Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, moderator_name, startAt) VALUES (?,?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setNull(2, Types.NULL);
                statement.setString(3, target);
                statement.setString(4, player.getUniqueId().toString());
                statement.setString(5, player.getName());
                statement.setString(6, startAt);
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
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, moderator_name, startAt, old_gamemode, new_gamemode) VALUES (?,?,?,?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                if (moderator != null) {
                    statement.setString(4, moderator.getUniqueId().toString());
                    statement.setString(5, moderator.getName());
                } else {
                    statement.setNull(4, Types.NULL);
                    statement.setNull(5, Types.NULL);
                }
                statement.setString(6, startAt);
                statement.setInt(7, oldGamemode);
                statement.setInt(8, newGamemode);
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
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO logs (type, target, target_name, moderator, moderator_name, startAt, balance_old, balance_new, balance_transaction, balance_transaction_target, balance_transaction_type) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                statement.setString(1, type.toString());
                statement.setString(2, target.getUniqueId().toString());
                statement.setString(3, target.getName());
                if (moderator != null) {
                    statement.setString(4, moderator.getUniqueId().toString());
                    statement.setString(5, moderator.getName());
                } else {
                    statement.setNull(4, Types.NULL);
                    statement.setNull(5, Types.NULL);
                }
                statement.setString(6, startAt);
                statement.setInt(7, oldBalance);
                statement.setInt(8, newBalance);
                statement.setInt(9, amount);
                if (transactionTarget == null) {
                    statement.setNull(10, Types.NULL);
                } else {
                    statement.setString(10, transactionTarget);
                }
                statement.setString(11, transactionType.toString());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
