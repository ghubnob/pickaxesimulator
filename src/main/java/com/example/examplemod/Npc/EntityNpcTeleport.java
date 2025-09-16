package com.example.examplemod.Npc;

import com.example.examplemod.ClientData;
import com.example.examplemod.messages.MakeSound;
import com.example.examplemod.messages.NetworkHandler;
import com.example.examplemod.messages.OpenGuiMessage;
import com.example.examplemod.messages.TeleportGuiButtonClicked;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityNpcTeleport extends EntityCreature {
    public EntityNpcTeleport(World world) {
        super(world);
        setSize(0.6F, 1.95F);
        enablePersistence();
        // или альтернативно:
        // this.persistenceRequired = true;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            ClientData.npcTeleportClicked = (int)this.posZ;
            NetworkHandler.INSTANCE.sendToServer(new OpenGuiMessage(1));
        }
        return true;
    }

    @Override
    public void onLivingUpdate() {
        this.motionX = 0;
        this.motionY = 0;
        this.motionZ = 0;
        super.onLivingUpdate();
        EntityPlayer nearest = this.world.getClosestPlayerToEntity(this, 20.0D); // радиус обзора
        if (nearest != null) {
            this.getLookHelper().setLookPositionWithEntity(nearest, 30.0F, 30.0F);
        }
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public String getCustomNameTag() {
        return TextFormatting.AQUA+"Teleport".substring(1);
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return true;
    }
}
