package net.sydokiddo.combatant.mixin.items.trident;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TridentItem.class)
public class TridentItemMixin extends Item {

    public TridentItemMixin(Properties properties) {
        super(properties);
    }

    // Tridents can now be repaired in an anvil using Prismarine Shards

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, ItemStack itemStack2) {
        return itemStack2.is(Items.PRISMARINE_SHARD);
    }
}
