package net.sydokiddo.combatant;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.sydokiddo.combatant.registry.enchantment.ModEnchantments;
import net.sydokiddo.combatant.registry.item.ModItems;
import net.sydokiddo.combatant.registry.misc.ModParticles;
import net.sydokiddo.combatant.registry.misc.ModSoundEvents;
import net.sydokiddo.combatant.util.PlayerAttackPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combatant implements ModInitializer {

	public static final String MOD_ID = "combatant";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModParticles.PARTICLES.register();
		ModSoundEvents.registerSounds();
		PlayerAttackPacket.init();
		ModEnchantments.registerModEnchantments();

		// Registry:

		LOGGER.info("Thank you for downloading Combatant! :)");
	}

	// Stomping Damage Source for Stomping Enchantment

	public static class StompingDamageSource extends EntityDamageSource {

		public StompingDamageSource(Entity source) {
			super("stomping", source);
		}
	}
}
