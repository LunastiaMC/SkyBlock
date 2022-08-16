package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTypeGamemode implements Log {
    private final EnumLogs type;
    private final Player target;
    private final Player moderator;
    private final GameMode oldMode;
    private final GameMode newMode;
    private final String startDate;

    public LogTypeGamemode(EnumLogs type, Player target, Player moderator, GameMode oldMode, GameMode newMode) {
        this.type = type;
        this.target = target;
        this.moderator = moderator;
        this.startDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.oldMode = oldMode;
        this.newMode = newMode;
    }

    private Integer getMode(GameMode mode) {
        return switch (mode) {
            case SURVIVAL -> 0;
            case CREATIVE -> 1;
            case ADVENTURE -> 2;
            case SPECTATOR -> 3;
        };
    }

    public void send() {
        Log.super.sendCommonLog(type, target, moderator, startDate, getMode(oldMode), getMode(newMode));
    }
}
