package net.sydokiddo.combatant.mixin.enchantments.aerial_affinity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.combatant.registry.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows for the player to mine at normal speed when not touching the ground if they have the Aerial Affinity enchantment

@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow @Final private Inventory inventory;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> type, Level world) {
        super(type, world);
    }

    @Inject(method = "getDestroySpeed",at = @At("RETURN"),cancellable = true)
    private void aerialAffinity(BlockState block, CallbackInfoReturnable<Float> cir) {
        int aerialAffinityLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AERIAL_AFFINITY, this.getItemBySlot(EquipmentSlot.CHEST)),
        EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.AERIAL_AFFINITY, this.getItemBySlot(EquipmentSlot.CHEST))));

        float f = this.inventory.getDestroySpeed(block);

        if (((!this.isOnGround()) || this.isFallFlying()) && aerialAffinityLevel > 0) {
            cir.setReturnValue(f);
        }
    }
}