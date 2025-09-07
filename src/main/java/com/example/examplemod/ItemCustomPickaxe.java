package com.example.examplemod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


import javax.annotation.Nullable;
import java.util.List;

public class ItemCustomPickaxe extends ItemPickaxe {
    public ItemCustomPickaxe(ToolMaterial material) {
        super(material);
        this.setMaxDamage(0);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState block) {
        switch(getLevel(stack)) {
            case 1: return 1.f;
            case 2: return 2.f;
            case 3: return 4.f;
            case 4: return 8.f;
            case 5: return 16.f;
            case 6: return 32.f;
            case 7: return 64.f;
            case 8: return 128.f;
            case 9: return 256.f;
            case 10: return 512.f;
            case 11: return 1024.f;
            case 12: return 2048.f;
            case 13: return 4096.f;
            case 14: return 4096.f;
            case 15: return 8192.f;
        }
        return super.getDestroySpeed(stack, block);
    }

    public static int getLevel(ItemStack stack) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound tag = stack.getTagCompound();
        if (!tag.hasKey("pickaxeLevel")) {
            tag.setInteger("pickaxeLevel", 1);
        }
        return tag.getInteger("pickaxeLevel");
    }
    public static void upLevel(ItemStack stack) {
        if (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setInteger("pickaxeLevel", getLevel(stack)+1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        String descStr = "§4Pickaxe level: ";
        descStr = descStr.substring(1, descStr.length());
        tooltip.add("");
        tooltip.add(descStr + getLevel(stack));
        super.addInformation(stack,worldIn,tooltip,flagIn);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        int level = getLevel(stack);
        String baseName = "Your sexy pickaxe";
        switch (level) {
            case 1: return TextFormatting.BLUE + baseName;
            case 2: return TextFormatting.BLUE + baseName;
            case 3: return TextFormatting.BLUE + baseName;
            case 4: return TextFormatting.BLUE + baseName;
            case 5: return TextFormatting.BLUE + baseName;
            case 6: return TextFormatting.AQUA + baseName;
            case 7: return TextFormatting.AQUA + baseName;
            case 8: return TextFormatting.AQUA + baseName;
            case 9: return TextFormatting.AQUA + baseName;
            case 10: return TextFormatting.GREEN + baseName;
            case 11: return TextFormatting.GREEN + baseName;
            case 12: return TextFormatting.GREEN + baseName;
            case 13: return TextFormatting.GREEN + baseName;
            case 14: return TextFormatting.GREEN + baseName;
            default: return TextFormatting.GOLD + baseName + " [MAX]";
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        if (slot == EntityEquipmentSlot.MAINHAND) {
            return HashMultimap.create(); // clear tooltip
        }
        return super.getAttributeModifiers(slot, stack);
    }

}
