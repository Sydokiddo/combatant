package net.sydokiddo.combatant.mixin.items.sickles;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;
import net.minecraft.world.item.enchantment.SweepingEdgeEnchantment;
import net.sydokiddo.combatant.item.custom_items.SickleItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
@SuppressWarnings("ConstantConditions")
public abstract class SickleModification {

    // Sickles cannot be enchanted with Sweeping Edge or Fire Aspect

    @Final
    public EnchantmentCategory type;

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment instanceof SweepingEdgeEnchantment) {
            if ((stack.getItem() instanceof SickleItem)) {
                cir.setReturnValue(false);
            }
        }
        if (enchantment instanceof FireAspectEnchantment) {
            if ((stack.getItem() instanceof SickleItem)) {
                cir.setReturnValue(false);
            }
        }
    }
}