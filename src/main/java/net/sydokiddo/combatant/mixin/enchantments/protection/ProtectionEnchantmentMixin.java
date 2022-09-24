package net.sydokiddo.combatant.mixin.enchantments.protection;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.sydokiddo.combatant.registry.enchantment.ModEnchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ALL")
@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin extends Enchantment {

    protected ProtectionEnchantmentMixin(Rarity rarity, EnchantmentCategory enchantmentCategory, EquipmentSlot[] equipmentSlots) {
        super(rarity, enchantmentCategory, equipmentSlots);
    }

    @Inject(method = "checkCompatibility", at = @At("RETURN"), cancellable = true)
    public void checkCompatibility(Enchantment enchantment, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(enchantment != ModEnchantments.ARCANE_PROTECTION && this != enchantment);
    }

    @Shadow @Final public ProtectionEnchantment.Type type;

    // Overwrites the vanilla Protection enchantments so that Protection now only protects against melee damage

    @Overwrite
    public int getDamageProtection(int i, DamageSource damageSource) {
        if (damageSource.isBypassInvul()) {
            return 0;
        } else if (this.type == ProtectionEnchantment.Type.ALL && !damageSource.isFire() && !damageSource.isFall()
        && !damageSource.isExplosion() && !damageSource.isMagic() && !damageSource.isProjectile()) {
            return i * 2;
        } else if (this.type == ProtectionEnchantment.Type.FIRE && damageSource.isFire()) {
            return i * 2;
        } else if (this.type == ProtectionEnchantment.Type.FALL && damageSource.isFall()) {
            return i * 3;
        } else if (this.type == ProtectionEnchantment.Type.EXPLOSION && damageSource.isExplosion()) {
            return i * 2;
        } else {
            return this.type == ProtectionEnchantment.Type.PROJECTILE && damageSource.isProjectile() ? i * 2 : 0;
        }
    }
}
