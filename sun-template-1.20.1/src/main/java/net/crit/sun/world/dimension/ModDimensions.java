package net.crit.sun.world.dimension;

import net.crit.sun.Sun;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> SUN_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(Sun.MOD_ID, "sundim"));
    public static final RegistryKey<World> SUN_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(Sun.MOD_ID, "sundim"));
    public static final RegistryKey<DimensionType> SUN_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(Sun.MOD_ID, "sundim_type"));

    public static final RegistryKey<DimensionOptions> SUN_KEY_TWO = RegistryKey.of(RegistryKeys.DIMENSION,
            new Identifier(Sun.MOD_ID, "sundim2"));
    public static final RegistryKey<World> SUN_LEVEL_KEY_TWO = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(Sun.MOD_ID, "sundim2"));
    public static final RegistryKey<DimensionType> SUN_DIM_TYPE_TWO = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            new Identifier(Sun.MOD_ID, "sundim_type_two"));

    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(SUN_DIM_TYPE, new DimensionType(
                OptionalLong.of(0), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                true, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                DimensionTypes.THE_END_ID, // effectsLocation
                1f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));

        context.register(SUN_DIM_TYPE_TWO, new DimensionType(
                OptionalLong.of(0), // fixedTime
                false, // hasSkylight
                true, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                true, // bedWorks
                false, // respawnAnchorWorks
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_NETHER, // infiniburn
                DimensionTypes.THE_END_ID, // effectsLocation
                -0.05f, // ambientLight
                new DimensionType.MonsterSettings(false, false, UniformIntProvider.create(0, 0), 0)));


    }


}


