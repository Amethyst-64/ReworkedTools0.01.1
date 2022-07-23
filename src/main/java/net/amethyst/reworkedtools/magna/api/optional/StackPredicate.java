package net.amethyst.reworkedtools.magna.api.optional;

import net.minecraft.item.ItemStack;

public interface StackPredicate {
    boolean isValid(ItemStack stack);
}
