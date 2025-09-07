package com.example.examplemod.Npc;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomNPC2 extends RenderLiving<EntityCustomNPC2> {
    private static final ResourceLocation TEX2 = new ResourceLocation("minecraft:textures/entity/villager/villager.png");

    public RenderCustomNPC2(RenderManager manager) {
        super(manager, new ModelVillager(0.0f), 0.5f);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCustomNPC2 entity) {
        return TEX2;
    }
}
