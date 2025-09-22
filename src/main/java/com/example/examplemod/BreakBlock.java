package com.example.examplemod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class BreakBlock {
    @SubscribeEvent
    public void onPlayerBreakBlock(BlockEvent.BreakEvent event) {
        int backpackAmount = event.getPlayer().getEntityData().getInteger("backpackAmount");
        int backpackLimit = event.getPlayer().getEntityData().getInteger("backpackLimit");
        int pickaxeLevel = ItemCustomPickaxe.getLevel(event.getPlayer().getHeldItemMainhand());
        EntityPlayer player = event.getPlayer();
        IBlockState block = event.getState();

        // off drop
        event.setExpToDrop(0);
        event.setCanceled(true);

        // zapret destroy bad blocks
        if (event.getState().getBlock() != Blocks.DIAMOND_BLOCK && event.getState().getBlock() != Blocks.SANDSTONE && event.getState().getBlock() != Blocks.END_STONE && event.getState().getBlock() != Blocks.COBBLESTONE && event.getState().getBlock() != Blocks.STONE && event.getState().getBlock() != Blocks.OBSIDIAN && event.getState().getBlock() != Blocks.NETHERRACK && event.getState().getBlock() != Blocks.NETHER_BRICK && event.getState().getBlock() != Blocks.RED_NETHER_BRICK && event.getState().getBlock() != Blocks.CONCRETE) {
            return;
        }

        // give treasure with 1*luckyEnchLevel chance
        Random random = new Random();
        if (event.getPlayer().getHeldItemMainhand().getItem() == ModItems.CUSTOM_PICKAXE && random.nextInt(200) <= 1 && !event.getPlayer().inventory.hasItemStack(new ItemStack(ModItems.CUSTOM_TREASURE))) {
            //event.getWorld().setBlockState(event.getPos(), ModBlocks)
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.CUSTOM_TREASURE));
        }

        // zapret block amount > limit
        backpackAmount = event.getPlayer().getEntityData().getInteger("backpackAmount");
        if (backpackAmount >= backpackLimit) {
            event.getPlayer().getEntityData().setInteger("backpackAmount", backpackLimit);
            ClientData.backpackAmount = backpackLimit;
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,40, 3));
            return;
        }

        // giving blocks to backpack
        int playerLoc = player.getEntityData().getInteger("playerLocation");
        event.getPlayer().getEntityData().setInteger("backpackAmount", backpackAmount+player.getEntityData().getInteger("playerLocation")*playerLoc*playerLoc*playerLoc);
        ClientData.backpackAmount = event.getPlayer().getEntityData().getInteger("backpackAmount");

        // add block to inv
        ItemStack blockToInv = new ItemStack(block.getBlock());
        player.inventory.addItemStackToInventory(blockToInv);

        // delete block
        event.getWorld().destroyBlock(event.getPos(), false);

        // respawn blocks
        RespawnHandler.queueBlock(event.getPos(), event.getState(), 400);

        // drill moves
        if (!event.getPlayer().inventory.hasItemStack(new ItemStack(ModItems.CUSTOM_TREASURE))) return;
        if (true) {
            BlockPos drillBlock1Pos = event.getPos().add(1, 0, 0);
            BlockPos drillBlock2Pos = event.getPos().add(-1, 0, 0);
            BlockPos drillBlock3Pos = event.getPos().add(0, 0, 1);
            BlockPos drillBlock4Pos = event.getPos().add(0, 0, -1);
            BlockPos drillBlock5Pos = event.getPos().add(0, -1, 0);
            IBlockState drillBlock1 = event.getWorld().getBlockState(drillBlock1Pos);
            IBlockState drillBlock2 = event.getWorld().getBlockState(drillBlock2Pos);
            IBlockState drillBlock3 = event.getWorld().getBlockState(drillBlock3Pos);
            IBlockState drillBlock4 = event.getWorld().getBlockState(drillBlock4Pos);
            IBlockState drillBlock5 = event.getWorld().getBlockState(drillBlock5Pos);

            // delete drill blocks
            if (true) {
                if (drillBlock1.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock1.getBlock() != Blocks.STAINED_GLASS)
                    event.getWorld().destroyBlock(drillBlock1Pos, false);
                if (drillBlock2.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock2.getBlock() != Blocks.STAINED_GLASS)
                    event.getWorld().destroyBlock(drillBlock2Pos, false);
                if (drillBlock3.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock3.getBlock() != Blocks.STAINED_GLASS)
                    event.getWorld().destroyBlock(drillBlock3Pos, false);
                if (drillBlock4.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock4.getBlock() != Blocks.STAINED_GLASS)
                    event.getWorld().destroyBlock(drillBlock4Pos, false);
                if (drillBlock5.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock5.getBlock() != Blocks.STAINED_GLASS)
                    event.getWorld().destroyBlock(drillBlock5Pos, false);
            }

            // add drill blocks to inv
            if (true) {
                ItemStack drillBlock1inv = new ItemStack(drillBlock1.getBlock());
                if (drillBlock1.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock1.getBlock() != Blocks.STAINED_GLASS) player.inventory.addItemStackToInventory(drillBlock1inv);
                ItemStack drillBlock2inv = new ItemStack(drillBlock2.getBlock());
                if (drillBlock2.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock2.getBlock() != Blocks.STAINED_GLASS) player.inventory.addItemStackToInventory(drillBlock2inv);
                ItemStack drillBlock3inv = new ItemStack(drillBlock3.getBlock());
                if (drillBlock3.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock3.getBlock() != Blocks.STAINED_GLASS) player.inventory.addItemStackToInventory(drillBlock3inv);
                ItemStack drillBlock4inv = new ItemStack(drillBlock4.getBlock());
                if (drillBlock4.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock4.getBlock() != Blocks.STAINED_GLASS) player.inventory.addItemStackToInventory(drillBlock4inv);
                ItemStack drillBlock5inv = new ItemStack(drillBlock5.getBlock());
                if (drillBlock5.getBlock() != Blocks.QUARTZ_BLOCK && drillBlock5.getBlock() != Blocks.STAINED_GLASS) player.inventory.addItemStackToInventory(drillBlock5inv);
            }

            // respawn drill blocks
            if (true) {
                RespawnHandler.queueBlock(drillBlock1Pos, drillBlock1, 400);
                RespawnHandler.queueBlock(drillBlock2Pos, drillBlock2, 400);
                RespawnHandler.queueBlock(drillBlock3Pos, drillBlock3, 400);
                RespawnHandler.queueBlock(drillBlock4Pos, drillBlock4, 400);
                RespawnHandler.queueBlock(drillBlock5Pos, drillBlock5, 400);
            }
        }
    }
}
