package fr.lunastia.skyblock.core.session.server;

import fr.lunastia.skyblock.core.utils.colors.Colors;

import java.util.ArrayList;

public enum EnumLogs {
    PLAYER_OPEN_INVENTORY("", "Ouverture d'un inventaire", Colors.COMMON,227),

    private final String itemTitle;

    EnumLogs(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}
