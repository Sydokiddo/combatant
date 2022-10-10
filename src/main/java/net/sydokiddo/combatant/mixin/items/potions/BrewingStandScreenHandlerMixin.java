package net.sydokiddo.combatant.mixin.items.potions;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BrewingStandMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BrewingStandMenu.class)
public abstract class BrewingStandScreenHandlerMixin extends AbstractContainerMenu {
    private BrewingStandScreenHandlerMixin(MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;set(Lnet/minecraft/world/item/ItemStack;)V"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void onTransferSlot(Player player, int index, CallbackInfoReturnable<ItemStack> info,
                                ItemStack itemStack, Slot slot, ItemStack itemStack2) {
        // Prevents more than 1 potion from fitting in a slot in a Brewing Stand
        if (slot.mayPlace(itemStack)) {
            boolean movedItems = false;
            for (int i = 0; i < 3; i++) {
                Slot slot2 = this.getSlot(i);
                if (slot2.getItem().isEmpty() && slot.mayPlace(itemStack2)) {
                    if (itemStack2.getCount() > slot2.getMaxStackSize()) {
                        slot2.set(itemStack2.split(slot2.getMaxStackSize()));
                    } else {
                        slot2.set(itemStack2.split(itemStack2.getCount()));
                    }
                    movedItems = true;
                    slot2.setChanged();
                    if (itemStack2.isEmpty()) break;
                }
            }
            if (movedItems) {
                info.setReturnValue(ItemStack.EMPTY);
            }
        }
    }

    @Redirect(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/BrewingStandMenu;moveItemStackTo(Lnet/minecraft/world/item/ItemStack;IIZ)Z"))
    private boolean onTransferSlotRedirect(BrewingStandMenu instance, ItemStack itemStack, int i, int a, boolean b) {
        return false;
    }
}