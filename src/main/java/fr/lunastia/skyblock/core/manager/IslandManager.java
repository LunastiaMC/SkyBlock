package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.session.Island;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class IslandManager {
    private HashMap<String, Island> islands;

    public IslandManager() {
        this.islands = new HashMap<>();
    }

    public void loadIsland(String uuid) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM sessions WHERE uuid = ?");
        statementFinder.setString(1, uuid);
        final ResultSet resultSet = statementFinder.executeQuery();

        if (resultSet.next()) {
            final Island island = new Island(uuid);
            this.islands.put(island.getUuid(), new Island(uuid));
        } else {
            UUID newUUID = UUID.randomUUID();

            final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO sessions (uuid, rank, money, permissions, island, freezed, vanished) VALUES (?,?,?,?,?,?,?)");
            statementCreation.setString(1, newUUID.toString());
            statementCreation.execute();

            final Island island = new Island(newUUID.toString());
            this.islands.put(newUUID.toString(), island);
        }
    }

    public void saveIsland(Island island) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        try {
            final PreparedStatement statement = connection.prepareStatement("UPDATE island SET uuid = ? WHERE uuid = ?");
            statement.setString(1, island.getUuid());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Island> getIslands() {
        return islands;
    }

    public Island getIsland(String uuid) {
        return islands.get(uuid);
    }
}
