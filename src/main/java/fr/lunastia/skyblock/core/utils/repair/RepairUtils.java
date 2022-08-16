package fr.lunastia.skyblock.core.utils.repair;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class RepairUtils {
    public static final Integer defaultCost = 1500;

    public int getPriceByMaterial(Material material) {
        if (material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS) {
            return 50;
        } else if (material == Material.IRON_HELMET || material == Material.IRON_CHESTPLATE || material == Material.IRON_LEGGINGS || material == Material.IRON_BOOTS || material == Material.IRON_SWORD || material == Material.IRON_PICKAXE || material == Material.IRON_AXE || material == Material.IRON_HOE || material == Material.IRON_SHOVEL) {
            return 150;
        } else if (material == Material.DIAMOND_HELMET || material == Material.DIAMOND_CHESTPLATE || material == Material.DIAMOND_LEGGINGS || material == Material.DIAMOND_BOOTS || material == Material.DIAMOND_SWORD || material == Material.DIAMOND_PICKAXE || material == Material.DIAMOND_AXE || material == Material.DIAMOND_HOE || material == Material.DIAMOND_SHOVEL) {
            return 250;
        } else if (material == Material.GOLDEN_HELMET || material == Material.GOLDEN_CHESTPLATE || material == Material.GOLDEN_LEGGINGS || material == Material.GOLDEN_BOOTS || material == Material.GOLDEN_SWORD || material == Material.GOLDEN_PICKAXE || material == Material.GOLDEN_AXE || material == Material.GOLDEN_HOE || material == Material.GOLDEN_SHOVEL) {
            return 100;
        } else if (material == Material.NETHERITE_HELMET || material == Material.NETHERITE_CHESTPLATE || material == Material.NETHERITE_LEGGINGS || material == Material.NETHERITE_BOOTS || material == Material.NETHERITE_SWORD || material == Material.NETHERITE_PICKAXE || material == Material.NETHERITE_AXE || material == Material.NETHERITE_HOE || material == Material.NETHERITE_SHOVEL) {
            return 1500;
        } else if (material == Material.WOODEN_SWORD || material == Material.WOODEN_PICKAXE || material == Material.WOODEN_AXE || material == Material.WOODEN_HOE || material == Material.WOODEN_SHOVEL) {
            return 5;
        } else if (material == Material.TRIDENT) {
            return 500;
        } else if (material == Material.FLINT_AND_STEEL) {
            return 100;
        } else if (material == Material.SHEARS) {
            return 100;
        } else if (material == Material.ELYTRA) {
            return 100;
        } else if (material == Material.CROSSBOW) {
            return 500;
        } else if (material == Material.BOW) {
            return 100;
        } else if (material == Material.SHIELD) {
            return 250;
        } else if (material == Material.FISHING_ROD) {
            return 100;
        } else if (material == Material.CARROT_ON_A_STICK || material == Material.WARPED_FUNGUS_ON_A_STICK) {
            return 20;
        } else {
            return 0;
        }
    }

    public int getPriceByItem(ItemStack item, Integer slot) {
        final int[] price = {defaultCost};
        price[0] += getPriceByMaterial(item.getType());
        getPriceByEnchantment(item).forEach((enchantment, priceByEnchantment) -> {
            price[0] += priceByEnchantment;
        });
        return price[0];
    }

    public HashMap<Enchantment, Integer> getPriceByEnchantment(ItemStack item) {
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        for (Enchantment enchantment : item.getEnchantments().keySet()) {
            enchantments.put(enchantment, getPriceByEnchantment(enchantment.getName()));
        }
        return enchantments;
    }

    private int getPriceByEnchantment(String enchantment) {
        return switch (enchantment) {
            case "PROTECTION_ENVIRONMENTAL" -> 50; // Protection
            case "PROTECTION_FIRE" -> 50; // Fire Protection
            case "PROTECTION_FALL" -> 50; // Feather Falling
            case "PROTECTION_EXPLOSIONS" -> 50; // Blast Protection
            case "PROTECTION_PROJECTILE" -> 50; // Projectile Protection
            case "OXYGEN" -> 50; // Respiration
            case "WATER_WORKER" -> 50; // Aqua Affinity
            case "THORNS" -> 50; // Thorns
            case "DEPTH_STRIDER" -> 50; // Depth Strider
            case "FROST_WALKER" -> 50; // Frost Walker
            case "BINDING_CURSE" -> 50; // Binding Curse
            case "DAMAGE_ALL" -> 50; // Sharpness
            case "DAMAGE_UNDEAD" -> 50; // Smite
            case "DAMAGE_ARTHROPODS" -> 50; // Bane of Arthropods
            case "KNOCKBACK" -> 50; // Knockback
            case "FIRE_ASPECT" -> 50; // Fire Aspect
            case "LOOT_BONUS_MOBS" -> 50; // looting
            case "SWEEPING_EDGE" -> 50; // sweeping
            case "DIG_SPEED" -> 50; // efficiency
            case "SILK_TOUCH" -> 50; // silk touch
            case "DURABILITY" -> 50; // Unbreaking
            case "LOOT_BONUS_BLOCKS" -> 50; // fortune
            case "ARROW_DAMAGE" -> 50; // Power
            case "ARROW_KNOCKBACK" -> 50; // Punch
            case "ARROW_FIRE" -> 50; // Flame
            case "ARROW_INFINITE" -> 50; // Infinity
            case "LUCK" -> 50; // Luck of the Sea
            case "LURE" -> 50; // lure
            case "LOYALTY" -> 50; // loyalty
            case "IMPALING" -> 50; // impaling
            case "RIPTIDE" -> 50; //riptide
            case "CHANNELING" -> 50; // channeling
            case "MULTISHOT" -> 50; // multishot
            case "QUICK_CHARGE" -> 50; // quick charge
            case "PIERCING" -> 50; // piercing
            case "MENDING" -> 50; // mending
            case "VANISHING_CURSE" -> 50; // vanishing curse
            case "SOUL_SPEED" -> 50; // soul speed
            case "SWIFT_SNEAK" -> 50; // swift sneak
            default -> 0;
        };
    }

    public String getEnchantmentName(String enchantment) {
        return switch (enchantment) {
            case "PROTECTION_ENVIRONMENTAL" -> "Protection";
            case "PROTECTION_FIRE" -> "Protection contra le feu";
            case "PROTECTION_FALL" -> "Chute amortie";
            case "PROTECTION_EXPLOSIONS" -> "Protection contre les explosions";
            case "PROTECTION_PROJECTILE" -> "Protection contre les projectiles";
            case "OXYGEN" -> "Apnée";
            case "WATER_WORKER" -> "Affinité aquatique";
            case "THORNS" -> "Épines";
            case "DEPTH_STRIDER" -> "Agilité aquatique";
            case "FROST_WALKER" -> "Semelles givrantes";
            case "BINDING_CURSE" -> "Malédiction du lien éternel";
            case "DAMAGE_ALL" -> "Tranchant";
            case "DAMAGE_UNDEAD" -> "Châtiment";
            case "DAMAGE_ARTHROPODS" -> "Châtiment";
            case "KNOCKBACK" -> "Recul";
            case "FIRE_ASPECT" -> "Aura de feu";
            case "LOOT_BONUS_MOBS" -> "Butin";
            case "SWEEPING_EDGE" -> "Affilage";
            case "DIG_SPEED" -> "Efficacité";
            case "SILK_TOUCH" -> "Toucher de soie";
            case "DURABILITY" -> "Solidité";
            case "LOOT_BONUS_BLOCKS" -> "Fortune";
            case "ARROW_DAMAGE" -> "Puissance";
            case "ARROW_KNOCKBACK" -> "Frappe";
            case "ARROW_FIRE" -> "Flame";
            case "ARROW_INFINITE" -> "Infinité";
            case "LUCK" -> "Chance de la mer";
            case "LURE" -> "Appât";
            case "LOYALTY" -> "Loyauté";
            case "IMPALING" -> "Empalement";
            case "RIPTIDE" -> "Impulsion";
            case "CHANNELING" -> "Canalisation";
            case "MULTISHOT" -> "Tir multiple";
            case "QUICK_CHARGE" -> "Charge rapide";
            case "PIERCING" -> "Perforation";
            case "MENDING" -> "Raccommodage";
            case "VANISHING_CURSE" -> "Malédiction de disparition\n";
            case "SOUL_SPEED" -> "Agilité des âmes";
            case "SWIFT_SNEAK" -> "Faufilement rapide";
            default -> "";
        };
    }
}
