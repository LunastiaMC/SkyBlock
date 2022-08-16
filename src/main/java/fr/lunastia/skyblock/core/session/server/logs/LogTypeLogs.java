package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTypeLogs implements Log {
    private final EnumLogs type;
    private final Player player;
    private String openedPlayer;
    private final String startAt;

    public LogTypeLogs(EnumLogs type, Player player, String openedPlayer) {
        this.type = type;
        this.player = player;
        this.openedPlayer = openedPlayer;
        this.startAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public void setTarget(String target) {
        this.openedPlayer = target;
    }

    public void send() {
        Log.super.sendLogs(type, player, startAt,openedPlayer,true);
    }
}
