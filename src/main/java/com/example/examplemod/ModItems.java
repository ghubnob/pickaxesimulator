package com.example.examplemod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = ExampleMod.MODID)
public class ModItems {
    public static final Item CUSTOM_PICKAXE = new ItemCustomPickaxe(Item.ToolMaterial.IRON)
            .setUnlocalizedName("CUSTOM_PICKAXE")
            .setRegistryName("CUSTOM_PICKAXE")
            .setCreativeTab(CreativeTabs.TOOLS);
    public static final Item CUSTOM_TREASURE = new ItemCustomTreasure()
            .setUnlocalizedName("CUSTOM_TREASURE")
            .setRegistryName("CUSTOM_TREASURE")
            .setCreativeTab(CreativeTabs.REDSTONE);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
        event.getRegistry().register(CUSTOM_PICKAXE);
        event.getRegistry().register(CUSTOM_TREASURE);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
            ModelLoader.setCustomMeshDefinition(CUSTOM_PICKAXE, stack -> {
                int lvl = ClientData.pickaxeLevel;
                if (lvl >= 15) return new ModelResourceLocation("minecraft:golden_pickaxe", "inventory");
                if (lvl > 10) return new ModelResourceLocation("minecraft:diamond_pickaxe", "inventory");
                if (lvl > 6) return new ModelResourceLocation("minecraft:iron_pickaxe", "inventory");
                if (lvl > 2) return new ModelResourceLocation("minecraft:stone_pickaxe", "inventory");
                if (lvl > 0) return new ModelResourceLocation("minecraft:wooden_pickaxe", "inventory");
                return new ModelResourceLocation("minecraft:apple", "inventory");
            });

        //ModelLoader.setCustomModelResourceLocation(CUSTOM_PICKAXE,0,new ModelResourceLocation("minecraft:iron_pickaxe", "inventory"));
        ModelLoader.setCustomModelResourceLocation(CUSTOM_TREASURE,0,new ModelResourceLocation("minecraft:enchanted_book", "inventory"));
    }
}
