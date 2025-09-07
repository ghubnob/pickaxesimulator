package com.example.examplemod.messages;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerTeleport extends Container {
    public ContainerTeleport(InventoryPlayer playerInventory) { /* не добавляем слотов */ }
    @Override public boolean canInteractWith(EntityPlayer playerIn) { return true; }
}