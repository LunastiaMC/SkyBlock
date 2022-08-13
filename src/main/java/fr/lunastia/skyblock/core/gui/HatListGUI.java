package fr.lunastia.skyblock.core.gui;

import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Hats;
import fr.lunastia.skyblock.core.session.Session;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import fr.lunastia.skyblock.core.utils.colors.Colors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class HatListGUI implements GUI {
    @Override
    public String getName() {
        return "Liste des chapeaux disponibles";
    }

    @Override
    public int getSize() {
        return 18;
    }

    @Override
    public void getContents(Player player, Inventory inventory) {
        // Foreach all hats in Hats.java
        Hats[] hats = Hats.getHats();
        int i = 0;
        for (Hats hat : hats) {
            if (i >= getSize()) return;
            ArrayList<String> lore = new ArrayList<>();
            if (!player.hasPermission(hat.getPermission())) {
                lore.add("§c§l✘ §r§cVous n'avez pas débloqué ce chapeau");
                continue;
            } else {
                lore.add("§a§l✔ §r§aVous avez débloqué ce chapeau");
            }
            i++;

            // Get item
            ItemStack item = new ItemStack(Objects.requireNonNull(Material.matchMaterial(hat.getIdentifier())));
            inventory.setItem(i, ItemUtils.customizedItem(item, "§d" + hat.getDisplayName(), lore));
        }

        Session session = Manager.getSessionManager().getSession(player);
        if (session == null) {
            return;
        }

        if (session.hasHat()) {
            inventory.setItem(18, ItemUtils.customizedItem(new ItemStack(Material.BARRIER), "§cRetirer le chapeau", new ArrayList<>()));
        }
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) {
            Hats hat = Hats.getHat(itemStack.getType());
            if (hat == null) {
                return;
            }

            if (!player.hasPermission(hat.getPermission())) {
                ColorUtils.sendMessage(player, "Vous n'avez pas débloqué ce chapeau", Colors.HAT, true);
                return;
            }

            Session session = Manager.getSessionManager().getSession(player);
            if (session == null) {
                return;
            }

            if (session.getHat() != null) {
                ColorUtils.sendMessage(player, "Vous avez déjà un chapeau, veuillez le retirer pour en choisir un autre", Colors.HAT, true);
                return;
            }

            session.setHat(hat, itemStack);
            ColorUtils.sendMessage(player, "Vous venez d'appliquer le chapeau" + hat.getDisplayName(), Colors.HAT);
        }

        @Override
        public void onClose (Player player, Inventory inventory){
            return;
        }

        @Override
        public void onOpen (Player player, Inventory inventory){
            return;
        }

        @Override
        public boolean clickCancelled () {
            return true;
        }

        @Override
        public InventoryType getInventoryType () {
            return null;
        }
    }
