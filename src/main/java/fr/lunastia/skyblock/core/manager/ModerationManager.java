package fr.lunastia.skyblock.core.manager;

import org.bukkit.entity.Player;

import java.sql.*;

public class ModerationManager {
    public void addBan(Player player, String expire, String reason) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("INSERT INTO bans (uuid, expire, reason) VALUES (?,?,?)");
        statement.setString(1, player.getUniqueId().toString());

        if (expire == null) statement.setNull(2, Types.NULL);
        else statement.setString(2, expire);

        if (reason == null) statement.setNull(3, Types.NULL);
        else statement.setString(3, reason);
        statement.execute();
    }

    public void delBan(String target) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("DELETE FROM bans WHERE uuid = ?");
        statement.setString(1, target);
        statement.execute();
    }

    public boolean isBanned(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM bans WHERE uuid = ?");
        statementFinder.setString(1, player.getUniqueId().toString());
        final ResultSet resultSet = statementFinder.executeQuery();

        return resultSet.next();
    }

    public ResultSet getBanInfo(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM bans WHERE uuid = ?");
        statementFinder.setString(1, player.getUniqueId().toString());

        return statementFinder.executeQuery();
    }
}
