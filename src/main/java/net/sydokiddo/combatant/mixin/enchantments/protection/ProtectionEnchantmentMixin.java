package net.sydokiddo.combatant.mixin.enchantments.protection;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin extends Enchantment {

    protected ProtectionEnchantmentMixin(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Shadow @Final public ProtectionEnchantment.Type type;

    // Overwrites the vanilla Protection enchantments so that Protection now only protects against melee damage

    @Inject(method = "getDamageProtection", at = @At("HEAD"), cancellable = true)
    public void getDamageProtection(int i, DamageSource damageSource, CallbackInfoReturnable<Integer> cir) {
        if (damageSource.isBypassInvul()) {
            cir.setReturnValue(0);
        } else if (this.type == ProtectionEnchantment.Type.ALL && !damageSource.isFire() && !damageSource.isFall()
        && !damageSource.isExplosion() && !damageSource.isMagic() && !damageSource.isProjectile()) {
            cir.setReturnValue(i * 2);
        } else if (this.type == ProtectionEnchantment.Type.FIRE && damageSource.isFire()) {
            cir.setReturnValue(i * 2);
        } else if (this.type == ProtectionEnchantment.Type.FALL && damageSource.isFall()) {
            cir.setReturnValue(i * 6);
        } else if (this.type == ProtectionEnchantment.Type.EXPLOSION && damageSource.isExplosion()) {
            cir.setReturnValue(i * 2);
        } else {
            cir.setReturnValue(this.type == ProtectionEnchantment.Type.PROJECTILE && damageSource.isProjectile() ? i * 2 : 0);
        }
    }
}
