package com.example.examplemod;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemCustomTreasure extends ItemArrow {
    public ItemCustomTreasure() {
        this.setMaxStackSize(64);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.GOLD + "Drill enchantment";
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String descStr = TextFormatting.DARK_RED + "Drill book.";
        tooltip.add("");
        tooltip.add(descStr);
        super.addInformation(stack,worldIn,tooltip,flagIn);
    }
}
