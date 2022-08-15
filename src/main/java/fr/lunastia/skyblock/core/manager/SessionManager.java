package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.session.Session;
import org.bukkit.entity.Player;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.UUID;

public class SessionManager {
    private HashMap<String, Session> sessions;

    public SessionManager() {
        this.sessions = new HashMap<>();
    }

    public void loadSession(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM sessions WHERE uuid = ?");
        statementFinder.setString(1, player.getUniqueId().toString());
        final ResultSet resultSet = statementFinder.executeQuery();

        if (resultSet.next()) {
            final Session session = new Session(player, resultSet);
            this.sessions.put(player.getUniqueId().toString(), session);
        } else {
            final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO sessions (uuid, rank, money, permissions, island, freezed, vanished, hat) VALUES (?,?,?,?,?,?,?,?)");
            statementCreation.setString(1, player.getUniqueId().toString());
            statementCreation.setInt(2, Manager.getRankManager().getDefaultRank().id());
            statementCreation.setLong(3, 0);
            statementCreation.setString(4, "");
            statementCreation.setNull(5, Types.NULL);
            statementCreation.setBoolean(6, false);
            statementCreation.setBoolean(7, false);
            statementCreation.execute();

            final Session session = new Session(player, Manager.getRankManager().getDefaultRank().id(), 0L, new String[]{}, false, false, "");
            statementCreation.setNull(7, Types.NULL);
            statementCreation.execute();

            final Session session = new Session(player, Manager.getRankManager().getDefaultRank().id(), 0L, new String[]{}, false, false, null);
            this.sessions.put(player.getUniqueId().toString(), session);
        }
    }

    public void saveSession(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        Session session = this.sessions.get(player.getUniqueId().toString());

        try {
            final PreparedStatement statement = connection.prepareStatement("UPDATE sessions SET rank = ?, money = ?, permissions = ?, island = ?, freezed = ?, vanished = ?, hat = ? WHERE uuid = ?");
            statement.setInt(1, session.getRank().id());
            statement.setLong(2, session.getMoney());
            statement.setString(3, session.getPermissions());
            if (session.getIslandUUID() != null) {
                statement.setString(4, session.getIslandUUID());
            } else {
                statement.setNull(4, Types.NULL);
            }
            statement.setBoolean(5, session.isFreezed());
            statement.setBoolean(6, session.isVanished());
            if (session.hasHat()) {
                statement.setString(7, session.getHat().getUniqueId().toString());
            } else {
                statement.setNull(7, Types.NULL);
            }
            statement.setString(8, player.getUniqueId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Session getSession(UUID uuid) {
        return this.sessions.get(uuid.toString());
    }

    public Session getSession(Player player) {
        return this.sessions.get(player.getUniqueId().toString());
    }

    public Session getSession(String uuid) {
        return this.sessions.get(uuid);
    }

    public HashMap<String, Session> getSessions() {
        return this.sessions;
    }

    public boolean hasSession(Player player) {
        return this.sessions.containsKey(player.getUniqueId().toString());
    }
}
