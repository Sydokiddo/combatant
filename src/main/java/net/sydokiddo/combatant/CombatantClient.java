package net.sydokiddo.combatant;

import net.fabricmc.api.ClientModInitializer;
import net.sydokiddo.combatant.registry.misc.ModParticles;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class CombatantClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ModParticles.init();
    }
}