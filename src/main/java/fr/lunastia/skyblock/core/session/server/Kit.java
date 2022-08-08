package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class Kit {

    private final String identifier;
    private String displayName;
    private int headId;
    private String permission;
    private Inventory inventory;

    public Kit(String identifier, String displayName, int headId, String permission, Inventory inventory) {
        this.identifier = identifier;
        this.displayName = displayName;
        this.headId = headId;
        this.permission = permission;
        this.inventory = inventory;
    }
    // TODO: Faire une fonction pour distribuer les objets de inventory
    //       Si il a la main de gauche vide par exemple alors sa lui applique l'objet de deuxième main de inventory
    //       Sinon, sa lui met dans son inventaire
    //       Et ainsi de suite pareil pour l'armure
    //       Avant de donner le kit vérifier si il a de la place, si il en à pas on drop a terre
    //       En lui notifiant qu'on a jeter a terre ces objets


    public String getIdentifier() {
        return identifier;
    }

    public ItemStack getHead() {
        return Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(headId));
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) throws SQLException {
        this.headId = headId;
        Manager.getKitManager().updateKit(this);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) throws SQLException {
        this.displayName = displayName;
        Manager.getKitManager().updateKit(this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) throws SQLException {
        this.inventory = inventory;
        Manager.getKitManager().updateKit(this);
    }

    public String getPermission() {
        return permission;
    }
}
