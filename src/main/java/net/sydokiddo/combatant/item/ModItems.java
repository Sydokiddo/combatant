package net.sydokiddo.combatant.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.sydokiddo.combatant.Combatant;
import net.sydokiddo.combatant.item.custom_items.SickleItem;

public class ModItems {

    // List of Items:

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle",
    new SickleItem(Tiers.WOOD, 1.5f, -2f,
    new FabricItemSettings().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Item STONE_SICKLE = registerItem("stone_sickle",
    new SickleItem(Tiers.STONE, 1.5f, -2f,
    new FabricItemSettings().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle",
    new SickleItem(Tiers.GOLD, 1.5F, -2f,
    new FabricItemSettings().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Item IRON_SICKLE = registerItem("iron_sickle",
    new SickleItem(Tiers.IRON, 1.5F, -2f,
    new FabricItemSettings().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle",
    new SickleItem(Tiers.DIAMOND, 1.5F, -2f,
    new FabricItemSettings().tab(CreativeModeTab.TAB_COMBAT)));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle",
    new SickleItem(Tiers.NETHERITE, 1.5F, -2f,
    new FabricItemSettings().fireResistant().tab(CreativeModeTab.TAB_COMBAT)));

    // Registry for Items:

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new ResourceLocation(Combatant.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Items for " + "combatant");
    }
}