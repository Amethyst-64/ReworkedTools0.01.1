package net.amethyst.reworkedtools.item;

import net.amethyst.reworkedtools.ReworkedTools;
import net.amethyst.reworkedtools.item.custom.ModHammer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item MODHAMMER = registerItem( "hammer",
            new ModHammer(ModToolMaterials.STONE,2, 6f,
                    (new FabricItemSettings().group(ModItemGroup.MODHAMMER))));

    public static final Item IRON_HAMMER = registerItem( "iron_hammer",
            new ModHammer(ModToolMaterials.IRON,2, 4.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODHAMMER))));
    public static final Item DIAMOND_HAMMER = registerItem( "diamond_hammer",
            new ModHammer(ModToolMaterials.DIAMOND,3, 8.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODHAMMER))));
    public static final Item NETHERITE_HAMMER = registerItem( "netherite_hammer",
            new ModHammer(ModToolMaterials.NETHERITE,4, 9.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODHAMMER))));
    public static final Item MODEXCAVATOR = registerItem( "excavator",
            new ModHammer(ModToolMaterials.STONE,2, 6f,
                    (new FabricItemSettings().group(ModItemGroup.MODEXCAVATOR))));

    public static final Item IRON_EXCAVATOR = registerItem( "iron_excavator",
            new ModHammer(ModToolMaterials.IRON,2, 4.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODEXCAVATOR))));
    public static final Item DIAMOND_EXCAVATOR = registerItem( "diamond_excavator",
            new ModHammer(ModToolMaterials.DIAMOND,3, 8.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODEXCAVATOR))));
    public static final Item NETHERITE_EXCAVATOR = registerItem( "netherite_excavator",
            new ModHammer(ModToolMaterials.NETHERITE,4, 9.5f,
                    (new FabricItemSettings().group(ModItemGroup.MODEXCAVATOR))));





    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(ReworkedTools.MOD_ID, name), item);
    }
    public static void registerModItems(){
        ReworkedTools.LOGGER.debug("Registering Mod Items for " + ReworkedTools.MOD_ID);
    }

}
