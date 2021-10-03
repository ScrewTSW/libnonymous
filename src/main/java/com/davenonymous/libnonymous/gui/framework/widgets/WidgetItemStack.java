package com.davenonymous.libnonymous.gui.framework.widgets;

import com.davenonymous.libnonymous.gui.framework.GUI;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.fmlclient.gui.GuiUtils;

import java.util.Collections;

public class WidgetItemStack extends WidgetWithValue<ItemStack> {
    boolean drawSlot = false;

    public WidgetItemStack(ItemStack stack) {
        this.setSize(16, 16);
        this.setValue(stack);
    }

    public WidgetItemStack(ItemStack stack, boolean drawSlot) {
        this(stack);
        this.drawSlot = drawSlot;
    }

    public void setValue(ItemStack stack) {
        if(!stack.isEmpty()) {
            TooltipFlag.Default tooltipFlag = Minecraft.getInstance().options.advancedItemTooltips ? TooltipFlag.Default.ADVANCED : TooltipFlag.Default.NORMAL;
            this.setTooltipLines(stack.getTooltipLines(Minecraft.getInstance().player, tooltipFlag));
        } else {
            this.setTooltipLines(Collections.emptyList());
        }

        super.setValue(stack);
    }

    @Override
    public void draw(Screen screen, PoseStack matrixStack) {
        super.draw(screen, matrixStack);

        if(drawSlot) {
            this.drawSlot(screen);
        }

        if(this.value == null || this.value.isEmpty()) {
            return;
        }

        RenderSystem.pushMatrix();

        RenderSystem.disableLighting();
        RenderSystem.color3f(1F, 1F, 1F); //Forge: Reset color in case Items change it.
        RenderSystem.enableBlend(); //Forge: Make sure blend is enabled else tabs show a white border.

        double xScale = this.width / 16.0f;
        double yScale = this.height / 16.0f;

        RenderSystem.scaled(xScale, yScale, 1.0d);

        Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(this.value, 0, 0);
        Lighting.turnOff();

        RenderSystem.popMatrix();
    }

    private void drawSlot(Screen screen) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1f);
        screen.getMinecraft().textureManager.bindForSetup(GUI.tabIcons);

        int texOffsetY = 84;
        int texOffsetX = 84;

        GuiUtils.drawTexturedModalRect(-1, -1, texOffsetX, texOffsetY, 18, 18, 0.0f);

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }
}
