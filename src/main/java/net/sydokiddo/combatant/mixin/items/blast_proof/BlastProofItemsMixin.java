package net.sydokiddo.combatant.mixin.items.blast_proof;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.sydokiddo.combatant.registry.tags.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class BlastProofItemsMixin {

    // Makes Peroratite related items immune to explosion damage

    @Inject(method = "isInvulnerableTo(Lnet/minecraft/world/damagesource/DamageSource;)Z", at = @At("RETURN"), cancellable = true)
    protected void isInvulnerableTo(DamageSource source, CallbackInfoReturnable<Boolean> ci) {
        Entity entity = (Entity) (Object) this;
        if (entity instanceof ItemEntity item && item.getItem().is(ModTags.BLAST_PROOF_ITEMS)) {
            ci.setReturnValue(ci.getReturnValue()
                    || (!item.getItem().isEmpty() && source.isExplosion()));
        }
    }
}