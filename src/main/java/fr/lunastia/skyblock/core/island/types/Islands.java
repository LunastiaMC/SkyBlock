package fr.lunastia.skyblock.core.island.types;

import fr.lunastia.skyblock.core.session.Island;

public enum Islands {
    SLEEPING_FOREST(SleepingForest.class),
    ABYSSAL_DEPTH(Island.class),
    LIVING_SWAMPS(Island.class),
    SINISTER_DIMENSION(Island.class);

    private final Class<? extends Island> instance;

    Islands(Class<? extends Island> clazz) {
        this.instance = clazz;
    }

    public Class<? extends Island> getInstance() {
        return instance;
    }
}
