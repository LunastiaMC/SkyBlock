package fr.lunastia.skyblock.core.gui.island;

import fr.lunastia.skyblock.core.gui.GUI;
import fr.lunastia.skyblock.core.manager.Manager;
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
        inventory.setItem(0, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("53343"), "&#73da3cF&#70db41o&#6cdc46r&#69dd4cê&#65dd51t &#62de56d&#5fdf5bo&#5be060r&#58e165m&#54e26ba&#51e270n&#4de375t&#4ae47ae"));
        inventory.setItem(1, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("52966"), "&#da4040P&#db4343r&#db4646o&#dc4848f&#dc4b4bo&#dd4e4en&#de5151d&#de5353e&#df5656u&#df5959r &#e05c5ca&#e05e5eb&#e16161y&#e26464s&#e26767s&#e36969a&#e36c6cl&#e46f6fe"));
        inventory.setItem(2, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("51906"), "&#af5848M&#b15949a&#b35a4ar&#b55b4ba&#b75c4ci&#ba5d4cq&#bc5e4du&#be5f4ea&#c0604fg&#c26250e&#c46351s &#c66452v&#c86553i&#cb6653v&#cd6754a&#cf6855n&#d16956t&#d36a57s"));
        inventory.setItem(3, ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead("12822"), "&#7455afD&#7555b0i&#7655b1m&#7754b2e&#7954b4n&#7a54b5s&#7b54b6i&#7c54b7o&#7d54b8n &#7e53b9s&#7f53bai&#8053bbn&#8253bdi&#8353bes&#8452bft&#8552c0r&#8652c1e"));
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
