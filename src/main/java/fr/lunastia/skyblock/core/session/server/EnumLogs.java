package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.utils.colors.Colors;

import java.util.ArrayList;

public enum EnumLogs {

    PLAYER_OPEN_INVENTORY("", "Ouverture d'un inventaire", Colors.COMMON,227),


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
