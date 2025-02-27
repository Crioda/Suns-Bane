package net.crit.sun.Item;



import net.crit.sun.Item.custom.Coolitem;
import net.crit.sun.Item.custom.LongswordItem;
import net.crit.sun.Item.custom.SoulYoinker;
import net.crit.sun.Sun;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SUN_SHARD = registerItem("sun_shard",
            new Item(new Item.Settings()));

    public static final Item CAGED_SOULS = registerItem("caged_souls",
            new Item(new Item.Settings()));

    public static final Item SUNS_END = registerItem("suns_end",
            new SwordItem(ToolMaterials.NETHERITE, 2, -2.1f, new FabricItemSettings()));

    public static final Item GILDED_HILT = registerItem("gilded_hilt",
            new HoeItem(ToolMaterials.WOOD, 1, -2f, new FabricItemSettings()));

    public static final Item TRUE_SUNS_END = registerItem("true_suns_end",
            new Coolitem(ModToolMaterial.SUN_SHARD, 4, -2.4f, new FabricItemSettings()));

    public static final Item THE_AWAKENED_END = registerItem("the_awakened_end",
            new SoulYoinker(ModToolMaterial.SUN_SHARD, 5, -2.7f, new FabricItemSettings()));

    public static final Item AXE = registerItem("axe",
            new ArmorItem(ModArmorMaterials.BEANS, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item TESTITEM = registerItem("testitem",
            new Item(new FabricItemSettings()));










    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Sun.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Sun.LOGGER.info("Registering Mod Items For " + Sun.MOD_ID);
    }

}
