package com.example.examplemod;

import com.example.examplemod.Npc.EntityCustomNPC;
import com.example.examplemod.Npc.EntityCustomNPC2;
import com.example.examplemod.Npc.EntityNpcTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class WhenPlayerLogin {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;

        player.getEntityData().setInteger("playerLocation", 1);
        ClientData.playerLocation = 1;

        if (!world.isRemote) {
            // other inf
            if (!player.inventory.hasItemStack(new ItemStack(ModItems.CUSTOM_PICKAXE))) player.inventory.addItemStackToInventory(new ItemStack(ModItems.CUSTOM_PICKAXE));
            ClientData.pickaxeLevel = player.getEntityData().getInteger("pickaxeLevel");
            player.setPositionAndUpdate(21.5, 67, -10.5);
            ClientData.backpackAmount = player.getEntityData().getInteger("backpackAmount");
            ClientData.backpackLimit = player.getEntityData().getInteger("backpackLimit");
            ClientData.moneyAmount = player.getEntityData().getInteger("moneyAmount");
            ClientData.bll = player.getEntityData().getInteger("backpackLimitLevel");
        }
    }
}
