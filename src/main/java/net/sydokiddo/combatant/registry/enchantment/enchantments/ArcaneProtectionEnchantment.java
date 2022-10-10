package net.sydokiddo.combatant.registry.enchantment.enchantments;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.*;
import org.jetbrains.annotations.NotNull;

public class ArcaneProtectionEnchantment extends Enchantment {

    public ArcaneProtectionEnchantment(Rarity weight, EnchantmentCategory type, EquipmentSlot... slotTypes) {
        super(weight, type, slotTypes);
    }

    public int getMinCost(int i) {
        return 10 + (i - 1) * 8;
    }

    public int getMaxCost(int i) {
        return this.getMinCost(i) + 8;
    }

    public int getMaxLevel() {
        return 4;
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

    public int getDamageProtection(int i, DamageSource damageSource) {
        if (damageSource.isBypassInvul()) {
            return 0;
        } else {
            return damageSource.isMagic() ? (int) (i * 1.5) : 0;
        }
    }

    public boolean checkCompatibility(@NotNull Enchantment enchantment) {
        if (enchantment instanceof ProtectionEnchantment protectionEnchantment && protectionEnchantment.type != ProtectionEnchantment.Type.FALL) {
            return false;
        }
        return super.checkCompatibility(enchantment);
    }
}
