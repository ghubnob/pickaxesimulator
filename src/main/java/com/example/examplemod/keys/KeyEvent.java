package com.example.examplemod.keys;

import com.example.examplemod.ClientData;
import com.example.examplemod.messages.MakeSound;
import com.example.examplemod.messages.NetworkHandler;
import com.example.examplemod.messages.OpenGuiMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import scala.tools.cmd.gen.AnyValReps;

public class KeyEvent {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.getEventKey())) {
            if (ClientProxy.openGuiUp.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new OpenGuiMessage(0));
            }
        }
    }
}
