package net.sydokiddo.combatant.tags;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CombatantTags {

    // Item Tags

    public static final TagKey<Item> DUAL_WIELDING_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("combatant", "dual_wielding_items"));

}
