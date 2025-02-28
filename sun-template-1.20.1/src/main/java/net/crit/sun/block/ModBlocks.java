package net.crit.sun.block;

import net.crit.sun.Sun;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MagmaBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {


    public static  Block SUNORE = registerBlock("sunore",
            new Block(AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS).strength(4f, 3100f)));

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
