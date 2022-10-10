package net.sydokiddo.combatant.registry.misc;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.sydokiddo.combatant.Combatant;

@SuppressWarnings("ALL")
public class ModSoundEvents {

    // Sound Registry:

    public static final SoundEvent ITEM_TRIDENT_IMPALE = registerSoundEvent("item.trident.impale");

    public static final SoundEvent ENCHANTMENT_STOMPING_STOMP = registerSoundEvent("enchantment.stomping.stomp");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Combatant.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Combatant.MOD_ID);
    }
}
