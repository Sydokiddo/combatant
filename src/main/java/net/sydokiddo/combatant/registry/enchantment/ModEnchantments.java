package net.sydokiddo.combatant.registry.enchantment;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;
import net.sydokiddo.combatant.Combatant;
import net.sydokiddo.combatant.registry.enchantment.enchantments.AerialAffinityEnchantment;
import net.sydokiddo.combatant.registry.enchantment.enchantments.ArcaneProtectionEnchantment;

@SuppressWarnings("ALL")
public class ModEnchantments extends Enchantments {

    public static Enchantment AERIAL_AFFINITY = register("aerial_affinity",
    new AerialAffinityEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST));

    public static Enchantment ARCANE_PROTECTION = register("arcane_protection",
    new ArcaneProtectionEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR));


    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new ResourceLocation(Combatant.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + Combatant.MOD_ID);
    }
}
