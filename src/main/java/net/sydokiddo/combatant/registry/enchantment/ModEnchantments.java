package net.sydokiddo.combatant.registry.enchantment;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.sydokiddo.combatant.Combatant;
import net.sydokiddo.combatant.registry.enchantment.enchantments.AerialAffinityEnchantment;

@SuppressWarnings("ALL")
public class ModEnchantments {

    public static Enchantment AERIAL_AFFINITY = register("aerial_affinity",
    new AerialAffinityEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_CHEST));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new ResourceLocation(Combatant.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments() {
        System.out.println("Registering Enchantments for " + Combatant.MOD_ID);
    }
}
