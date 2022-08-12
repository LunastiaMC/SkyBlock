package fr.lunastia.skyblock.core.session.server;

public enum EnumLogs {
    PLAYER_JOIN("Le joueur viens de rejoindre le serveur"),
    PLAYER_QUIT("Le joueur viens de quitter le serveur"),
    PLAYER_KICKED("Le joueur viens d'être expulsé"),
    PLAYER_BANNED("Le joueur viens d'être banni"),
    PLAYER_IP_BANNED("Le joueur viens d'être banni (IP"),
    PLAYER_UNBANNED("Le joueur viens d'être débanni"),
    PLAYER_IP_UNBANNED("Le joueur viens d'être débanni (IP)"),
    PLAYER_MUTED("La parole du joueur viens d'être mis en sourdine"),
    PLAYER_UNMUTED("La parole du joueur viens d'être rétablie par la force"),
    PLAYER_FREEZED("Le joueur viens d'être gelé(e)"),
    PLAYER_UNFREEZED("Le joueur viens d'être dégelé(e)"),
    PLAYER_CHANGE_HAT("Le joueur viens de changer de chapeau"),
    PLAYER_GAMEMODE_CHANGED_BY_FORCE("Le mode de jeu du joueur viens d'être mis à jour par force"),
    PLAYER_CHANGE_GAMEMODE("Le mode de jeu du joueur viens d'être mis à jour"),
    PLAYER_RANK_UPDATED("Le grade du joueur viens d'être mis à jour"),
    PLAYER_MUTE_EXPIRED("La parole du joueur viens d'être rétablie"),
    PLAYER_TELEPORT("Joueur viens d'être téléporté"),
    PLAYER_TELEPORTED_BY_FORCE("Joueur viens d'être téléporté par force"),
    PLAYER_KIT_USE("Équipement utilisé"),
    KIT_ADDED("Équipement ajouté"),
    KIT_REMOVED("Équipement supprimé"),
    KIT_UPDATED("Équipement mis à jour"),
    ISLAND_MEMBER_ADDED("Membre d'île arrivé"),
    ISLAND_MEMBER_LEAVED("Membre d'île parti(e)"),
    ISLAND_MEMBER_KICKED("Membre de l'île expulsé(e)"),
    ISLAND_BANK_DEPOSIT("Dépôt d'argent sur banque d'île"),
    ISLAND_BANK_WITHDRAW("Retrait d'argent sur banque d'île"),
    ISLAND_BANK_DEPOSIT_FAILED("Dépôt d'argent sur banque d'île échoué"),
    ISLAND_BANK_WITHDRAW_FAILED("Retrait d'argent sur banque d'île échoué"),
    ISLAND_VISITOR_ADDED("Nouveau visiteur sur l'île"),
    ISLAND_VISITOR_REMOVED("Visiteur parti(e) de l'île"),
    ISLAND_VISITOR_KICKED("Visiteur d'île expulsé"),
    ISLAND_VISITOR_BANNED("Visiteur d'île banni(e)"),
    ISLAND_VISITOR_UNBANNED("Visiteur d'île débanni(e)"),
    ISLAND_WEATHER_CHANGED("Changement du temps de l'île"),
    ISLAND_TIME_CHANGED("Changement de l'heure de l'île");

    private final String itemTitle;

    EnumLogs(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemTitle() {
        return itemTitle;
    }
}
