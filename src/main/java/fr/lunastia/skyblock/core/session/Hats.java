package fr.lunastia.skyblock.core.session;

public enum Hats {
    GLASS("glass", "Chapeau en verre", "skyblock.hat.use"),
    TINTED_GLASS("tinted_glass", "Chapeau en verre teinté", "skyblock.hat.use"),
    WOOL("white_wool", "Chapeau en laine", "skyblock.hat.use"),
    NETHERITE_BLOCK("netherite_block", "Chapeau de bloc de netherite", "skyblock.hat.use");

    private final String identifier;
    private final String displayName;
    private final String permission;

    Hats(String identifier, String displayName, String permission) {
        this.identifier = "minecraft:" + identifier;
        this.displayName = displayName;
        this.permission = permission;
    }

    public static Hats[] getHats() {
        return Hats.values();
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
