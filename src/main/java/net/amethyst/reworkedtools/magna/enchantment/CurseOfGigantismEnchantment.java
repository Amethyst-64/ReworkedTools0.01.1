package net.amethyst.reworkedtools.magna.enchantment;

import net.amethyst.reworkedtools.magna.api.optional.MagnaOptionals;
import net.amethyst.reworkedtools.magna.api.optional.StackPredicate;
import net.amethyst.reworkedtools.magna.item.ExcavatorItem;
import net.amethyst.reworkedtools.magna.item.HammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CurseOfGigantismEnchantment extends Enchantment {

    public CurseOfGigantismEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return other == Enchantments.EFFICIENCY;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        List<StackPredicate> stackPredicates = MagnaOptionals.getValidForCurseOfGigantism();

        for(StackPredicate predicate : stackPredicates) {
            if(predicate.isValid(stack)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    }

    @Override
    public int getMaxPower(int level) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public boolean isCursed() {
        return true;
    }
}
