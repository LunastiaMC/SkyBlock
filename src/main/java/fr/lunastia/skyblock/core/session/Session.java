package fr.lunastia.skyblock.core.session;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.manager.RankManager;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private final Player player;
    private final Rank rank;

    public Session(Player player, ResultSet rs) throws SQLException {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rs.getInt("rank"));
        this.rank.applyPermissions(player);
    }

    public Session(Player player, Integer rank) {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rank);
        this.rank.applyPermissions(player);
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }
}
