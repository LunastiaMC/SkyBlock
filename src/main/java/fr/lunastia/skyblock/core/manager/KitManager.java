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

    public KitManager() {
        kits = new HashMap<>();
    }

    public void addKit(String identifier, Kit kit) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementCreation = connection.prepareStatement("INSERT INTO kits (identifier, displayName, inventory64) VALUES (?,?,?)");
        statementCreation.setString(1, kit.identifier());
        statementCreation.setString(2, kit.displayName());
        statementCreation.setString(3, BukkitSerialization.toBase64(kit.inventory()));
        statementCreation.execute();

        this.kits.put(kit.identifier(), kit);
    }
}
