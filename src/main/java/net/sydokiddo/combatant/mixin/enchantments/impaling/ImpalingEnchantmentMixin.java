package net.sydokiddo.combatant.mixin.enchantments.impaling;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.enchantment.TridentImpalerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TridentImpalerEnchantment.class)
public class ImpalingEnchantmentMixin {

    /**
     * @author Sydokiddo
     * @reason Overrides the vanilla Impaling enchantment and replaces it with a new one
     */
    @Overwrite
    public float getDamageBonus(int i, MobType mobType) {
        return 0.0F;
    }
}
