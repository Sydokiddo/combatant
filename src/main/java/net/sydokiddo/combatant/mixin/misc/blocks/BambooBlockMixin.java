package net.sydokiddo.combatant.mixin.misc.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin extends Block implements BonemealableBlock {

    public BambooBlockMixin(Properties properties) {
        super(properties);
    }

    // Swords, Axes, and Tridents can all mine Bamboo faster

    @SuppressWarnings("ALL")
    @Inject(method = "getDestroyProgress", at = @At("HEAD"), cancellable = true)
    public void getDestroyProgress(BlockState blockState, Player player, BlockGetter blockGetter, BlockPos blockPos, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(player.getMainHandItem().getItem() instanceof SwordItem || player.getMainHandItem().getItem() instanceof AxeItem ||
        player.getMainHandItem().getItem() instanceof TridentItem ? 1.0F : super.getDestroyProgress(blockState, player, blockGetter, blockPos));
    }
}
