package net.sydokiddo.combatant.mixin.items.potions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    // Adds a Glass Bottle to the player's inventory instead of replacing their hand when used on Dirt to create Mud
    // This is to prevent players from losing their potions since they can now stack up to 8

    @Inject(method="useOn", at=@At("HEAD"), cancellable = true)
    public void useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {

        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        Player player = useOnContext.getPlayer();
        ItemStack itemStack = useOnContext.getItemInHand();
        BlockState blockState = level.getBlockState(blockPos);

        if (useOnContext.getClickedFace() != Direction.DOWN && blockState.is(BlockTags.CONVERTABLE_TO_MUD) && PotionUtils.getPotion(itemStack) == Potions.WATER) {

            level.playSound(null, blockPos, SoundEvents.GENERIC_SPLASH, SoundSource.PLAYERS, 1.0F, 1.0F);

            assert player != null;
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
                player.addItem(Items.GLASS_BOTTLE.getDefaultInstance());
            }

            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            if (!level.isClientSide) {
                ServerLevel serverLevel = (ServerLevel)level;

                for(int i = 0; i < 5; ++i) {
                    serverLevel.sendParticles(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), blockPos.getY() + 1, (double)blockPos.getZ() + level.random.nextDouble(), 1, 0.0D, 0.0D, 0.0D, 1.0D);
                }
            }

            level.playSound(null, blockPos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
            level.setBlockAndUpdate(blockPos, Blocks.MUD.defaultBlockState());
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));

        } else {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
