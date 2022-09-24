package net.sydokiddo.combatant.mixin.enchantments.aerial_affinity;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.sydokiddo.combatant.registry.enchantment.enchantments.AerialAffinityEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
@SuppressWarnings("ConstantConditions")
public abstract class ItemModification {

    // Mixin to allow the Aerial Affinity enchantment to be put onto a Chestplate or Elytra

    @Final
    public EnchantmentCategory type;
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Enchantment enchantment = (Enchantment) (Object) this;
        if (enchantment instanceof AerialAffinityEnchantment) {
            if (!(stack.getItem() instanceof ElytraItem) || (!(stack.getItem() instanceof ArmorItem && ((ArmorItem)stack.getItem()).getSlot() == EquipmentSlot.CHEST))) return;
            cir.setReturnValue(true);
        }
    }
}