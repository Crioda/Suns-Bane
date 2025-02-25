package net.crit.sun.datagen;

import net.crit.sun.Item.ModItems;

import net.crit.sun.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SUNORE);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {


        itemModelGenerator.register(ModItems.SUN_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SUNS_END, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CAGED_SOULS, Models.GENERATED);
        itemModelGenerator.register(ModItems.GILDED_HILT,Models.HANDHELD_ROD);
        itemModelGenerator.register(ModItems.TS,Models.HANDHELD);
        itemModelGenerator.registerArmor((ArmorItem) ModItems.AXE);




    }


}
