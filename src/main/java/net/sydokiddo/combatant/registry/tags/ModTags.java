package net.sydokiddo.combatant.registry.tags;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {

    // Item Tags

    public static final TagKey<Item> DUAL_WIELDING_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("combatant", "dual_wielding_items"));
    public static final TagKey<Item> BLAST_PROOF_ITEMS = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("combatant", "blast_proof_items"));

}
