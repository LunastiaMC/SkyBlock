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
