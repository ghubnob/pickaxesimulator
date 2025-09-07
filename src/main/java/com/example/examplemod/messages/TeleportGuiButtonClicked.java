package com.example.examplemod.messages;

import com.example.examplemod.ClientData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TeleportGuiButtonClicked implements IMessage {

    public TeleportGuiButtonClicked() {}

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<TeleportGuiButtonClicked, IMessage> {
        @Override
        public IMessage onMessage(TeleportGuiButtonClicked message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            int moneyAmount = player.getEntityData().getInteger("moneyAmount");
            int playerLocation = player.getEntityData().getInteger("playerLocation");
            player.getServerWorld().addScheduledTask(() -> {
                switch (playerLocation) {
                    case 1: { if (moneyAmount >= 5000 && player.getEntityData().getInteger("bought2Level") != 1) {
                        player.setPositionAndUpdate(21.5, 67, -46.5);
                        player.getEntityData().setInteger("moneyAmount", moneyAmount - 5000);
                        ClientData.moneyAmount = moneyAmount - 5000;
                        player.getEntityData().setInteger("bought2Level", 1);
                        player.getEntityData().setInteger("playerLocation", 2);
                        ClientData.playerLocation = 2;
                    }
                    else if (player.getEntityData().getInteger("bought2Level") == 1) { player.setPositionAndUpdate(21.5, 67, -46.5); player.getEntityData().setInteger("playerLocation", 2); ClientData.playerLocation = 2; }  break; }

                    case 2: { if (moneyAmount >= 20000 && player.getEntityData().getInteger("bought3Level") != 1) {
                        player.setPositionAndUpdate(21.5, 67, -78.5);
                        player.getEntityData().setInteger("moneyAmount", moneyAmount - 20000);
                        ClientData.moneyAmount = moneyAmount-20000;
                        player.getEntityData().setInteger("bought3Level", 1);
                        player.getEntityData().setInteger("playerLocation",3);
                        ClientData.playerLocation = 3;
                    }  /*nazad*/ else if (ClientData.npcTeleportClicked == -45) { player.setPositionAndUpdate(21.5, 67, -40.5); player.getEntityData().setInteger("playerLocation", 1); ClientData.playerLocation = 1; }
                    else if (player.getEntityData().getInteger("bought3Level") == 1 && ClientData.npcTeleportClicked == -73) { player.setPositionAndUpdate(21.5, 67, -78.5); player.getEntityData().setInteger("playerLocation", 3); ClientData.playerLocation = 3; }  break; }

                    case 3: { if (moneyAmount >= 55000 && player.getEntityData().getInteger("bought4Level") != 1) {
                        player.setPositionAndUpdate(21.5, 67, -110.5);
                        player.getEntityData().setInteger("moneyAmount", moneyAmount - 55000);
                        ClientData.moneyAmount = moneyAmount-55000;
                        player.getEntityData().setInteger("bought4Level", 1);
                        player.getEntityData().setInteger("playerLocation",4);
                        ClientData.playerLocation = 4;
                    }  /*nazad*/ else if (ClientData.npcTeleportClicked == -77) { player.setPositionAndUpdate(21.5, 67, -72.5); player.getEntityData().setInteger("playerLocation", 2); ClientData.playerLocation = 2;  }
                    else if (player.getEntityData().getInteger("bought4Level") == 1 && ClientData.npcTeleportClicked == -105) { player.setPositionAndUpdate(21.5, 67, -110.5); player.getEntityData().setInteger("playerLocation", 4); ClientData.playerLocation = 4; }  break; }

                    case 4: { if (moneyAmount >= 90000 && player.getEntityData().getInteger("bought5Level") != 1) {
                        player.setPositionAndUpdate(21.5, 67, -141.5);
                        player.getEntityData().setInteger("moneyAmount", moneyAmount - 90000);
                        ClientData.moneyAmount = moneyAmount-90000;
                        player.getEntityData().setInteger("bought5Level", 1);
                        player.getEntityData().setInteger("playerLocation",5);
                        ClientData.playerLocation = 5;
                    }  /*nazad*/ else if (ClientData.npcTeleportClicked == -109) { player.setPositionAndUpdate(21.5, 67, -104.5); player.getEntityData().setInteger("playerLocation", 3); ClientData.playerLocation = 3;  }
                    else if (player.getEntityData().getInteger("bought5Level") == 1 && ClientData.npcTeleportClicked == -136) { player.setPositionAndUpdate(21.5, 67, -141.5); player.getEntityData().setInteger("playerLocation", 5); ClientData.playerLocation = 5; }  break; }

                    case 5: { /*nazad*/ if (ClientData.npcTeleportClicked == -140) { player.setPositionAndUpdate(21.5, 67, -135.5); player.getEntityData().setInteger("playerLocation", 4); ClientData.playerLocation = 4; }  break; }

                    default: player.sendMessage(new TextComponentString("You have maximum level!"));
                }
            });
            return null;
        }
    }
}
