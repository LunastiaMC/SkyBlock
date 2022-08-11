package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class LogTypeModeration implements Log {
    private final EnumLogs type;
    private final Player target;
    private final Player moderator;
    private final String reason;
    private Timestamp expireAt;

    public LogTypeModeration(EnumLogs type, Player target, Player moderator, String reason) {
        this.type = type;
        this.target = target;
        this.moderator = moderator;
        this.reason = reason;
        this.expireAt = new Timestamp(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
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

    public Timestamp getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }
}
