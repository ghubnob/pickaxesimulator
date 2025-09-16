package com.example.examplemod.modblocks;

import com.example.examplemod.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTreasureChest extends Block {
    public BlockTreasureChest() {
        super(Material.PORTAL); // материал для прочности
        setRegistryName("treasure_chest");
        setUnlocalizedName("treasure_chest");
        setHardness(2.0F);
        setResistance(10.0F);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state,
                                    EntityPlayer player, EnumHand hand, EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.CUSTOM_TREASURE));
            world.setBlockToAir(pos);
        }
        return true;
    }
}
