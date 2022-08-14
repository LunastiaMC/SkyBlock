package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.commands.moderation.GameModeCommand;
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
    PLAYER_VANISHED("", "Disparition", Colors.COMMON, 20388),
    PLAYER_UNVANISHED("", "Réapparition", Colors.COMMON, 20388),
    // TODO: Heal, Feed, Fly, Invsee (& other), Report
    PLAYER_CHANGE_HAT("", "Changement de chapeau", Colors.COMMON, 55055),
    PLAYER_CHANGE_HAT_TO_AIR("", "Chapeau retiré de la tête", Colors.COMMON, 55055),
    PLAYER_CHANGE_GAMEMODE("", "Changement de mode de jeu", Colors.WARNING, 8765),
    PLAYER_GAMEMODE_CHANGED_BY_FORCE("", "Changement de mode de jeu par force", Colors.WARNING, 8765),
    PLAYER_RANK_UPDATED("", "Changement de grade", Colors.BAD, 33946),
    PLAYER_MONEY_UPDATED("", "Changement de solde", Colors.WARNING, 33946),
    MONEY_PAY("Payement à un joueur"),
    MONEY_RECEIVE("Payement reçu"),
    MONEY_ADDED("Argent ajouté"),
    MONEY_REMOVED("Argent retiré"),
    MONEY_SET("Argent défini"),
    MONEY_DEPOSIT_ISLAND_BANK("Dépôt d'argent sur la banque d'île"),
    MONEY_WITHDRAW_ISLAND_BANK("Retrait d'argent de la banque d'île"),

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
    private final String moneyType;

    EnumLogs(String itemDescription, String itemTitle, Colors color, Integer itemHead) {
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemColor = color;
        this.itemHead = itemHead;
        this.moneyType = null;
    }

    EnumLogs(String moneyType) {
        this.itemTitle = null;
        this.itemDescription = null;
        this.itemColor = null;
        this.itemHead = null;

        this.moneyType = moneyType;
    }

    public String getMoneyType() {
        return moneyType;
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
            case PLAYER_MONEY_UPDATED -> {
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Transaction: " + log.itemColor.color() + "+" + infos.getString("balance_transaction") + " pièce(s)"));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Ancien solde: " + log.itemColor.color() + infos.getString("balance_old") + " pièce(s)"));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Nouveau solde: " + log.itemColor.color() + infos.getString("balance_new") + " pièce(s)"));
                lore.add(" ");

                EnumLogs type = EnumLogs.valueOf(infos.getString("balance_transaction_type"));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Type de transaction: " + log.itemColor.color() + type.getMoneyType()));
                if (type == MONEY_ADDED || type == MONEY_REMOVED || type == MONEY_SET) {
                    lore.add(ColorUtils.colorize("§l§7➥ §r§7Modérateur: " + log.itemColor.color() + infos.getString("moderator_name")));
                } else if (type == MONEY_DEPOSIT_ISLAND_BANK || type == MONEY_WITHDRAW_ISLAND_BANK) {
                    lore.add(ColorUtils.colorize("§l§7➥ §r§7Île de: " + log.itemColor.color() + "Pas encore disponible"));
                } else if (type == MONEY_PAY || type == MONEY_RECEIVE) {
                    lore.add(ColorUtils.colorize("§l§7➥ §r§7Autre joueur: " + log.itemColor.color() + infos.getString("balance_transaction_target")));
                }
            }
            }
            case PLAYER_GAMEMODE_CHANGED_BY_FORCE -> {
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Ancien mode de jeu: " + log.itemColor.color() + GameModeCommand.getNameGameMode(String.valueOf(infos.getInt("old_gamemode")))));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Nouveau mode de jeu: " + log.itemColor.color() + GameModeCommand.getNameGameMode(String.valueOf(infos.getInt("new_gamemode")))));
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Modérateur: " + log.itemColor.color() + infos.getString("moderator_name")));
            }
            case PLAYER_CHANGE_HAT -> {
                lore.add(ColorUtils.colorize("§l§7➥ §r§7Chapeau: §r" + log.itemColor.color() + infos.getString("hat_name")));
            }
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
