package com.example.examplemod.messages;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
    public static final int BACKPACK = 0;
    public static final int TELEPORT = 1;

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == BACKPACK) {
            // Серверная логика GUI: вернуть Container
            return new ContainerBackpack(player.inventory); // даже если пустой контейнер
        }
        if (id == TELEPORT) {
            return new ContainerTeleport(player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == BACKPACK) {
            // Клиентская часть GUI: вернуть GuiScreen/GuiContainer
            return new GuiBackpack(new ContainerBackpack(player.inventory));
        }
        if (id == TELEPORT) {
            return new GuiTeleport(new ContainerTeleport(player.inventory));
        }
        return null;
    }
}
