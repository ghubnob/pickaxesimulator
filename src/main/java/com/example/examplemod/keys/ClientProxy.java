package com.example.examplemod.keys;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.messages.GuiHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
    public static KeyBinding openGuiUp;

    @Override
    public void init() {
        openGuiUp = new KeyBinding("key.examplemod.open_gui_up", Keyboard.KEY_X, "key.categories.inventory");
        ClientRegistry.registerKeyBinding(openGuiUp);

        super.init();
        MinecraftForge.EVENT_BUS.register(new KeyEvent());
        NetworkRegistry.INSTANCE.registerGuiHandler(ExampleMod.instance, new GuiHandler());
    }
}
