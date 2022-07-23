package net.amethyst.reworkedtools.item;

import net.amethyst.reworkedtools.ReworkedTools;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.Identifier;

public class ModItemGroup {
        public static final ItemGroup HAMMERITEM = FabricItemGroupBuilder.build(
                new Identifier(ReworkedTools.MOD_ID, "hammer"), () -> new ItemStack(ModItems.HAMMERITEM));
    public static final ItemGroup EXCAVATORITEM = FabricItemGroupBuilder.build(
            new Identifier(ReworkedTools.MOD_ID, "excavator"), () -> new ItemStack(ModItems.EXCAVATORITEM));
}
