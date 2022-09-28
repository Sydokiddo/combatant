package net.sydokiddo.combatant.mixin.items.misc;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Slot.class)
public class SlotMixin {

    @Redirect(method = "safeInsert(Lnet/minecraft/world/item/ItemStack;I)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I"))
    private int safeInsert(Slot slot, ItemStack stack) {
        if (stack.getItem() instanceof PotionItem) {
            return 8;
        }
        if (stack.getItem() instanceof SnowballItem || stack.getItem() instanceof EggItem) {
            return 64;
        }
        if (stack.getItem() instanceof EnchantedBookItem) {
            return 8;
        }
        if (stack.getItem() instanceof MinecartItem || stack.getItem() instanceof BoatItem) {
            return 16;
        }
        return slot.getMaxStackSize(stack);
    }
}