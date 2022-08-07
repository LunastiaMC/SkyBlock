package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.manager.Manager;
import org.bukkit.inventory.ItemStack;

public class Warp {
    public final String identifier;
    public String displayName;
    public int headId;

    public Warp(String identifier, String displayName, int headId) {
        this.identifier = identifier;
        this.displayName = displayName;
        this.headId = headId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ItemStack getHead() {
        return Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(headId));
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getHeadId() {
        return headId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
