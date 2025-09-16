package com.example.examplemod.Npc;

import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTeleportNpc extends RenderLiving<EntityNpcTeleport> {
    private static final ResourceLocation TEX3 = new ResourceLocation("minecraft:textures/entity/villager/villager.png");

    public RenderTeleportNpc(RenderManager manager) {
        super(manager, new ModelVillager(0.0f), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityNpcTeleport entity) {
        return TEX3;
    }
}
