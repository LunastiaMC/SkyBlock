package fr.lunastia.skyblock.core.session.server;

import org.bukkit.inventory.Inventory;

public record Kit(String identifier, String displayName, Inventory inventory) {
    // TODO: Faire une fonction pour distribuer les objets de inventory
    //       Si il a la main de gauche vide par exemple alors sa lui applique l'objet de deuxième main de inventory
    //       Sinon, sa lui met dans son inventaire
    //       Et ainsi de suite pareil pour l'armure
    //       Avant de donner le kit vérifier si il a de la place, si il en à pas on drop a terre
    //       En lui notifiant qu'on a jeter a terre ces objets
}
