package com.example.examplemod;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventHandler {
    int tickCount = 0;
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        tickCount = event.player.getEntityData().getInteger("tickCount");

        // ne peretaskivai kirku
        InventoryPlayer inv = event.player.inventory;
        for (int i=0; i<inv.getSizeInventory(); i++) {
            ItemStack invStack = inv.getStackInSlot(i);
            if (!invStack.isEmpty() && invStack.getItem() instanceof ItemCustomPickaxe) {
                if (i != 0) {
                    if (inv.getStackInSlot(0).isEmpty()) {
                        inv.setInventorySlotContents(0, invStack);
                    }
                    inv.setInventorySlotContents(i, ItemStack.EMPTY);
                    inv.markDirty();
                }
            }
        }

        event.player.getEntityData().setInteger("tickCount", tickCount+1);
    }
}