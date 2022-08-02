package fr.lunastia.skyblock.core.session;

import com.nametagedit.plugin.NametagEdit;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private final Player player;
    private final Rank rank;
    private final String[] permissions;
    private Long money;

    public Session(Player player, ResultSet rs) throws SQLException {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rs.getInt("rank"));
        this.money = rs.getLong("money");
        this.rank.applyPermissions(player);
        this.permissions = rs.getString("permissions").split(";");
        Rank.applyPermissions(player, rs.getString("permissions").split(";"));

        NametagEdit.getApi().setPrefix(player, ColorUtil.colorize(this.getRank().nametagName()) + "§7");
        player.setPlayerListName(ColorUtil.colorize(this.getRank().nametagName()) + player.getName());
    }

    public Session(Player player, Integer rank, Long money, String[] permissions) {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rank);
        this.money = money;
        this.rank.applyPermissions(player);
        this.permissions = permissions;
        Rank.applyPermissions(player, permissions);
    }

    public Player getPlayer() {
        return player;
    }

    public Rank getRank() {
        return rank;
    }

    public String getPermissions() {
        return String.join(";", permissions);
    }

    // /$$$$$$$$  /$$$$$$   /$$$$$$  /$$   /$$  /$$$$$$  /$$      /$$ /$$     /$$
    // | $$_____/ /$$__  $$ /$$__  $$| $$$ | $$ /$$__  $$| $$$    /$$$|  $$   /$$/
    // | $$      | $$  \__/| $$  \ $$| $$$$| $$| $$  \ $$| $$$$  /$$$$ \  $$ /$$/
    // | $$$$$   | $$      | $$  | $$| $$ $$ $$| $$  | $$| $$ $$/$$ $$  \  $$$$/
    // | $$__/   | $$      | $$  | $$| $$  $$$$| $$  | $$| $$  $$$| $$   \  $$/
    // | $$      | $$    $$| $$  | $$| $$\  $$$| $$  | $$| $$\  $ | $$    | $$
    // | $$$$$$$$|  $$$$$$/|  $$$$$$/| $$ \  $$|  $$$$$$/| $$ \/  | $$    | $$
    // |________/ \______/  \______/ |__/  \__/ \______/ |__/     |__/    |__/

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public void addMoney() {
        this.money++;
    }

    public void addMoney(Long money) {
        this.money += money;
    }

    public void reduceMoney() {
        this.money--;
    }

    public void reduceMoney(Long money) {
        this.money -= money;
    }
}
