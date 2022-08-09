package fr.lunastia.skyblock.core.session.server;

public enum EnumLogs {
    KICK("kicked"),
    BANNED("banned"),
    PARDONNED("pardoned"),
    IP_BANNED("banned_ip"),
    IP_PARDONNED("pardoned_ip");

    EnumLogs(String action) {
    }
}
