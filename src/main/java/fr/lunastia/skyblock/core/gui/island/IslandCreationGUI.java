package fr.lunastia.skyblock.core.gui.island;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.gui.GUI;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Island;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class IslandCreationGUI implements GUI {
    private HashMap<Integer, String> arguments;

    @Override
    public String getName() {
        return "Création d'une île";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void getContents(Player player, Inventory inventory) throws SQLException {
        int i = 0;
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(Core.getInstance().getIslandConfig());
        final ConfigurationSection section = config.getConfigurationSection("islands");
        assert section != null;
        for (String string : section.getKeys(false)) {
            ArrayList<String> lore = new ArrayList<>();
            Objects.requireNonNull(section.getList(string + ".lore")).forEach(s -> lore.add(ColorUtils.colorize((String) s)));
            inventory.setItem(i, ItemUtils.customizedItem(
                    Manager.getHeadDatabaseAPI().getItemHead(section.getString(string + ".headId")),
                    ColorUtils.colorize(section.getString(string + ".display_name")),
                    lore
            ));
            i++;
        }

        inventory.setItem(8, getDifficulty());
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {


        if (slot == 8) {
            if (!clickType.isLeftClick()) return;
            switch (arguments.get(0)) {
                case "easy" -> arguments.put(0, "medium");
                case "medium" -> arguments.put(0, "hard");
                case "hard" -> arguments.put(0, "easy");
            }

            inventory.setItem(8, getDifficulty());
        }
    }

    public ItemStack getDifficulty() {
        ArrayList<String> lore = new ArrayList<>();
        switch (arguments.get(0)){
            case "easy" -> {
                lore.add(ColorUtils.colorize("&#60f36fL'aventure se déroulera avec beacoup moins"));
                lore.add(ColorUtils.colorize("&#60f36fde stress que dans les autres difficultés"));
                lore.add(ColorUtils.colorize("&#60f36fcar lors ce que vous mourrez, vous garderez"));
                lore.add(ColorUtils.colorize("&#60f36ftout votre argent, équipement ou objets"));
            }
            case "medium" -> {
                lore.add(ColorUtils.colorize("&#f3ab48Vous gardez votre île quand vous mourrez"));
                lore.add(ColorUtils.colorize("&#f3ab48mais vous perdez 20% de l'argent que vous"));
                lore.add(ColorUtils.colorize("&#f3ab48avez sur vous lors de votre mort"));
                lore.add(ColorUtils.colorize("&#f3ab48- Vous aurez 5 minutes pour revenir à votre"));
                lore.add(ColorUtils.colorize("&#f3ab48point de mort pour récupérer les pièces au sol"));
            }
            case "hard" ->{
                lore.add(ColorUtils.colorize("&#f85050Lors ce que vous mourrez, vous perdez votre île"));
                lore.add(ColorUtils.colorize("&#f85050Cela est valable seulement si la mort"));
                lore.add(ColorUtils.colorize("&#f85050est infligée par un joueur, ou une entité"));
                lore.add(ColorUtils.colorize("&#f85050Si vous mourrez par un autre moyen, vous"));
                lore.add(ColorUtils.colorize("&#f85050perdez §n20%§r &#f85050D"+"de l'argent que vous avez §nsur vous"));
            }
        }
        lore.add(" ");
        lore.add(ColorUtils.colorize("§eMulti-joueurs possible: " + switch (arguments.get(0)){
            case "hard" -> "§c§l✘";
            default -> "§a§l✔";
        }));
        lore.add(ColorUtils.colorize("§eClique gauche pour changer la difficulté"));
        return ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead(switch (arguments.get(0)) {
            case "medium" -> "52469";
            case "hard" -> "52468";
            default -> "52461";
        }), ColorUtils.colorize(switch (arguments.get(0)) {
            case "medium" -> "&#f3ab48Moyennement difficile";
            case "hard" -> "&#f85050Difficile";
            default -> "&#60f36fFacile";
        }), lore);
    }

    @Override
    public void onClose(Player player, Inventory inventory) {

    }

    @Override
    public void onOpen(Player player, Inventory inventory) {

    }

    @Override
    public boolean clickCancelled() {
        return true;
    }

    @Override
    public InventoryType getInventoryType() {
        return null;
    }

    @Override
    public void setArgument(HashMap<Integer, String> argument) {
        this.arguments = argument;
    }
}
