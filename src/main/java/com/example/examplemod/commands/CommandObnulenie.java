package com.example.examplemod.commands;

import com.example.examplemod.ClientData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandObnulenie extends CommandBase {

    @Override
    public String getName() {
        return "makemenull";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/makemenull";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP)sender;
            player.setPositionAndUpdate(21.5, 67, -10.5);
            player.getEntityData().setInteger("playerLocation", 1);
            ClientData.playerLocation = 1;
            player.getEntityData().setInteger("moneyAmount", 0);
            ClientData.moneyAmount = 0;
            player.getEntityData().setInteger("backpackAmount", 0);
            ClientData.backpackAmount = 0;
            player.getEntityData().setInteger("backpackLimit", 5);
            ClientData.backpackLimit = 5;
            player.getEntityData().setInteger("bought1Level", 0);
            player.getEntityData().setInteger("bought2Level", 0);
            player.getEntityData().setInteger("bought3Level", 0);
            player.getEntityData().setInteger("bought4Level", 0);
            player.getEntityData().setInteger("bought5Level", 0);
            player.getEntityData().setInteger("backpackLimitLevel", 0);
            ClientData.bll = 0;
            player.inventory.clear();
            player.getEntityData().setInteger("pickaxeLevel", 1);
            ClientData.pickaxeLevel = 1;
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0; // all players
    }
}
