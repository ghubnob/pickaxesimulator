package com.example.examplemod.messages;

import com.example.examplemod.ExampleMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class OpenGuiMessage implements IMessage {
    public int modGui;

    public OpenGuiMessage() {}
    public OpenGuiMessage(int modGui) {this.modGui = modGui;}

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(modGui);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        modGui = buf.readInt();
    }

    public static class Handler implements IMessageHandler<OpenGuiMessage, IMessage> {
        @Override
        public IMessage onMessage(OpenGuiMessage message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> {
                player.openGui(ExampleMod.instance, message.modGui, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
            });

            return null;
        }
    }
}
