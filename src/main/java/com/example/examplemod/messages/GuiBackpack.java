package com.example.examplemod.messages;

import com.example.examplemod.ClientData;
import com.example.examplemod.ItemCustomPickaxe;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class GuiBackpack extends GuiContainer {
    private GuiButton upgradeButton;
    private GuiButton pickaxeButton;

    public GuiBackpack(ContainerBackpack container) {
        super(container);
        this.xSize = 196; // ширина окна
        this.ySize = 146; // высота окна
    }

    @Override
    public void initGui() {
        super.initGui();

        // позиция кнопки считается относительно верхнего левого угла GUI
        int centerX = (this.width - this.xSize) / 2 - 10;
        int centerY = (this.height - this.ySize) / 2 - 15;

        // создаём кнопку: id, x, y, ширина, высота, текст
        this.buttonList.clear();
        this.upgradeButton = new GuiButton(0, centerX+115, centerY+10, 80, 20, "Backpack");
        this.buttonList.add(upgradeButton);
        this.pickaxeButton = new GuiButton(1, centerX+27, centerY+10, 80, 20, "Pickaxe");
        this.buttonList.add(pickaxeButton);
        pickaxeButton.enabled = ClientData.pickaxeLevel != 15;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == upgradeButton) { NetworkHandler.INSTANCE.sendToServer(new BackpackButtonClicked(1)); }
        else if (button == pickaxeButton) { NetworkHandler.INSTANCE.sendToServer(new BackpackButtonClicked(2)); }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        int x = (this.width-this.xSize)/2;
        int y = (this.height-this.ySize)/2-20;
        drawGradientRect(x, y, x+this.xSize, y+this.ySize, 0xCC000000, 0xCC000000);
        this.drawDefaultBackground();
    }

    // add tooltips
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        if (pickaxeButton.isMouseOver()) {
            List<String> tooltip = new ArrayList<>();
            int p = ClientData.pickaxeLevel;
            tooltip.add(TextFormatting.AQUA + "upgrade your pickaxe here");
            int upgradeprice = 0;
            switch (p) {
                case 1: upgradeprice = 250;
                case 2: upgradeprice = 850;
                case 3: upgradeprice = 1800;
                case 4: upgradeprice = 5700;
                case 5: upgradeprice = 9400;
                case 6: upgradeprice = 24000;
                case 7: upgradeprice = 52000;
                case 8: upgradeprice = 86000;
                case 9: upgradeprice = 115000;
                case 10: upgradeprice = 168000;
                case 11: upgradeprice = 245000;
                case 12: upgradeprice = 380000;
                case 13: upgradeprice = 542000;
                case 14: upgradeprice = 1000000;
            }
            if (p<15) tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + Integer.toString(upgradeprice) + "$");
            else tooltip.add(TextFormatting.LIGHT_PURPLE + "You have maximum level of pickaxe");
            this.drawHoveringText(tooltip, mouseX, mouseY);
        }
        if (upgradeButton.isMouseOver()) {
            List<String> tooltip = new ArrayList<>();
            int p = ClientData.bll;
            tooltip.add(TextFormatting.AQUA + "upgrade your backpack capability here");
            int upgradeprice = 0;
            switch(p) {
                case 0: upgradeprice = 5;
                case 1: upgradeprice = 150;
                case 2: upgradeprice = 800;
                case 3: upgradeprice = 3500;
                case 4: upgradeprice = 9000;
                case 5: upgradeprice = 20000;
                case 6: upgradeprice = 45000;
                case 7: upgradeprice = 85000;
                case 8: upgradeprice = 130000;
                case 9: upgradeprice = 250000;
                case 10: upgradeprice = 500000;
            }
            if (p<11) tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + Integer.toString(upgradeprice) + "$");
            else tooltip.add(TextFormatting.LIGHT_PURPLE + "can't upgrade backpack more");
            this.drawHoveringText(tooltip, mouseX, mouseY);
        }
    }
}
