package com.example.examplemod.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MakeSound implements IMessage {
    int soundEvent;

    public MakeSound() {}
    public MakeSound(int soundEvent) {
        this.soundEvent = soundEvent;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.soundEvent);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.soundEvent = buf.readInt();
    }

    public static class Handler implements IMessageHandler<MakeSound, IMessage> {
        @Override
        public IMessage onMessage(MakeSound message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                EntityPlayer p1 = Minecraft.getMinecraft().player;
                if (p1 == null) return;
                switch (message.soundEvent) {
                    case 1: { Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_VILLAGER_YES, 1, 1); break; }
                    case 2: { Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1); break; }
                    case 3: { Minecraft.getMinecraft().player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1, 1); break; }
                    case 4: { Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_ANVIL_FALL, 1, 1); break; }
                }
            });
            return null;
        }
    }
}
