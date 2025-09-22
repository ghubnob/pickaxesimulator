package com.example.examplemod.messages;

import com.example.examplemod.ClientData;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public class GuiTeleport extends GuiContainer {
    private GuiButton levelButton;

    public GuiTeleport(ContainerTeleport container) {
        super(container);
        this.xSize = 150; // ширина окна
        this.ySize = 110; // высота окна
    }

    @Override
    public void initGui() {
        super.initGui();

        // позиция кнопки считается относительно верхнего левого угла GUI
        int centerX = (this.width - this.xSize) / 2 - 10;
        int centerY = (this.height - this.ySize) / 2 - 15;

        // создаём кнопку: id, x, y, ширина, высота, текст
        this.buttonList.clear();
        this.levelButton = new GuiButton(0, centerX+47, centerY+15, 80, 20, "Teleport");
        this.buttonList.add(levelButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == levelButton) { NetworkHandler.INSTANCE.sendToServer(new TeleportGuiButtonClicked()); }
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
        if (levelButton.isMouseOver()) {
            List<String> tooltip = new ArrayList<>();

            int p = ClientData.playerLocation;
            tooltip.add(TextFormatting.AQUA + "Teleport to the next location");
            tooltip.add("");
            switch(p) {
                case 1: { tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + "5.000$"); break; }
                case 2: { tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + "20.000$"); break; }
                case 3: { tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + "55.000$"); break; }
                case 4: { tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + "90.000$"); break; }
                case 5: { tooltip.add(TextFormatting.GOLD + Integer.toString(ClientData.moneyAmount) + "$/" + "150.000$"); break; }
            }
            this.drawHoveringText(tooltip, mouseX, mouseY);
        }
    }
}
