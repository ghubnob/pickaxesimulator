package com.example.examplemod.Npc;

import com.example.examplemod.ExampleMod;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {
    private static int id = 0;

    public static void registerEntities() {
        EntityRegistry.registerModEntity(
                new ResourceLocation(ExampleMod.MODID, "inv_seller"),
                EntityCustomNPC2.class,
                "InvSeller",
                id++, ExampleMod.instance,
                64, 1, true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(ExampleMod.MODID, "teleport_npc"),
                EntityNpcTeleport.class,
                "NpcTeleport",
                id++, ExampleMod.instance,
                64, 1, true
        );
    }
}
