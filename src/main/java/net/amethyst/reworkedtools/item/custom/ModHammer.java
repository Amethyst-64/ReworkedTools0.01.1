package net.amethyst.reworkedtools.item.custom;


import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class ModHammer extends PickaxeItem {
    public ModHammer(ToolMaterial material,int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
