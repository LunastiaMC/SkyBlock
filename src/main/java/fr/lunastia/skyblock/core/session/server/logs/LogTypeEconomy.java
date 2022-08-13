package fr.lunastia.skyblock.core.session.server.logs;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import org.bukkit.entity.Player;

public class LogTypeEconomy implements Log {
    private final EnumLogs type;
    private final Player target;
    private final Player moderator;
    private final Integer oldBalance;
    private final Integer newBalance;
    private final Integer amount;
    private EnumLogs moneyType;

    public LogTypeEconomy(EnumLogs type, Player target, Integer oldBalance, Integer newBalance, Integer amount) {
        this.type = type;
        this.target = target;
        this.moderator = null;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.amount = amount;
        this.moneyType = null;
    }

    public void send() {
        Log.super.sendEconomyLog(type, target, oldBalance, newBalance, amount);
    }

    public void setMoneyType(EnumLogs type) {
        this.moneyType = type;
    }
}
