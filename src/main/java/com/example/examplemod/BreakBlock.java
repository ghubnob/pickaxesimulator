package com.example.examplemod;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,40, 3));
            return;
        }

        // spawn luckyblock with 1*luckyEnchLevel chance
        Random random = new Random();
        if (event.getPlayer().getHeldItemMainhand().getItem() == ModItems.CUSTOM_PICKAXE && random.nextInt(200) <= 1) {
            player.inventory.addItemStackToInventory(new ItemStack(ModItems.CUSTOM_TREASURE));
        }

        // zapret block amount > limit
        backpackAmount = event.getPlayer().getEntityData().getInteger("backpackAmount");
        if (backpackAmount >= backpackLimit) {
            event.getPlayer().getEntityData().setInteger("backpackAmount", backpackLimit);
            ClientData.backpackAmount = backpackLimit;
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,65, 3));
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
        event.getWorld().setBlockToAir(event.getPos());

        // respawn blocks
        RespawnHandler.queueBlock(event.getPos(), event.getState(), 400);
    }
}
