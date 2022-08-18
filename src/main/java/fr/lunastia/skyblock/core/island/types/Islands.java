package fr.lunastia.skyblock.core.island.types;

import fr.lunastia.skyblock.core.session.Island;

public enum Islands {
    SLEEPING_FOREST(SleepingForest.class, 53343),
    ABYSSAL_DEPTH(AbyssalDepth.class, 52966),
    LIVING_SWAMPS(LivingSwamps.class, 51906),
    SINISTER_DIMENSION(SinisterDimension.class, 12822);

    public final Class<? extends Island> island;
    private final Integer headId;

    Islands(Class<? extends Island> clazz, Integer headId) {
        this.island = clazz;
        this.headId = headId;

    }

    public Class<? extends Island> getIsland() {
        return island;
    }

    public Integer getHeadId() {
        return headId;
    }
}
