package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTypeEconomy implements Log {
    private final EnumLogs type;
    private final Player target;
    private final Integer oldBalance;
    private final Integer newBalance;
    private final Integer amount;
    private final String startAt;
    private Player moderator;
    private EnumLogs transactionType;
    private String transactionTarget;

    public LogTypeEconomy(EnumLogs type, Player target, Integer oldBalance, Integer newBalance, Integer amount) {
        this.type = type;
        this.target = target;
        this.moderator = null;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.amount = amount;
        this.transactionType = null;
        this.transactionTarget = null;
        this.startAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public void setTransactionType(EnumLogs type) {
        this.transactionType = type;
    }

    public void setTransactionTarget(String target) {
        this.transactionTarget = target;
    }

    public void setModerator(Player moderator) {
        this.moderator = moderator;
    }

    public void send() {
        Log.super.sendEconomyLog(type, target, moderator, oldBalance, newBalance, amount, transactionTarget, transactionType, startAt);
    }
}
