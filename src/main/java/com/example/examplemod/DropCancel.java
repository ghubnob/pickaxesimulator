package com.example.examplemod;

import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DropCancel {
    @SubscribeEvent
    public void onPlayerDrops(ItemTossEvent event) {
        event.setCanceled(true);
        event.getPlayer().inventory.addItemStackToInventory(event.getEntityItem().getItem());
    }
}