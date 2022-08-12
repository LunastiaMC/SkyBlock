package fr.lunastia.skyblock.core.manager;

import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Date;

public class ModerationManager {
    public void addBan(Player player, String expire, String reason) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("INSERT INTO bans (uuid, startDate, endDate, reason) VALUES (?,?,?,?)");
        statement.setString(1, player.getUniqueId().toString());

        Date date = new Date();
        String startString = date.toString();

        String[] startSplit = startString.split(" ");
        statement.setString(2, startSplit[2] + " " + startSplit[1] + " " + startSplit[5] + " " + startSplit[3]);

        String[] expireSplit = expire.split(" ");
        statement.setString(3, expireSplit[2] + " " + expireSplit[1] + " " + expireSplit[5] + " " + expireSplit[3]);

        if (reason == null) statement.setNull(4, Types.NULL);
        else statement.setString(4, reason);

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
