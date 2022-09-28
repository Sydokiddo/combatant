package net.sydokiddo.combatant.mixin.enchantments.impaling;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.sydokiddo.combatant.registry.enchantment.enchantments.ImprovedImpalingEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThrownTrident.class)
public abstract class TridentEntityMixin extends AbstractArrow {

    @Shadow private ItemStack tridentItem;

    @Shadow private boolean dealtDamage;

    @Shadow public abstract boolean isChanneling();

    protected TridentEntityMixin(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"))
    protected void onHitEntity(EntityHitResult entityHitResult, CallbackInfo ci) {
        Entity entity = entityHitResult.getEntity();
        float f = 8.0F;
        if (entity instanceof LivingEntity) {
            f += ImprovedImpalingEnchantment.getAttackDamage(this.tridentItem, entityHitResult.getEntity());
        }

        Entity entity2 = this.getOwner();
        DamageSource damageSource = DamageSource.trident(this, entity2 == null ? this : entity2);
        this.dealtDamage = true;
        SoundEvent soundEvent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2) {
                if (entity2 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingEntity2, entity2);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity2, livingEntity2);
                }

                this.doPostHurtEffects(livingEntity2);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float g = 1.0F;
        if (this.level instanceof ServerLevel && this.level.isThundering() && this.isChanneling()) {
            BlockPos blockPos = entity.blockPosition();
            if (this.level.canSeeSky(blockPos)) {
                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level);
                assert lightningBolt != null;
                lightningBolt.moveTo(Vec3.atBottomCenterOf(blockPos));
                lightningBolt.setCause(entity2 instanceof ServerPlayer ? (ServerPlayer)entity2 : null);
                this.level.addFreshEntity(lightningBolt);
                soundEvent = SoundEvents.TRIDENT_THUNDER;
                g = 5.0F;
            }
        }

        this.playSound(soundEvent, g, 1.0F);
    }
}
