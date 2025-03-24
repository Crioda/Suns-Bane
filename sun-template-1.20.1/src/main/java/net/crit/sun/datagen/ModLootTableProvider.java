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


        addDrop(ModBlocks.SUNORE, ModItems.SUN_SHARD);

        addDrop(ModBlocks.SUN_SHARD_BRICKS, ModBlocks.SUN_SHARD_BRICKS);
        addDrop(ModBlocks.SUN_SHARD_BRICK_STAIRS, ModBlocks.SUN_SHARD_BRICK_STAIRS);
        addDrop(ModBlocks.SUN_SHARD_BRICK_SLAB, ModBlocks.SUN_SHARD_BRICK_SLAB);
        addDrop(ModBlocks.SUN_SHARD_BRICK_WALL, ModBlocks.SUN_SHARD_BRICK_WALL);
        addDrop(ModBlocks.SUN_SHARD_PILLAR, ModBlocks.SUN_SHARD_PILLAR);
        addDrop(ModBlocks.CHISELED_SUN_SHARD, ModBlocks.CHISELED_SUN_SHARD);




    }


}
