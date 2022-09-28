package net.sydokiddo.combatant.mixin.items.trident;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.LootBonusEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(Enchantment.class)
public abstract class TridentModification {

    // Mixin to allow Looting and Sharpness to work on Tridents

    @Shadow
    @Final
    public EnchantmentCategory category;
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment instanceof LootBonusEnchantment) {
            if (category != EnchantmentCategory.WEAPON || !(stack.getItem() instanceof TridentItem)) return;
            cir.setReturnValue(true);
        }
        if (enchantment instanceof DamageEnchantment) {
            if (category != EnchantmentCategory.WEAPON || !(stack.getItem() instanceof TridentItem)) return;
            cir.setReturnValue(true);
        }
    }
}