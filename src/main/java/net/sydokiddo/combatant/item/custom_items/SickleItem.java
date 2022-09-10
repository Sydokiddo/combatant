package net.sydokiddo.combatant.item.custom_items;

import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SickleItem extends SwordItem {

    public SickleItem(Tier toolMaterial, float attackDamage, float attackSpeed, Properties settings) {
        super(toolMaterial, (int) attackDamage, attackSpeed, settings);
    }
}