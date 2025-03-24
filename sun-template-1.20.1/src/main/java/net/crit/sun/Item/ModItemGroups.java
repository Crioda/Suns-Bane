package net.crit.sun.Item;

import net.crit.sun.Sun;

import net.crit.sun.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup SUN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Sun.MOD_ID, "sun"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sun"))
                    .icon(() -> new ItemStack(ModItems.TRUE_SUNS_END)).entries((displayContext, entries) -> {

                        entries.add(ModItems.SUNS_END);
                        entries.add(ModItems.TRUE_SUNS_END);
                        entries.add(ModItems.THE_AWAKENED_END);
                        entries.add(ModItems.SUN_SHARD);
                        entries.add(ModItems.CAGED_SOULS);
                        entries.add(ModItems.GILDED_HILT);
                        entries.add(ModBlocks.MOLTEN_MAGMA);
                        entries.add(ModBlocks.SUNORE);

                        entries.add(ModBlocks.SUN_SHARD_BRICKS);
                        entries.add(ModBlocks.SUN_SHARD_BRICK_STAIRS);
                        entries.add(ModBlocks.SUN_SHARD_BRICK_SLAB);
                        entries.add(ModBlocks.SUN_SHARD_BRICK_WALL);
                        entries.add(ModBlocks.SUN_SHARD_PILLAR);
                        entries.add(ModBlocks.CHISELED_SUN_SHARD);

                    }).build());


    public static void registerItemGroups() {
        Sun.LOGGER.info("Registering Item Groups for " + Sun.MOD_ID);
    }
}
