package net.sydokiddo.combatant.mixin.items.misc;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Slot.class)
public class SlotMixin {

    @Shadow @Final public Container container;

    @Redirect(method = "safeInsert(Lnet/minecraft/world/item/ItemStack;I)Lnet/minecraft/world/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;getMaxStackSize(Lnet/minecraft/world/item/ItemStack;)I"))
    private int safeInsert(Slot slot, ItemStack stack) {
        if (container instanceof BrewingStandBlockEntity) {
            if (stack.getItem() instanceof PotionItem) {
                return 1;
            }
        }
        if (stack.getItem() instanceof SnowballItem || stack.getItem() instanceof EggItem || stack.getItem() instanceof EnderpearlItem || stack.getItem() instanceof SignItem) {
            return 64;
        }
        if (stack.getItem() instanceof MinecartItem || stack.getItem() instanceof BoatItem || stack.getItem() instanceof BedItem) {
            return 16;
        }
        return slot.getMaxStackSize(stack);
    }
}