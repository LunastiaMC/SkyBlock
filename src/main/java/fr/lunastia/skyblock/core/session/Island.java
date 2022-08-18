package fr.lunastia.skyblock.core.session;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;

import java.util.UUID;

public abstract class Island {
    private final UUID uuid;
    private String displayName;
    private String name;
    private final MultiverseWorld world;

    public Island(UUID uuid) {
        this.uuid = uuid;
        this.world = null;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public MultiverseWorld getWorld() {
        return world;
    }
}
