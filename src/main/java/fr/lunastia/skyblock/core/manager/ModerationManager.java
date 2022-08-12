package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.session.server.EnumLogs;
import fr.lunastia.skyblock.core.session.server.logs.LogTypeModeration;
import fr.lunastia.skyblock.core.utils.TextUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ModerationManager {

    //  /$$$$$$$   /$$$$$$  /$$   /$$
    // | $$__  $$ /$$__  $$| $$$ | $$
    // | $$  \ $$| $$  \ $$| $$$$| $$
    // | $$$$$$$ | $$$$$$$$| $$ $$ $$
    // | $$__  $$| $$__  $$| $$  $$$$
    // | $$  \ $$| $$  | $$| $$\  $$$
    // | $$$$$$$/| $$  | $$| $$ \  $$
    // |_______/ |__/  |__/|__/  \__/

    public void addBan(Player player, Player moderator, String expire, String reason) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("INSERT INTO bans (uuid, startDate, endDate, reason) VALUES (?,?,?,?)");
        String start = getMinimizedDate(new Date());
        String expireMMZ = getMinimizedDate(expire);
        statement.setString(1, player.getUniqueId().toString());
        statement.setString(2, start);
        statement.setString(3, expireMMZ);
        statement.setString(4, reason);
        statement.execute();

        LogTypeModeration log = new LogTypeModeration(EnumLogs.PLAYER_BANNED, player, moderator, reason);
        log.setExpireAt(expireMMZ);
        log.setStartAt(start);
        log.send();
    }

    public void delBan(String target) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();
        final PreparedStatement statement = connection.prepareStatement("DELETE FROM bans WHERE uuid = ?");
        statement.setString(1, target);
        statement.execute();
    }

    public boolean isBanned(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM bans WHERE uuid = ?");
        statementFinder.setString(1, player.getUniqueId().toString());
        final ResultSet resultSet = statementFinder.executeQuery();

        if (resultSet.next()) {
            long days = TextUtils.getDifferenceDays(new Date(resultSet.getString("startDate")), new Date(resultSet.getString("endDate")));
            if (days <= (long) 0) {
                Manager.getModerationManager().delBan(player.getUniqueId().toString());
                Manager.getSessionManager().loadSession(player);
                return false;
            } else {
                String reason = resultSet.getString("reason");
                if (reason == null) reason = "Aucune raison";

                String[] message = new String[]{
                        ColorUtils.colorize(Colors.MOD_RED.color() + "§lVous êtes banni(e) du serveur\n\n"),
                        ColorUtils.colorize("§7§l➤§r§7 Pour la raison suivante: " + Colors.MOD_RED.color() + reason) + "\n",
                        ColorUtils.colorize("§7§l➤§r§7 Votre banissement expirera dans " + Colors.MOD_RED.color() + days + " jour(s)") + "\n",
                        "\n",
                        ColorUtils.colorize("§r§7Si vous pensez qu'il y a une erreur") + "\n",
                        ColorUtils.colorize("§r§7vous pouvez ouvrir un ticket sur le discord") + "\n",
                        ColorUtils.colorize(Colors.DISCORD_COLOR.color() + "discord.gg/F9aQyQZxQr")
                };
                player.kickPlayer(String.join("", message));
                return true;
            }
        }
        return resultSet.next();
    }

    public ResultSet getBanInfo(Player player) throws SQLException {
        Connection connection = Manager.getDatabaseManager().getDatabase().getConnection();

        final PreparedStatement statementFinder = connection.prepareStatement("SELECT * FROM bans WHERE uuid = ?");
        statementFinder.setString(1, player.getUniqueId().toString());

        return statementFinder.executeQuery();
    }

    //  /$$      /$$ /$$   /$$ /$$$$$$$$ /$$$$$$$$
    // | $$$    /$$$| $$  | $$|__  $$__/| $$_____/
    // | $$$$  /$$$$| $$  | $$   | $$   | $$
    // | $$ $$/$$ $$| $$  | $$   | $$   | $$$$$
    // | $$  $$$| $$| $$  | $$   | $$   | $$__/
    // | $$\  $ | $$| $$  | $$   | $$   | $$
    // | $$ \/  | $$|  $$$$$$/   | $$   | $$$$$$$$
    // |__/     |__/ \______/    |__/   |________/
}
