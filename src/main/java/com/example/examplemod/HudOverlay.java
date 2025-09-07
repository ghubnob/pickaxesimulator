package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HudOverlay {
    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            int screenWidth = event.getResolution().getScaledWidth();
            int screenHeight = event.getResolution().getScaledHeight();

            int hotbarX = (screenWidth / 2) - 91;
            int hotbarY = screenHeight - 33;

            int barX = hotbarX;
            int barY = hotbarY - 8;
            int barWidth = 182;
            int barHeight = 14;

            int current = ClientData.backpackAmount;
            int limit = Math.max(ClientData.backpackLimit, 1);
            int filledWidth = (int)((current / (float)limit) * barWidth);

            Gui.drawRect(barX, barY-3, barX + barWidth, barY + barHeight+2, 0xFF040404);
            filledWidth = Math.max(filledWidth, 4);
            Gui.drawRect(barX+2, barY, barX + filledWidth-2, barY + barHeight-1, 0xFF1899C7);

            // Отрисовка рюкзака (буквы)
            int backpackCountData = ClientData.backpackAmount;
            int backpackLimit = ClientData.backpackLimit;
            int backpackX = barX+53;
            int backpackY = barY+3;
            Minecraft.getMinecraft().fontRenderer.drawString("BackPack: " + Integer.toString(backpackCountData) + "/" + Integer.toString(backpackLimit), backpackX, backpackY, 0xFFDDDDDD);

            // Отрисовка монет
            int moneyAmount = ClientData.moneyAmount;
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Money: " + Integer.toString(moneyAmount),1,1,0xFFE800);

            //int playerLevel = ClientData.playerLevel;
            //Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Level: " + Integer.toString(playerLevel), 1, 30, 0x33AADD);

            int playerLocation = ClientData.playerLocation;
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("location: " + Integer.toString(playerLocation), 1, 10, 0xFF00FF);

            int pickaxeLevel = ClientData.pickaxeLevel;
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("pickaxe level: " + Integer.toString(pickaxeLevel), 1, 20, 0xBB00FF);

            //int posZNpc = ClientData.npcTeleportClicked;
            //Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("npc pos: " + Integer.toString(posZNpc), 1, 40, 0xFF00FF);
        }
    }
}