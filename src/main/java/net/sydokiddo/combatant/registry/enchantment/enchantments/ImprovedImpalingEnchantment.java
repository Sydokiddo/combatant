package net.sydokiddo.combatant.registry.enchantment.enchantments;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.sydokiddo.combatant.registry.misc.ModSoundEvents;

public final class ImprovedImpalingEnchantment {

    public static float getAttackDamage(ItemStack stack, Entity target) {

        int impalingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.IMPALING, stack);

        if (impalingLevel > 0) {

            if (stack.getItem() instanceof TridentItem) {

                if (target.isInWaterOrRain()) {
                    target.level.playSound(null, target, ModSoundEvents.ITEM_TRIDENT_IMPALE, SoundSource.NEUTRAL, 2.0f, 1.0f);
                    return impalingLevel * 1.5F;
                }
            }
        }
        return 0;
    }
}