package net.amethyst.reworkedtools.item.custom;

import net.amethyst.reworkedtools.magna.item.ExcavatorItem;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModExcavator extends ExcavatorItem {
    private int breakRadius = 3;
    public ModExcavator(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int breakRadius) {
        super(toolMaterial, attackDamage, attackSpeed, settings, breakRadius);
    }
    @Override
    public int getRadius(ItemStack stack) {
        return breakRadius;
    }

    @Override
    public boolean playBreakEffects() {
        return false;
    }
}
