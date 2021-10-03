package com.davenonymous.libnonymous.gui.framework.widgets;

import com.davenonymous.libnonymous.gui.framework.GUIHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;

import java.awt.Color;

public class WidgetImage extends Widget {
    ResourceLocation image;
    float textureWidth = 16.0f;
    float textureHeight = 16.0f;
    Color color;
    float alpha = 1.0f;

    public WidgetImage(ResourceLocation image) {
        this.image = image;
    }

    public WidgetImage setTextureSize(float width, float height) {
        this.textureWidth = width;
        this.textureHeight = height;
        return this;
    }

    public WidgetImage setAlpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public WidgetImage setColor(Color color) {
        this.alpha = color.getAlpha() / 255;
        this.color = color;
        return this;
    }

    public WidgetImage resetColor() {
        this.alpha = 1.0f;
        this.color = null;
        return this;
    }

    public void draw(Screen screen) {
        RenderSystem.pushMatrix();
        RenderSystem.enableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.translatef(0.0f, 0.0f, 2.0f);

        screen.getMinecraft().getTextureManager().bindForSetup(image);

        // Draw the image
        if(color == null) {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        } else {
            float[] pColors = color.getRGBColorComponents(null);
            float pA = alpha;
            float pR = pColors[0];
            float pG = pColors[1];
            float pB = pColors[2];
            RenderSystem.setShaderColor(pR, pG, pB, pA);
        }

        actuallyDraw();

        //RenderSystem.clearCurrentColor();
        RenderSystem.disableBlend();
        RenderSystem.disableAlphaTest();

        RenderSystem.popMatrix();
    }

    protected void actuallyDraw() {
        GUIHelper.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width*2, height*2, textureWidth, textureHeight);
    }
}
