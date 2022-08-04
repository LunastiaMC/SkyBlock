package fr.lunastia.skyblock.core.gui;
public class RepairGUI implements GUIBuilder {
    public String getName() {
        return "Réparation";
    }
    public int getSize() {
        return 45;
    }
    public void getContents(Player player, Inventory inventory) {
    }
    public void onClick(Player player, Inventory inventory, ItemStack itemStack, int slot, ClickType clickType) throws SQLException {
    public void onClose(Player player, Inventory inventory) {
    public void onOpen(Player player, Inventory inventory) {

    }
    public boolean clickCancelled() {
        return true;
    }
}
