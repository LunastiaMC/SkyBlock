package fr.lunastia.skyblock.core.session;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Session {

    private final Player player;
    private final String[] permissions;
    private Rank rank;
    private Long money = 0L;
    private boolean isVanished = false;
    private boolean isFreezed = false;
    private String island = null;
    private Hats hat = null;
    private boolean wasKicked = false;

    public Session(Player player, ResultSet rs) throws SQLException {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rs.getInt("rank"));
        this.money = rs.getLong("money");
        this.rank.applyPermissions(this);
        this.permissions = rs.getString("permissions").split(";");
        Rank.applyPermissions(player, rs.getString("permissions").split(";"));
        setFreezed(rs.getBoolean("freezed"), rs.getBoolean("freezed"));
        setVanished(rs.getBoolean("vanished"), rs.getBoolean("vanished"));

        Hats hat = Hats.fromUUID(rs.getString("hat"));
        if (hat != null) setHat(hat, hat.getItemStack());
    }

    public Session(Player player, Integer rank, Long money, String[] permissions) {
        this.player = player;
        this.rank = Manager.getRankManager().getRank(rank);
        this.money = money;
        this.rank.applyPermissions(this);
        this.permissions = permissions;
        Rank.applyPermissions(player, permissions);
        setFreezed(isFreezed, isFreezed);
        setVanished(isVanished, isVanished);

        if (hat == null) setHat(null);
        else setHat(hat, hat.getItemStack());
        if (island == null) this.island = "";
        else this.island = island;

        NametagEdit.getApi().setPrefix(player, ColorUtils.colorize(this.getRank().nametagName()) + "§7");
        player.setPlayerListName(ColorUtils.colorize(this.getRank().nametagName()) + player.getName());
    }

    public Player getPlayer() {
        return player;
    }

    //  /$$$$$$$   /$$$$$$  /$$   /$$ /$$   /$$
    // | $$__  $$ /$$__  $$| $$$ | $$| $$  /$$/
    // | $$  \ $$| $$  \ $$| $$$$| $$| $$ /$$/
    // | $$$$$$$/| $$$$$$$$| $$ $$ $$| $$$$$/
    // | $$__  $$| $$__  $$| $$  $$$$| $$  $$
    // | $$  \ $$| $$  | $$| $$\  $$$| $$\  $$
    // | $$  | $$| $$  | $$| $$ \  $$| $$ \  $$
    // |__/  |__/|__/  |__/|__/  \__/|__/  \__/

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
        rank.applyPermissions(this);
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

    // /$$   /$$ /$$$$$$$$ /$$$$$$ /$$        /$$$$$$
    // | $$  | $$|__  $$__/|_  $$_/| $$       /$$__  $$
    // | $$  | $$   | $$     | $$  | $$      | $$  \__/
    // | $$  | $$   | $$     | $$  | $$      |  $$$$$$
    // | $$  | $$   | $$     | $$  | $$       \____  $$
    // | $$  | $$   | $$     | $$  | $$       /$$  \ $$
    // |  $$$$$$/   | $$    /$$$$$$| $$$$$$$$|  $$$$$$/
    // \______/    |__/   |______/|________/ \______/

    public boolean isFreezed() {
        return isFreezed;
    }

    public void setFreezed(boolean freezed, boolean messages) {
        if (freezed) {
            isFreezed = true;
            this.getPlayer().setFreezeTicks((800 * 999999999));
            if (messages) ColorUtils.sendMessage(this.getPlayer(), "Vous venez d'être immobilisé", Colors.PREFIX);
            return;
        }

        isFreezed = false;
        if (messages) {
            ColorUtils.sendMessage(this.getPlayer(), "Vous vous pouvez à nouveau vous dégourdir les pieds.", Colors.PREFIX);
        }
        this.getPlayer().setFreezeTicks(1);
    }

    public boolean isVanished() {
        return isVanished;
    }

    public void setVanished(boolean vanished, boolean messages) {
        if (vanished) {
            isVanished = true;
            if (messages) ColorUtils.sendMessage(player, "Vous venez de vous cacher !", Colors.PREFIX);
            this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 999999999, 1, false, false, false));
            return;
        }

        isVanished = false;
        if (messages) {
            ColorUtils.sendMessage(player, "Vous venez de vous révéler !", Colors.PREFIX);
        }
        this.getPlayer().getActivePotionEffects().forEach(potionEffect -> this.getPlayer().removePotionEffect(potionEffect.getType()));
    }

    public boolean wasKicked() {
        return wasKicked;
    }

    public void setKicked() {
        wasKicked = true;
    }

    //  /$$$$$$  /$$$$$$  /$$        /$$$$$$  /$$   /$$ /$$$$$$$
    // |_  $$_/ /$$__  $$| $$       /$$__  $$| $$$ | $$| $$__  $$
    //   | $$  | $$  \__/| $$      | $$  \ $$| $$$$| $$| $$  \ $$
    //   | $$  |  $$$$$$ | $$      | $$$$$$$$| $$ $$ $$| $$  | $$
    //   | $$   \____  $$| $$      | $$__  $$| $$  $$$$| $$  | $$
    //   | $$   /$$  \ $$| $$      | $$  | $$| $$\  $$$| $$  | $$
    //  /$$$$$$|  $$$$$$/| $$$$$$$$| $$  | $$| $$ \  $$| $$$$$$$/
    // |______/ \______/ |________/|__/  |__/|__/  \__/|_______/

    public Island getIsland() {
        return Manager.getIslandManager().getIsland(island);
    }

    public String getIslandUUID() {
        return island;
    }

    // /$$   /$$  /$$$$$$  /$$$$$$$$ /$$$$$$
    // | $$  | $$ /$$__  $$|__  $$__//$$__  $$
    // | $$  | $$| $$  \ $$   | $$  | $$  \__/
    // | $$$$$$$$| $$$$$$$$   | $$  |  $$$$$$
    // | $$__  $$| $$__  $$   | $$   \____  $$
    // | $$  | $$| $$  | $$   | $$   /$$  \ $$
    // | $$  | $$| $$  | $$   | $$  |  $$$$$$/
    // |__/  |__/|__/  |__/   |__/   \______/


    public void setHat(Hats hat, ItemStack itemStack) {
        this.player.getInventory().setHelmet(itemStack);
        this.hat = hat;
    }

    public Hats getHat() {
        return hat;
    }

    public void setHat(Hats hat) {
        if (hat == null) {
            this.player.getInventory().setHelmet(null);
            this.hat = null;
            return;
        }

        this.hat = hat;
    }

    public boolean hasHat() {
        return hat != null;
    }
}
