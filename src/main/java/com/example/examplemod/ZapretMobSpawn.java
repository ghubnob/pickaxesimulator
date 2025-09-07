package com.example.examplemod;

import com.example.examplemod.Npc.EntityCustomNPC;
import com.example.examplemod.Npc.EntityCustomNPC2;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ZapretMobSpawn {
    @SubscribeEvent
    public void onMobSpawn(LivingSpawnEvent.CheckSpawn event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer) && !(event.getEntityLiving() instanceof EntityCustomNPC) && !(event.getEntityLiving() instanceof EntityCustomNPC2)) {
            event.setResult(Event.Result.DENY);
        }
    }
}
