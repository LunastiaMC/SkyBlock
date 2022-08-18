package fr.lunastia.skyblock.core.gui.island;

import fr.lunastia.skyblock.core.gui.GUI;
import fr.lunastia.skyblock.core.island.types.Islands;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.session.Island;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import fr.lunastia.skyblock.core.utils.colors.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
        for (Island island : Islands.getIslands()) {
            inventory.setItem(i, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(island.getDisplayName())), "ccc"));
            i++;
        }

        inventory.setItem(8, getDifficulty());
    }

    @Override
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {
        switch (slot) {
            case 8 -> {
                switch (arguments.get(0)) {
                    case "easy" -> arguments.put(0, "medium");
                    case "medium" -> arguments.put(0, "hard");
                    case "hard" -> arguments.put(0, "easy");
                }

                inventory.setItem(8, getDifficulty());
            }
        }
    }

    public ItemStack getDifficulty() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorUtils.colorize("&#68fb72- " + (arguments.get(0).equals("easy") ? "§n" : "") + "&#60f36fF&#59eb6ba&#51e368c&#49db65i&#42d361l&#3acb5ee"));
        lore.add(ColorUtils.colorize("&#fbb04c- " + (arguments.get(0).equals("medium") ? "§n" : "") + "&#f3ab48M&#eca745o&#e4a241y&#dd9d3de&#d59839n&#ce9436n&#c68f32e"));
        lore.add(ColorUtils.colorize("&#fb5454- " + (arguments.get(0).equals("hard") ? "§n" : "") + "&#f85050D&#f44c4ci&#f14848f&#ee4444f&#ea4141i&#e73d3dc&#e43939i&#e03535l&#dd3131e"));
        lore.add(" ");
        switch (arguments.get(0)){
            case "easy" -> {
                lore.add(ColorUtils.colorize("&#60f36fVous gardez votre île quand vous mourrez"));
                lore.add(" ");
                lore.add(" ");
                lore.add(ColorUtils.colorize("§7Possibilité de jouer à plusieurs: §a§l✔"));
            }
            case "medium" -> {
                lore.add(ColorUtils.colorize("&#f3ab48Vous gardez votre île quand vous mourrez"));
                lore.add(ColorUtils.colorize("&#f3ab48mais vous perdez 20% de l'argent que vous"));
                lore.add(ColorUtils.colorize("&#f3ab48avez sur vous lors de votre mort"));
                lore.add(ColorUtils.colorize("&#f3ab48- Vous aurez 5 minutes pour revenir à votre"));
                lore.add(ColorUtils.colorize("&#f3ab48point de mort pour récupérer les pièces au sol"));
                lore.add(" ");
                lore.add(" ");
                lore.add(ColorUtils.colorize("§7Possibilité de jouer à plusieurs: §a§l✔"));
            }
            case "hard" ->{
                lore.add(ColorUtils.colorize("&#f85050Lors ce que vous mourrez, vous perdez votre île"));
                lore.add(ColorUtils.colorize("&#f85050Cela est valable seulement si la mort"));
                lore.add(ColorUtils.colorize("&#f85050est infligée par un joueur, ou une entité"));
                lore.add(ColorUtils.colorize("&#f85050Si vous mourrez par un autre moyen, vous"));
                lore.add(ColorUtils.colorize("&#f85050perdez §n20%§r &#f85050D"+"de l'argent que vous avez §nsur vous"));
                lore.add(" ");
                lore.add(" ");
                lore.add(ColorUtils.colorize("§7Possibilité de jouer à plusieurs: §c§l✘"));
            }
        }
        lore.add(ColorUtils.colorize("§eClique gauche pour changer la difficulté"));
        String headId = "";
        switch (arguments.get(0)) {
            case "easy" -> headId = "52461";
            case "medium" -> headId = "52469";
            case "hard" -> headId = "52468";
        }
        return ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead(headId), "§fDifficulté", lore);
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
