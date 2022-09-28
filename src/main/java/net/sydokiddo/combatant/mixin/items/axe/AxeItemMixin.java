package net.sydokiddo.combatant.mixin.items.axe;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DiggerItem.class, priority = 990)
public abstract class AxeItemMixin extends TieredItem implements Vanishable {

    public AxeItemMixin(Tier tier, Properties properties) {
        super(tier, properties);
    }

    // Axes in combat now only lose 1 durability instead of 2

    @Inject(method = "hurtEnemy", at = @At("HEAD"), cancellable = true)
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.getItem() instanceof AxeItem) {
            itemStack.hurtAndBreak(1, livingEntity2, (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        } else {
            itemStack.hurtAndBreak(2, livingEntity2, (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        }
        cir.setReturnValue(true);
    }

    // Axes don't destroy blocks when in Creative Mode

    @Unique
    @Override
    public boolean canAttackBlock(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, Player player) {
        if (player.getMainHandItem().getItem() instanceof AxeItem) {
            return !player.isCreative();
        } else {
            return true;
        }
    }
}