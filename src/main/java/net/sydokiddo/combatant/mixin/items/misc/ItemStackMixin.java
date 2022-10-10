package net.sydokiddo.combatant.mixin.items.misc;

import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow public abstract Item getItem();

    // Changes the max stack size of various items

    @Inject(method = "getMaxStackSize", at = @At("RETURN"), cancellable = true)
    private void getMaxStackSize(CallbackInfoReturnable<Integer> cir) {

        if (this.getItem() instanceof PotionItem || this.getItem() instanceof SplashPotionItem || this.getItem() instanceof LingeringPotionItem) {
            cir.setReturnValue(8);
        }
        if (this.getItem() instanceof SnowballItem || this.getItem() instanceof EggItem) {
            cir.setReturnValue(64);
        }
        if (this.getItem() instanceof MinecartItem || this.getItem() instanceof BoatItem) {
            cir.setReturnValue(16);
        }
    }
}