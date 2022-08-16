package fr.lunastia.skyblock.core.session;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.manager.Manager;
import fr.lunastia.skyblock.core.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public enum Hats {
    // BLOCKS
    GLASS(UUID.fromString("f31f3518-19ab-499a-a49f-e185cc1b089b"), "glass", "Verre", "skyblock.hat.use", true),
    TINTED_GLASS(UUID.fromString("fbd30c53-b6cc-40be-b96d-415fa6879844"), "tinted_glass", "Verre tetinté", "skyblock.hat.use", true),
    WOOL(UUID.fromString("b1863e8f-7150-4a13-a5bf-436212b216d1"), "white_wool", "Laine blanche", "skyblock.hat.use", true),
    NETHERITE_BLOCK(UUID.fromString("4214efdd-af74-493b-8924-396edb561240"), "netherite_block", "Bloc de Netherite", "skyblock.hat.use", true),
    ANCIEN_DEBRIS(UUID.fromString("e4715e3f-5859-4b21-bafc-d62999076353"), "ancien_debirs", "Bloc d'ancien débris", "skyblock.hat.use", false),
    // HEADS
    CHICK(UUID.fromString("aecf25cf-8b8b-4044-94c1-e51be76c1a8f"), 42983, "Détective Cocotte", "skyblock.hat.use", true),
    ASTRAUNOT(UUID.fromString("0ca0a698-c677-465b-96b1-c58fcd4fc066"), 33514, "Casque d'astronaute", "skyblock.hat.use", true);


    private final UUID uuid;
    private final String displayName;
    private final String permission;
    private Integer head = null;
    private String identifier = null;
    private final boolean active;

    Hats(UUID uuid, String minecraftIdentifier, String displayName, String permission, boolean active) {
        this.uuid = uuid;
        this.head = null;
        this.identifier = "minecraft:" + minecraftIdentifier;
        this.displayName = displayName;
        this.permission = permission;
        this.active = active;
    }

    Hats(UUID uuid, Integer headId, String displayName, String permission, boolean active) {
        this.uuid = uuid;
        this.head = headId;
        this.identifier = null;
        this.displayName = displayName;
        this.permission = permission;
        this.active = active;
    }

    public static Hats[] getHats() {
        return Hats.values();
    }

    public static Hats[] getActivesHats() {
        ArrayList<Hats> hats = new ArrayList<>();
        for (Hats hat : Hats.values()) {
            if (hat.isActive()) {
                hats.add(hat);
            }
        }
        return hats.toArray(new Hats[0]);
    }

    public static Hats fromUUID(String uuid) {
        for (Hats h : Hats.values()) {
            if (h.uuid.toString().equals(uuid)) {
                return h;
            }
        }
        return null;
    }

    public static Hats getHat(ItemStack itemStack) {
        NamespacedKey key = new NamespacedKey(Core.getInstance(), "hatId");
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.STRING)) {
            String foundValue = container.get(key, PersistentDataType.STRING);
            for (Hats hat : Hats.values()) {
                if (hat.getUniqueId().toString().equals(foundValue)) {
                    return hat;
                }
            }
        }
        return null;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isHead() {
        return identifier == null;
    }

    public Integer getHead() {
        return head;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isActive() {
        return active;
    }

    public ItemStack getItemStack() {
        if (this.isHead()) {
            return ItemUtils.customizedItem(Manager.getHeadDatabaseAPI().getItemHead(String.valueOf(this.getHead())), "§d" + this.getDisplayName(), new ArrayList<>(), "hatId", uuid);
        } else {
            return ItemUtils.customizedItem(new ItemStack(Objects.requireNonNull(Material.matchMaterial(this.getIdentifier()))), "§d" + this.getDisplayName(), new ArrayList<>(), "hatId", uuid);
        }
    }
}
