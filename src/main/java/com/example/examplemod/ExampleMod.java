package com.example.examplemod;

import com.example.examplemod.Npc.*;
import com.example.examplemod.commands.CommandObnulenie;
import com.example.examplemod.commands.SpawnCommand;
import com.example.examplemod.keys.CommonProxy;
import com.example.examplemod.keys.KeyEvent;
import com.example.examplemod.messages.NetworkHandler;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExampleMod.MODID, name = ExampleMod.NAME, version = ExampleMod.VERSION)
public class ExampleMod
{
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static ExampleMod instance;

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        NetworkHandler.registerMessages();
        // init custom npc
        int id = 1; // уникальный ID внутри твоего мода
        EntityRegistry.registerModEntity(
                new ResourceLocation(ExampleMod.MODID, "custom_npc"),
                EntityCustomNPC.class,
                "custom_npc",
                id,
                ExampleMod.instance,   // экземпляр мода
                64,                    // tracking range
                1,                     // update frequency
                true                   // send velocity updates
        );

        // render registry
        RenderingRegistry.registerEntityRenderingHandler(
                EntityCustomNPC.class,
                manager -> new RenderCustomNPC(manager)
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityCustomNPC2.class,
                manager -> new RenderCustomNPC2(manager)
        );
        RenderingRegistry.registerEntityRenderingHandler(
                EntityNpcTeleport.class,
                manager -> new RenderTeleportNpc(manager)
        );

        proxy.preInit();

        ModEntities.registerEntities();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        MinecraftForge.EVENT_BUS.register(new com.example.examplemod.EventHandler());
        MinecraftForge.EVENT_BUS.register(new BreakBlock());
        MinecraftForge.EVENT_BUS.register(new HudOverlay());
        MinecraftForge.EVENT_BUS.register(new WhenPlayerLogin());
        MinecraftForge.EVENT_BUS.register(new ZapretMobSpawn());
        MinecraftForge.EVENT_BUS.register(new ZapretDamage());
        MinecraftForge.EVENT_BUS.register(new DropCancel());

        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @SidedProxy(clientSide = "com.example.examplemod.keys.ClientProxy", serverSide = "com.example.examplemod.keys.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandObnulenie());
        event.registerServerCommand(new SpawnCommand());
    }
}
