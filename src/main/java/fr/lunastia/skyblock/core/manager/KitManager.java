package fr.lunastia.skyblock.core.manager;


import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.Kit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class KitManager {
    private HashMap<String, Kit> kits;

    public KitManager() {
        kits = new HashMap<>();
    }

    public void addKit(String identifier, Kit kit) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO sessions (uuid, rank, money, permissions, freezed, vanished) VALUES (?,?,?,?,?,?)");
        statementCreation.setString(1, player.getUniqueId().toString());
        statementCreation.setInt(2, Manager.getRankManager().getDefaultRank().id());
        statementCreation.setLong(3, 0);
        statementCreation.setString(4, "");
        statementCreation.setBoolean(5, false);
        statementCreation.setBoolean(6, false);
        statementCreation.execute();

        final Session session = new Session(player, Manager.getRankManager().getDefaultRank().id(), 0L, new String[]{}, false, false);
        this.sessions.put(player.getUniqueId().toString(), session);
    }
}
