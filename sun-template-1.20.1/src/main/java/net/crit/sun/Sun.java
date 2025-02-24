package net.crit.sun;

import net.crit.sun.Effect.EmpoweredEffect;
import net.crit.sun.Effect.TaterEffect;
import net.crit.sun.Item.ModItemGroups;
import net.crit.sun.Item.ModItems;


import net.fabricmc.api.ModInitializer;

import net.minecraft.block.entity.BlockEntityType;


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
	public static final StatusEffect TATER_EFFECT = new TaterEffect();
	public static final StatusEffect EMPOWERED_EFFECT = new EmpoweredEffect(StatusEffectCategory.BENEFICIAL, 0);




	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		ModItemGroups.registerItemGroups();



		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "tater"), TATER_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("sun", "empowered"), EMPOWERED_EFFECT);



	}
}