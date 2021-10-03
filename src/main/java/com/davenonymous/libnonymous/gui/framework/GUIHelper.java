package com.davenonymous.libnonymous.gui.framework;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.Font;

import net.minecraft.client.gui.screens.Screen;
import com.mojang.blaze3d.vertex.BufferBuilder;

import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.TextComponent;
import org.lwjgl.opengl.GL11;

public class GUIHelper {
    
    private static final Minecraft minecraft = Minecraft.getInstance();
    
    public static void drawSplitStringCentered(PoseStack matrixStack, String str, Screen screen, int x, int y, int width, int color) {
        Font renderer = minecraft.font;
        int yOffset = 0;
        for(FormattedCharSequence row : renderer.split(new TextComponent(str), width)) {
            GuiComponent.drawCenteredString(matrixStack, renderer, row.toString(), x + width/2, y + yOffset, color);
            yOffset += renderer.lineHeight;
        }
    }

    public static void drawColoredRectangle(int x, int y, int width, int height, int argb) {
        int a = (argb >> 24) & 0xFF;
        int r = (argb >> 16) & 0xFF;
        int g = (argb >> 8) & 0xFF;
        int b = (argb & 0xFF);
        drawColoredRectangle(x, y, width, height, r, g, b, a);
    }

    public static void drawColoredRectangle(int x, int y, int width, int height, int red, int green, int blue, int alpha) {
        double zLevel = 0.0f;

        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.disableAlphaTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.shadeModel(GL11.GL_SMOOTH);

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder renderer = tessellator.getBuilder();
        renderer.begin(GL11.GL_QUADS, DefaultVertexFormat.POSITION_COLOR);
        renderer.vertex((x + 0), (y + 0), zLevel).color(red, green, blue, alpha).endVertex();
        renderer.vertex((x + 0), (y + height), zLevel).color(red, green, blue, alpha).endVertex();
        renderer.vertex((x + width), (y + height), zLevel).color(red, green, blue, alpha).endVertex();
        renderer.vertex((x + width), (y + 0), zLevel).color(red, green, blue, alpha).endVertex();
        tessellator.end();

        RenderSystem.shadeModel(GL11.GL_FLAT);
        RenderSystem.disableBlend();
        RenderSystem.enableAlphaTest();
        RenderSystem.enableTexture();

    }

    public static void drawStretchedTexture(int x, int y, int width, int height, int textureX, int textureY, int textureWidth, int textureHeight) {
        float f =  0.00390625F;
        double zLevel = 0.0f;

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder
                .vertex((double)(x + 0), (double)(y + height), zLevel)
                .uv(((float)(textureX + 0) * f), ((float)(textureY + textureHeight) * f))
                .endVertex();

        bufferbuilder
                .vertex((double)(x + width), (double)(y + height), zLevel)
                .uv(((float)(textureX + textureWidth) * f), ((float)(textureY + textureHeight) * f))
                .endVertex();

        bufferbuilder
                .vertex((double)(x + width), (double)(y + 0), zLevel)
                .uv(((float)(textureX + textureWidth) * f), ((float)(textureY + 0) * f))
                .endVertex();

        bufferbuilder
                .vertex((double)(x + 0), (double)(y + 0), zLevel)
                .uv(((float)(textureX + 0) * f), ((float)(textureY + 0) * f))
                .endVertex();

        tessellator.end();
    }

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight)
    {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex((double)x, (double)(y + height), 0.0D).uv((u * f), ((v + (float)height) * f1)).endVertex();
        bufferbuilder.vertex((double)(x + width), (double)(y + height), 0.0D).uv(((u + (float)width) * f), ((v + (float)height) * f1)).endVertex();
        bufferbuilder.vertex((double)(x + width), (double)y, 0.0D).uv(((u + (float)width) * f), (v * f1)).endVertex();
        bufferbuilder.vertex((double)x, (double)y, 0.0D).uv((u * f), (v * f1)).endVertex();
        tessellator.end();
    }
}
