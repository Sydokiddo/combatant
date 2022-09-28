package net.sydokiddo.combatant.mixin.items.trident;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public class TridentItemMixin extends Item {

    public TridentItemMixin(Properties properties) {
        super(properties);
    }

    // Tridents can now be repaired in an anvil using Prismarine Shards

    @Unique
    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.is(Items.PRISMARINE_SHARD);
    }

    // Tridents can destroy Cobwebs faster much like Swords can

    @Unique
    @Override
    public float getDestroySpeed(@NotNull ItemStack itemStack, BlockState blockState) {
        if (blockState.is(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            Material material = blockState.getMaterial();
            return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && !blockState.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
        }
    }

    // Breaking Cobwebs with a Trident only deals 1 durability damage to it

    @Inject(method = "mineBlock", at = @At("HEAD"), cancellable = true)
    public void mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (blockState.is(Blocks.COBWEB)) {
            if ((double) blockState.getDestroySpeed(level, blockPos) != 0.0D) {
                itemStack.hurtAndBreak(1, livingEntity, (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        } else {
            if ((double) blockState.getDestroySpeed(level, blockPos) != 0.0D) {
                itemStack.hurtAndBreak(2, livingEntity, (livingEntityx) -> livingEntityx.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            }
        }
        cir.setReturnValue(true);
    }

    // Overrides the vanilla Trident's enchantment value

    @Inject(method = "getEnchantmentValue", at = @At("HEAD"), cancellable = true)
    public void getEnchantmentValue(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(10);
    }
}
