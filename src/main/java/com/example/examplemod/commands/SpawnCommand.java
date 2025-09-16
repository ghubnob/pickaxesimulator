package com.example.examplemod.commands;

import com.example.examplemod.ClientData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class SpawnCommand extends CommandBase {

    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/spawn";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP)sender;
            player.setPositionAndUpdate(21.5, 67, -10.5);
            player.getEntityData().setInteger("playerLocation", 1);
            ClientData.playerLocation = 1;
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // all players
    }
}
