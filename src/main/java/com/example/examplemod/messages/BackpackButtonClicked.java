package com.example.examplemod.messages;

import com.example.examplemod.ClientData;
import com.example.examplemod.ItemCustomPickaxe;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import sun.nio.ch.Net;

public class BackpackButtonClicked implements IMessage {
    public int buttonNum;

    public BackpackButtonClicked() {}
    public BackpackButtonClicked(int buttonNum) {
        this.buttonNum = buttonNum;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(buttonNum);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        buttonNum = buf.readInt();
    }

    public static class Handler implements IMessageHandler<BackpackButtonClicked, IMessage> {
        @Override
        public IMessage onMessage(BackpackButtonClicked message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            int backpackLimit = player.getEntityData().getInteger("backpackLimit");
            int bll = player.getEntityData().getInteger("backpackLimitLevel");
            player.getServerWorld().addScheduledTask(() -> {
                switch (message.buttonNum) {
                    case 1: {
                        int money = player.getEntityData().getInteger("moneyAmount");
                        switch (bll) {
                            case 0: if (money >= 5) {
                                player.getEntityData().setInteger("backpackLimit", 100);
                                ClientData.backpackLimit = 100;
                                player.getEntityData().setInteger("moneyAmount", money-5);
                                ClientData.moneyAmount -= 5;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 1: if (money >= 150) {
                                player.getEntityData().setInteger("backpackLimit", 150);
                                ClientData.backpackLimit = 150;
                                player.getEntityData().setInteger("moneyAmount", money-150);
                                ClientData.moneyAmount -= 150;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 2: if (money >= 800) {
                                player.getEntityData().setInteger("backpackLimit", 350);
                                ClientData.backpackLimit = 350;
                                player.getEntityData().setInteger("moneyAmount", money-800);
                                ClientData.moneyAmount -= 800;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 3: if (money >= 3500) {
                                player.getEntityData().setInteger("backpackLimit", 650);
                                ClientData.backpackLimit = 650;
                                player.getEntityData().setInteger("moneyAmount", money-3500);
                                ClientData.moneyAmount -= 3500;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 4: if (money >= 9000) {
                                player.getEntityData().setInteger("backpackLimit", 1500);
                                ClientData.backpackLimit = 1500;
                                player.getEntityData().setInteger("moneyAmount", money-9000);
                                ClientData.moneyAmount -= 9000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 5: if (money >= 20000) {
                                player.getEntityData().setInteger("backpackLimit", 3500);
                                ClientData.backpackLimit = 3500;
                                player.getEntityData().setInteger("moneyAmount", money-20000);
                                ClientData.moneyAmount -= 20000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 6: if (money >= 45000) {
                                player.getEntityData().setInteger("backpackLimit", 8000);
                                ClientData.backpackLimit = 8000;
                                player.getEntityData().setInteger("moneyAmount", money-45000);
                                ClientData.moneyAmount -= 45000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 7: if (money >= 85000) {
                                player.getEntityData().setInteger("backpackLimit", 20000);
                                ClientData.backpackLimit = 20000;
                                player.getEntityData().setInteger("moneyAmount", money-85000);
                                ClientData.moneyAmount -= 85000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 8: if (money >= 130000) {
                                player.getEntityData().setInteger("backpackLimit", 45000);
                                ClientData.backpackLimit = 45000;
                                player.getEntityData().setInteger("moneyAmount", money-130000);
                                ClientData.moneyAmount -= 130000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 9: if (money >= 250000) {
                                player.getEntityData().setInteger("backpackLimit", 100000);
                                ClientData.backpackLimit = 100000;
                                player.getEntityData().setInteger("moneyAmount", money-250000);
                                ClientData.moneyAmount -= 250000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            case 10: if (money >= 500000) {
                                player.getEntityData().setInteger("backpackLimit", 250000);
                                ClientData.backpackLimit = 250000;
                                player.getEntityData().setInteger("moneyAmount", money-500000);
                                ClientData.moneyAmount -= 500000;
                                player.getEntityData().setInteger("backpackLimitLevel", bll+1);
                                ClientData.bll++;
                                break;
                            }
                            default: { player.sendMessage(new TextComponentString("can't upgrade backpack more")); NetworkHandler.INSTANCE.sendTo(new MakeSound(4), (EntityPlayerMP) player); }
                        }
                        break;
                    }
                    case 2: {
                        int p = ClientData.pickaxeLevel;
                        int money = player.getEntityData().getInteger("moneyAmount");
                        switch (p) {
                            case 1: if (money > 250) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 250);
                                ClientData.moneyAmount -= 250;
                                player.getEntityData().setInteger("pickaxeLevel", 2);
                                ClientData.pickaxeLevel = 2;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 2: if (money > 850) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 850);
                                ClientData.moneyAmount -= 850;
                                player.getEntityData().setInteger("pickaxeLevel", 3);
                                ClientData.pickaxeLevel = 3;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 3: if (money > 1800) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 1800);
                                ClientData.moneyAmount -= 1800;
                                player.getEntityData().setInteger("pickaxeLevel", 4);
                                ClientData.pickaxeLevel = 4;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 4: if (money > 5700) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 5700);
                                ClientData.moneyAmount -= 5700;
                                player.getEntityData().setInteger("pickaxeLevel", 5);
                                ClientData.pickaxeLevel = 5;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 5: if (money > 9400) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 9400);
                                ClientData.moneyAmount -= 9400;
                                player.getEntityData().setInteger("pickaxeLevel", 6);
                                ClientData.pickaxeLevel = 6;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 6: if (money > 24000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 24000);
                                ClientData.moneyAmount -= 24000;
                                player.getEntityData().setInteger("pickaxeLevel", 7);
                                ClientData.pickaxeLevel = 7;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 7: if (money > 52000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 52000);
                                ClientData.moneyAmount -= 52000;
                                player.getEntityData().setInteger("pickaxeLevel", 8);
                                ClientData.pickaxeLevel = 8;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 8: if (money > 86000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 86000);
                                ClientData.moneyAmount -= 86000;
                                player.getEntityData().setInteger("pickaxeLevel", 9);
                                ClientData.pickaxeLevel = 9;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 9: if (money > 115000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 115000);
                                ClientData.moneyAmount -= 115000;
                                player.getEntityData().setInteger("pickaxeLevel", 10);
                                ClientData.pickaxeLevel = 10;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 10: if (money > 168000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 168000);
                                ClientData.moneyAmount -= 168000;
                                player.getEntityData().setInteger("pickaxeLevel", 11);
                                ClientData.pickaxeLevel = 11;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 11: if (money > 245000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 245000);
                                ClientData.moneyAmount -= 245000;
                                player.getEntityData().setInteger("pickaxeLevel", 12);
                                ClientData.pickaxeLevel = 12;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 12: if (money > 380000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 380000);
                                ClientData.moneyAmount -= 380000;
                                player.getEntityData().setInteger("pickaxeLevel", 13);
                                ClientData.pickaxeLevel = 13;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 13: if (money > 542000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 542000);
                                ClientData.moneyAmount -= 542000;
                                player.getEntityData().setInteger("pickaxeLevel", 14);
                                ClientData.pickaxeLevel = 14;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(2), (EntityPlayerMP) player);
                                break;
                            }
                            case 14: if (money > 1000000) {
                                ItemCustomPickaxe.upLevel(player.inventory.getStackInSlot(0));
                                player.getEntityData().setInteger("moneyAmount", money - 1000000);
                                ClientData.moneyAmount -= 1000000;
                                player.getEntityData().setInteger("pickaxeLevel", 15);
                                ClientData.pickaxeLevel = 15;
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(3), (EntityPlayerMP) player);
                                break;
                            }
                            default: {
                                player.sendMessage(new TextComponentString("can't upgrade pickaxe more"));
                                NetworkHandler.INSTANCE.sendTo(new MakeSound(4), (EntityPlayerMP) player);
                            }
                        }
                    }
                }
            });
            return null;
        }
    }
}
