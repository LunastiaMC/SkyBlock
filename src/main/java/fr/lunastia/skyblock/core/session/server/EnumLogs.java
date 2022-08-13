package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.utils.TextUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public enum EnumLogs {
    PLAYER_JOIN("", "Connexion", Colors.COMMON, 8768),
    PLAYER_QUIT("", "Déconnexion", Colors.COMMON, 8774),
    PLAYER_KICKED("", "Expulsion", Colors.WARNING, 48927),
    PLAYER_BANNED("", "Bannissement", Colors.BAD, 48927),
    PLAYER_IP_BANNED("", "Bannissement par IP", Colors.BAD, 48927),
    PLAYER_UNBANNED("", "Débannissement", Colors.BAD, 48927),
    PLAYER_UNBANNED_EXPIRED("", "Bannissement expiré", Colors.BAD, 47475),
    PLAYER_IP_UNBANNED("", "Débannissement par IP", Colors.BAD, 48927),
    PLAYER_IP_UNBANNED_EXPIRED("", "Bannissement par IP expiré", Colors.BAD, 47475),
    PLAYER_MUTED("", "Mise en sourdine", Colors.WARNING, 48927),
    PLAYER_UNMUTED("", "Rétablissement de la parole", Colors.WARNING, 48927),
    PLAYER_UNMUTED_EXPIRED("", "Rétablissement de la parole par temps expiré", Colors.COMMON, 47475),
    PLAYER_FREEZED("", "Gel", Colors.WARNING, 29472),
    PLAYER_UNFREEZED("", "Dégel", Colors.WARNING, 29472),
    PLAYER_CHANGE_HAT("", "Changement de chapeau", Colors.COMMON, 55055),
    PLAYER_CHANGE_HAT_TO_AIR("", "Chapeau retiré de la tête", Colors.COMMON, 55055),
    PLAYER_GAMEMODE_CHANGED_BY_FORCE("", "Changement de mode de jeu par force", Colors.WARNING, 8765),
    PLAYER_CHANGE_GAMEMODE("", "Changement de mode de jeu", Colors.WARNING, 8765),
    PLAYER_RANK_UPDATED("", "Changement de grade", Colors.BAD, 33946),
    PLAYER_TELEPORT("", "Téléportation", Colors.WARNING, 1109),
    PLAYER_TELEPORTED_BY_FORCE("", "Téléportation par force", Colors.WARNING, 1109),
    PLAYER_KIT_USE("", "Utilisation d'un équipement", Colors.COMMON, 51459),
    PLAYER_OPEN_INVENTORY("", "Ouverture d'un inventaire", Colors.COMMON, 227),
    KIT_ADDED("", "Ajout d'un équipement", Colors.WARNING, 51459),
    KIT_REMOVED("", "Suppression d'un équipement", Colors.WARNING, 51459),
    KIT_UPDATED("", "Mise à jour d'un équipement", Colors.WARNING, 51459),
    ISLAND_MEMBER_ADDED("", "Ajout d'un membre d'île", Colors.COMMON, 53343),
    ISLAND_MEMBER_LEAVED("", "Suppression d'un membre d'île", Colors.COMMON, 53343),
    ISLAND_MEMBER_KICKED("", "Expulsion d'un membre d'île", Colors.COMMON, 53343),
    ISLAND_BANK_DEPOSIT("", "Dépôt d'argent sur banque d'île", Colors.COMMON, 53343),
    ISLAND_BANK_WITHDRAW("", "Retrait d'argent sur banque d'île", Colors.COMMON, 53343),
    ISLAND_BANK_DEPOSIT_FAILED("", "Dépôt d'argent sur banque d'île échoué", Colors.COMMON, 53343),
    ISLAND_BANK_WITHDRAW_FAILED("", "Retrait d'argent sur banque d'île échoué", Colors.COMMON, 53343),
    ISLAND_VISITOR_ADDED("", "Ajout d'un visiteur sur l'île", Colors.COMMON, 53343),
    ISLAND_VISITOR_REMOVED("", "Suppression d'un visiteur sur l'île", Colors.COMMON, 53343),
    ISLAND_VISITOR_KICKED("", "Expulsion d'un visiteur sur l'île", Colors.COMMON, 53343),
    ISLAND_VISITOR_BANNED("", "Bannissement d'un visiteur sur l'île", Colors.COMMON, 53343),
    ISLAND_VISITOR_UNBANNED("", "Débannissement d'un visiteur sur l'île", Colors.COMMON, 53343),
    ISLAND_WEATHER_CHANGED("", "Changement du temps de l'île", Colors.COMMON, 34467),
    ISLAND_TIME_CHANGED("", "Changement de l'heure de l'île", Colors.COMMON, 8369);

    private final String itemDescription;
    private final String itemTitle;
    private final Colors itemColor;
    private final Integer itemHead;

    EnumLogs(String itemDescription, String itemTitle, Colors color, Integer itemHead) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemColor = color;
        this.itemHead = itemHead;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Colors getItemColor() {
        return itemColor;
    }

    public Integer getItemHead() {
        return itemHead;
    }

    public ArrayList<String> getItemLore(EnumLogs log, ResultSet infos, Player player) throws SQLException {
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§l§7• §r" + log.getItemDescription());
        lore.add(" ");
        lore.add(ColorUtils.colorize("§l§7➤ §r§7Joueur: §r" + log.itemColor.color() + infos.getString("target_name")));
        lore.add(ColorUtils.colorize("§l§7➥ §r§7Le: §r" + log.itemColor.color() + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(infos.getString("startAt")))));
        lore.add(" ");
        switch (log) {
            case PLAYER_KICKED -> {
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Raison: §r" + log.itemColor.color() + infos.getString("reason")));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Modérateur: §r" + log.itemColor.color() + infos.getString("moderator_name")));
            }
            case PLAYER_BANNED -> {
                isPermanentBan(infos, lore, log);
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Raison: " + log.itemColor.color() + infos.getString("reason")));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Banni par: " + log.itemColor.color() + infos.getString("moderator_name")));
            }
            case PLAYER_IP_BANNED -> {
                if (player.isOp()) {
                    lore.add(ColorUtils.colorize("§l§7➥ §r§7Adresse IP: " + log.itemColor.color() + infos.getString("ip")));
                }
                isPermanentBan(infos, lore, log);
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Raison: " + log.itemColor.color() + infos.getString("reason")));
            }
            case PLAYER_CHANGE_HAT ->
                    lore.add(ColorUtils.colorize("§l§7➥ §r§7Chapeau: §r" + log.itemColor.color() + infos.getString("hat_name")));
        }
        return lore;
    }

    private void isPermanentBan(ResultSet infos, ArrayList<String> lore, EnumLogs log) throws SQLException {
        if (infos.getString("expireAt").equals("perm")) {
            lore.add(ColorUtils.colorize("§l§7➥ §r§7Durée de bannissement: §r" + log.itemColor.color() + "Permanent"));
        } else {
            lore.add(ColorUtils.colorize("§l§7➥ §r§7Durée de bannissement: " + log.itemColor.color() + TextUtils.getDifferenceDays(new Date(infos.getString("startAt")), new Date(infos.getString("expireAt"))) + " jours"));
        }
    }
}
