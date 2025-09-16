package com.example.examplemod.Npc;

import com.example.examplemod.ClientData;
import com.example.examplemod.messages.MakeSound;
import com.example.examplemod.messages.NetworkHandler;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityCustomNPC2 extends EntityCreature {
    public EntityCustomNPC2(World world) {
        super(world);
        setSize(0.6f, 1.95f);
        enablePersistence();
    }

    @Override
    public boolean canDespawn() {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            int invMoney = 0;
            int moneyAmount = player.getEntityData().getInteger("moneyAmount");
            for (int i=0; i<player.inventory.getSizeInventory(); i++) {
                ItemStack currItem = player.inventory.getStackInSlot(i);
                if (currItem.getItem() == Item.getItemFromBlock(Blocks.STONE)) {
                    invMoney += currItem.getCount()*8*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.COBBLESTONE)) {
                    invMoney += (int)(currItem.getCount()*12*Math.max(player.getEntityData().getInteger("boostersMoney"), 1));
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.SANDSTONE)) {
                    invMoney += currItem.getCount()*25*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } /*else if (currItem.getItem() == Item.getItemFromBlock(Blocks.END_STONE)) {
                    invMoney += currItem.getCount()*60*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);*/
                else if (currItem.getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN)) {
                    invMoney += currItem.getCount()*350*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.NETHERRACK)) {
                    invMoney += currItem.getCount()*55*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.NETHER_BRICK)) {
                    invMoney += currItem.getCount()*87*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.RED_NETHER_BRICK)) {
                    invMoney += currItem.getCount()*135*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                } else if (currItem.getItem() == Item.getItemFromBlock(Blocks.CONCRETE)) {
                    invMoney += currItem.getCount()*675*Math.max(player.getEntityData().getInteger("boostersMoney"), 1);
                    player.inventory.deleteStack(currItem);
                }
            }
            player.getEntityData().setInteger("moneyAmount", moneyAmount+invMoney);
            ClientData.moneyAmount = player.getEntityData().getInteger("moneyAmount");
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
        return TextFormatting.DARK_AQUA+"Sell inventory";
    }

    @Override
    public boolean getAlwaysRenderNameTag() {
        return true;
    }
}
