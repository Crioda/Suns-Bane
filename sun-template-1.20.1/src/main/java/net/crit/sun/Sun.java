package net.crit.sun;

import net.crit.sun.Effect.BlanketedEffect;
import net.crit.sun.Effect.EmpoweredEffect;
import net.crit.sun.Effect.ChargedEffect;
import net.crit.sun.Effect.TetheredEffect;
import net.crit.sun.Item.ModItemGroups;
import net.crit.sun.Item.ModItems;


import net.crit.sun.block.ModBlocks;
import net.crit.sun.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sun implements ModInitializer {
	public static final String MOD_ID = "sun";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final StatusEffect CHARGED_EFFECT = new ChargedEffect();
	public static final StatusEffect TETHERED_EFFECT = new TetheredEffect();
	public static final StatusEffect BLANKETED_EFFECT = new BlanketedEffect();
	public static final StatusEffect EMPOWERED_EFFECT = new EmpoweredEffect(StatusEffectCategory.BENEFICIAL, 0);




	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();



		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "charged"), CHARGED_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "empowered"), EMPOWERED_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "tethered"), TETHERED_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "blanketed"), BLANKETED_EFFECT);

		ModWorldGen.generateModWorldGen();



	}
}