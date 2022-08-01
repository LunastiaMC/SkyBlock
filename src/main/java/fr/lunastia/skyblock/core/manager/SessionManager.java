package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.session.Session;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO sessions (uuid, rank, money) VALUES (?,?,?)");
            statementCreation.setString(1, player.getUniqueId().toString());
            statementCreation.setInt(2, Manager.getRankManager().getDefaultRank().id());
            statementCreation.setLong(3, 0);
            statementCreation.execute();

            final Session session = new Session(player, Manager.getRankManager().getDefaultRank().id(), 0L);
            this.sessions.put(player.getUniqueId().toString(), session);
        }
    }

    public void saveSession(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        Session session = this.sessions.get(player.getUniqueId().toString());

        final PreparedStatement statement = connection.prepareStatement("UPDATE sessions SET rank = ?, money = ? WHERE uuid = ?");
        statement.setInt(1, session.getRank().id());
        statement.setLong(2, session.getMoney());
        statement.setString(3, player.getUniqueId().toString());
        statement.execute();
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
}
