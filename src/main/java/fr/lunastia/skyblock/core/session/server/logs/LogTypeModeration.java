package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

public class LogTypeModeration implements Log {
    private final EnumLogs type;
    private final Player target;
    private final Player moderator;
    private final String reason;
    private String expireAt;

    public LogTypeModeration(EnumLogs type, Player target, Player moderator, String reason) {
        this.type = type;
        this.target = target;
        this.moderator = moderator;
        this.reason = reason;
        this.expireAt = null;
    }

    public EnumLogs getType() {
        return type;
    }

    public Player getTarget() {
        return target;
    }

    public Player getModerator() {
        return moderator;
    }

    public String getReason() {
        return reason;
    }

    public String getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(String expireAt) {
        this.expireAt = expireAt;
    }

    public void send() {
        Log.super.sendModLog(type, target, moderator, reason, expireAt);
    }
}
