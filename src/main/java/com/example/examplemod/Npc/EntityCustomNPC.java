package com.example.examplemod.Npc;

import com.example.examplemod.ClientData;
import com.example.examplemod.messages.MakeSound;
import com.example.examplemod.messages.NetworkHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityCustomNPC extends EntityCreature {
    public EntityCustomNPC(World world) {
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
            int moneyAmount = player.getEntityData().getInteger("moneyAmount");
            player.getEntityData().setInteger("moneyAmount", moneyAmount+player.getEntityData().getInteger("backpackAmount"));
            ClientData.moneyAmount = player.getEntityData().getInteger("moneyAmount");
            player.getEntityData().setInteger("backpackAmount",0);
            ClientData.backpackAmount = 0;
            player.getEntityData().getInteger("playerLevel");
            NetworkHandler.INSTANCE.sendTo(new MakeSound(1), (EntityPlayerMP) player);
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
        return true;
    }

    @Override
    public String getCustomNameTag() {
        return "§2Sell backpack".substring(1);
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return true;
    }
}
