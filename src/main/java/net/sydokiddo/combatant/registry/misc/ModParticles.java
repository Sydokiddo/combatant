package net.sydokiddo.combatant.registry.misc;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.*;
import net.sydokiddo.combatant.Combatant;
import net.sydokiddo.combatant.client.particles.OffhandSweepingParticles;
import net.sydokiddo.combatant.mixin.util.SimpleParticleTypeAccessor;
import net.sydokiddo.combatant.util.CoreRegistry;

// Particle Registry

public class ModParticles {
    public static final CoreRegistry<ParticleType<?>> PARTICLES = CoreRegistry.create(Registry.PARTICLE_TYPE_REGISTRY, Combatant.MOD_ID);
    public static final SimpleParticleType OFFHAND_SWEEPING = register("offhand_sweep_attack", false);

    public static SimpleParticleType register(String id, boolean bl) {
        return PARTICLES.register(id, SimpleParticleTypeAccessor.createSimpleParticleType(bl));
    }

    @Environment(EnvType.CLIENT)
    public static void init() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        registry.register(OFFHAND_SWEEPING, OffhandSweepingParticles.Provider::new);
    }
}
