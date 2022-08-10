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
    PLAYER_MUTE_EXPIRED,

    EnumLogs(String action) {
    }
}
