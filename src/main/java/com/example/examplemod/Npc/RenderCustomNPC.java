package com.example.examplemod.Npc;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomNPC extends RenderLiving<EntityCustomNPC> {
    private static final ResourceLocation TEX = new ResourceLocation("minecraft:textures/entity/villager/villager.png");

    public RenderCustomNPC(RenderManager mgr) {
        super(mgr, new ModelVillager(0.0f), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCustomNPC entity) {
        return TEX;
    }
}
