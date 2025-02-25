package net.crit.sun.datagen;

import net.crit.sun.Item.ModItems;
import net.crit.sun.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;


public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        addDropWithSilkTouch(ModBlocks.SUNORE, ModBlocks.SUNORE);
        addDrop(ModBlocks.SUNORE, ModItems.SUN_SHARD);

    }


}
