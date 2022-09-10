package net.sydokiddo.combatant.mixin.items.sickles;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.sydokiddo.combatant.tags.CombatantTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)

public class ServerPlayerEntityMixin {

    // Allows for the player to swing their off-hand if holding 2 dual-wielding items

    @Inject(method = "swing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;swing(Lnet/minecraft/world/InteractionHand;)V", shift = Shift.AFTER), cancellable = true)
    private void swingHandMixin(InteractionHand hand, CallbackInfo info) {
        boolean item = ((Player) (Object) this).getOffhandItem().is(CombatantTags.DUAL_WIELDING_ITEMS);
        if (hand == InteractionHand.OFF_HAND && (item))
            info.cancel();
    }
}