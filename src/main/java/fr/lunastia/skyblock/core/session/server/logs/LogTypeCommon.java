package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTypeCommon implements Log {
    private final EnumLogs type;
    private final Player target;
    private String startAt;
    private String hat;

    public LogTypeCommon(EnumLogs type, Player target) {
        this.type = type;
        this.target = target;
        this.startAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.hat = null;
    }

    public LogTypeCommon(EnumLogs type, Player target, String hat) {
        this.type = type;
        this.target = target;
        this.startAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.hat = hat;
    }

    public void send() {
        Log.super.sendCommonLog(type, target, startAt, hat);
    }
}
