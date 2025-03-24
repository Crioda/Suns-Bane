package net.crit.sun.datagen;

import net.crit.sun.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SUNORE)
                .add(ModBlocks.SUN_SHARD_BRICK_SLAB)
                .add(ModBlocks.SUN_SHARD_BRICKS)
                .add(ModBlocks.SUN_SHARD_BRICK_STAIRS)
                .add(ModBlocks.SUN_SHARD_BRICK_WALL)
                .add(ModBlocks.SUN_SHARD_PILLAR)
                .add(ModBlocks.CHISELED_SUN_SHARD);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.SUN_SHARD_BRICK_SLAB)
                .add(ModBlocks.SUN_SHARD_BRICKS)
                .add(ModBlocks.SUN_SHARD_BRICK_STAIRS)
                .add(ModBlocks.SUN_SHARD_BRICK_WALL)
                .add(ModBlocks.SUN_SHARD_PILLAR)
                .add(ModBlocks.CHISELED_SUN_SHARD);




        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.SUNORE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.SUN_SHARD_BRICK_WALL);

    }
}

