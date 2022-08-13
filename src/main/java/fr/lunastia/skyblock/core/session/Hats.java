package fr.lunastia.skyblock.core.session;

import java.util.UUID;

public enum Hats {
    GLASS(UUID.fromString("f31f3518-19ab-499a-a49f-e185cc1b089b"), "glass", "Chapeau en verre", "skyblock.hat.use"),
    TINTED_GLASS(UUID.fromString("fbd30c53-b6cc-40be-b96d-415fa6879844"), "tinted_glass", "Chapeau en verre teinté", "skyblock.hat.use"),
    WOOL(UUID.fromString("b1863e8f-7150-4a13-a5bf-436212b216d1"), "white_wool", "Chapeau en laine", "skyblock.hat.use"),
    NETHERITE_BLOCK(UUID.fromString("4214efdd-af74-493b-8924-396edb561240"), "netherite_block", "Chapeau de bloc de netherite", "skyblock.hat.use");

    private final UUID uuid;
    private final String identifier;
    private final String displayName;
    private final String permission;

    Hats(UUID uuid, String minecraftIdentifier, String displayName, String permission) {
        this.uuid = uuid;
        this.identifier = "minecraft:" + minecraftIdentifier;
        this.displayName = displayName;
        this.permission = permission;
    }

    public static Hats[] getHats() {
        return Hats.values();
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPermission() {
        return permission;
    }
}