package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

public class LogTypeCommon implements Log {
    private final EnumLogs type;
    private final Player target;

    public LogTypeCommon(EnumLogs type, Player target) {
        this.type = type;
        this.target = target;
    }

    public void send() {
        Log.super.sendCommonLog(type, target);
    }
}
