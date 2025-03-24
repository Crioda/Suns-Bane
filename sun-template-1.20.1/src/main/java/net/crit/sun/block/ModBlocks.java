package net.crit.sun.block;

import net.crit.sun.Sun;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static  Block SUNORE = registerBlock("sunore",
            new Block(AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS).strength(4f, 3100f)));

    public static  Block SUN_SHARD_BRICKS = registerBlock("sun_shard_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).strength(2f, 3f)));

    public static  Block SUN_SHARD_BRICK_WALL = registerBlock("sun_shard_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).strength(2f, 3f)));

    public static  Block SUN_SHARD_BRICK_STAIRS = registerBlock("sun_shard_brick_stairs",
            new StairsBlock(SUN_SHARD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SUN_SHARD_BRICKS)));

    public static  Block SUN_SHARD_BRICK_SLAB = registerBlock("sun_shard_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).strength(2f, 3f)));

    public static  Block SUN_SHARD_PILLAR = registerBlock("sun_shard_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).strength(2f, 3f)));

    public static  Block CHISELED_SUN_SHARD = registerBlock("chiseled_sun_shard",
            new Block(AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK).strength(2f, 3f)));

    public static Block MOLTEN_MAGMA = registerBlock("molten_magma",
            new MagmaBlock(AbstractBlock.Settings.copy(Blocks.MAGMA_BLOCK).strength(2f, 2f)));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Sun.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Sun.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Sun.LOGGER.info("Registering ModBlocks for " + Sun.MOD_ID);
    }
}
