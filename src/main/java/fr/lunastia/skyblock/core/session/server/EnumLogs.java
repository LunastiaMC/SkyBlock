package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.utils.colors.Colors;

import java.util.ArrayList;

public enum EnumLogs {
    PLAYER_KIT_USE("", "Utilisation d'un équipement", Colors.COMMON,51459),

    PLAYER_OPEN_INVENTORY("", "Ouverture d'un inventaire", Colors.COMMON,227),

    KIT_ADDED("", "Ajout d'un équipement", Colors.WARNING,51459),
    KIT_REMOVED("", "Suppression d'un équipement", Colors.WARNING,51459),
    KIT_UPDATED("", "Mise à jour d'un équipement", Colors.WARNING,51459),
    ISLAND_MEMBER_ADDED("", "Ajout d'un membre d'île", Colors.COMMON,53343),
    ISLAND_MEMBER_LEAVED("", "Suppression d'un membre d'île", Colors.COMMON,53343),
    ISLAND_MEMBER_KICKED("", "Expulsion d'un membre d'île", Colors.COMMON,53343),
    ISLAND_BANK_DEPOSIT("", "Dépôt d'argent sur banque d'île", Colors.COMMON,53343),
    ISLAND_BANK_WITHDRAW("", "Retrait d'argent sur banque d'île", Colors.COMMON,53343),
    ISLAND_BANK_DEPOSIT_FAILED("", "Dépôt d'argent sur banque d'île échoué", Colors.COMMON,53343),
    ISLAND_BANK_WITHDRAW_FAILED("", "Retrait d'argent sur banque d'île échoué", Colors.COMMON,53343),
    ISLAND_VISITOR_ADDED("", "Ajout d'un visiteur sur l'île", Colors.COMMON,53343),
    ISLAND_VISITOR_REMOVED("", "Suppression d'un visiteur sur l'île", Colors.COMMON,53343),
    ISLAND_VISITOR_KICKED("", "Expulsion d'un visiteur sur l'île", Colors.COMMON,53343),
    ISLAND_VISITOR_BANNED("", "Bannissement d'un visiteur sur l'île", Colors.COMMON,53343),
    ISLAND_VISITOR_UNBANNED("", "Débannissement d'un visiteur sur l'île", Colors.COMMON,53343),
    ISLAND_WEATHER_CHANGED("", "Changement du temps de l'île", Colors.COMMON,34467),
    ISLAND_TIME_CHANGED("", "Changement de l'heure de l'île", Colors.COMMON,8369);

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

    public ArrayList<String> getItemLore(EnumLogs log) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add("§l§7➥ §r" + log.getItemDescription());
        lore.add(" ");
        if (log == EnumLogs.PLAYER_BANNED) {

        }
        return lore;
    }
}
