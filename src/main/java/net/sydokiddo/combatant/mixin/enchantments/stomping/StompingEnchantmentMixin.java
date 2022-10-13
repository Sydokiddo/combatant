package net.sydokiddo.combatant.mixin.enchantments.stomping;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.sydokiddo.combatant.Combatant;
import net.sydokiddo.combatant.registry.enchantment.ModEnchantments;
import net.sydokiddo.combatant.registry.misc.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class StompingEnchantmentMixin {

    LivingEntity player = (LivingEntity) (Object) this;

    @Inject(at = @At("RETURN"), method = "touch")
    private void collideWithEntity(Entity entity, CallbackInfo ci) {

        int stompingLevel = Math.max(0, Math.max(EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.STOMPING, player.getItemBySlot(EquipmentSlot.FEET)),
        EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.STOMPING, player.getItemBySlot(EquipmentSlot.FEET))));

        if (player instanceof ServerPlayer && (player.fallDistance > 1) && entity instanceof LivingEntity && (stompingLevel > 0) && (!player.isFallFlying()) && entity.isAlive()) {

            float damage = (player.fallDistance * (stompingLevel / 2.0f)); // Damage Calculator

            player.level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.ENCHANTMENT_STOMPING_STOMP, player.getSoundSource(), 0.5F, 1.0F);

            // Hurt Entity

            entity.hurt(new Combatant.StompingDamageSource(player), damage);

            // Particles

            double d = player.level.random.nextGaussian() * 0.02;
            double e = player.level.random.nextGaussian() * 0.02;
            double f = player.level.random.nextGaussian() * 0.02;
            player.level.addParticle(ParticleTypes.POOF, entity.getRandomX(1.0), entity.getRandomY(), entity.getRandomZ(1.0), d, e, f);
        }
    }
}
