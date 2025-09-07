package com.example.examplemod.messages;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    public static final SimpleNetworkWrapper INSTANCE =
            NetworkRegistry.INSTANCE.newSimpleChannel("examplemod");

    private static int ID = 0;

    public static void registerMessages() {
        INSTANCE.registerMessage(MakeSound.Handler.class, MakeSound.class, ID++, Side.CLIENT);
        INSTANCE.registerMessage(OpenGuiMessage.Handler.class, OpenGuiMessage.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(BackpackButtonClicked.Handler.class, BackpackButtonClicked.class, ID++, Side.SERVER);
        INSTANCE.registerMessage(TeleportGuiButtonClicked.Handler.class, TeleportGuiButtonClicked.class, ID++, Side.SERVER);
        //INSTANCE.registerMessage(MessageText.Handler.class, MessageText.class, ID++, Side.CLIENT);
    }
}
