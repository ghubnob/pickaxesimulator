package com.example.examplemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ZapretDamage {
    @SubscribeEvent
    public void onPlayerDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            event.setCanceled(true);
        }
    }
}
