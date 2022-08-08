package fr.lunastia.skyblock.core.manager;


import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.session.server.Kit;
import fr.lunastia.skyblock.core.utils.BukkitSerialization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

public class KitManager {
    private HashMap<String, Kit> kits;

    public KitManager() throws SQLException, IOException {
        kits = new HashMap<>();
        // TODO: Charger les kits qui sont déjà dans la base de données
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM kits");
        final ResultSet resultSet = statementFinder.executeQuery();

        while (resultSet.next()) {
            System.out.println("Kit " + resultSet.getString("identifier") + " chargé");
            kits.put(resultSet.getString("identifier"), new Kit(
                    resultSet.getString("identifier"),
                    resultSet.getString("identifier"),
                    resultSet.getInt("headId"),
                    BukkitSerialization.fromBase64(resultSet.getString("items"))
            ));
        }

    }

    public void addKit(Kit kit) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO kits (identifier, displayName, headId, inventory64) VALUES (?,?,?,?)");
        statementCreation.setString(1, kit.identifier());
        statementCreation.setString(2, kit.displayName());
        statementCreation.setInt(3, kit.headId());
        statementCreation.setString(4, BukkitSerialization.toBase64(kit.inventory()));
        statementCreation.execute();

        this.kits.put(kit.identifier(), kit);
    }

    public void removeKit(String identifier) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementCreation = connection.prepareStatement("DELETE FROM kits WHERE identifier = ?");
        statementCreation.setString(1, identifier);
        statementCreation.execute();

        this.kits.remove(identifier);
    }

    public Kit getKit(String identifier) {
        return this.kits.get(identifier);
    }

    public HashMap<String, Kit> getKits() {
        return this.kits;
    }

    public boolean exist(String identifier) {
        return this.kits.containsKey(identifier);
    }

    public boolean exist(Kit kit) {
        return this.kits.containsKey(kit.identifier());
    }
}
