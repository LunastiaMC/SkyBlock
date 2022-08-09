package fr.lunastia.skyblock.core.session;

import java.util.UUID;

public class Island {

    private final UUID uuid;

    public Island(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }
}
