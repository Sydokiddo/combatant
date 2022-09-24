package net.sydokiddo.combatant.registry.enchantment.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AerialAffinityEnchantment extends Enchantment {
    public AerialAffinityEnchantment(Rarity weight, EnchantmentCategory type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ElytraItem || stack.getItem() instanceof ArmorItem && ((ArmorItem)stack.getItem()).getSlot() == EquipmentSlot.CHEST;
    }

    public int getMinCost(int i) {
        return 1;
    }

    public int getMaxCost(int i) {
        return this.getMinCost(i) + 40;
    }

    public boolean isTreasureOnly() {
        return false;
    }

    public boolean isTradeable() {
        return true;
    }

    public boolean isDiscoverable() {
        return true;
    }

    public int getMaxLevel() {
        return 1;
    }
}