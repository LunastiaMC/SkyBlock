package fr.lunastia.skyblock.core.session.server;

public enum EnumLogs {
    PLAYER_JOIN,
    PLAYER_QUIT,
    PLAYER_KICKED,
    PLAYER_BANNED,
    PLAYER_IP_BANNED,
    PLAYER_UNBANNED,
    PLAYER_IP_UNBANNED,
    PLAYER_MUTED,
    PLAYER_UNMUTED,
    PLAYER_FREEZED,
    PLAYER_UNFREEZED,
    PLAYER_CHANGE_HAT,
    PLAYER_RANK_UPDATED,
    PLAYER_MUTE_EXPIRED,
    PLAYER_TELEPORT,
    PLAYER_TELEPORTED_BY_FORCE, // /tp Joueur1 Joueur2
    PLAYER_KIT_USE,
    KIT_ADDED,
    KIT_REMOVED,
    KIT_UPDATED,
    ISLAND_MEMBER_ADDED,
    ISLAND_MEMBER_REMOVED,
    ISLAND_MEMBER_KICKED,
    ISLAND_BANK_DEPOSIT,
    ISLAND_BANK_WITHDRAW,
    ISLAND_BANK_DEPOSIT_FAILED,
    ISLAND_BANK_WITHDRAW_FAILED,
    ISLAND_VISITOR_ADDED,
    ISLAND_VISITOR_REMOVED,
    ISLAND_VISITOR_KICKED,
    ISLAND_VISITOR_BANNED,
    ISLAND_VISITOR_UNBANNED,
    ISLAND_WEATHER_CHANGED,
    ISLAND_TIME_CHANGED;

    public static EnumLogs getByName(final String name) {
        for (final EnumLogs enumLogs : values()) {
            if (enumLogs.name().equalsIgnoreCase(name)) {
                return enumLogs;
            }
        }
        return null;
    }

    public static EnumLogs getByIndex(final int index) {
        for (final EnumLogs enumLogs : values()) {
            if (enumLogs.ordinal() == index) {
                return enumLogs;
            }
        }
        return null;
    }

    public static boolean isPlayerLog(final EnumLogs enumLogs) {
        return enumLogs.ordinal() <= PLAYER_KIT_USE.ordinal();
    }

    public static boolean isIslandLog(final EnumLogs enumLogs) {
        return enumLogs.ordinal() >= ISLAND_MEMBER_ADDED.ordinal();
    }
}
