package com.davenonymous.libnonymous.utils;

import com.davenonymous.libnonymous.Libnonymous;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Camera;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

public class RotationTools {
    @OnlyIn(Dist.CLIENT)
    private static ResourceLocation arrowImage;

    @OnlyIn(Dist.CLIENT)
    public static void renderArrowOnGround(Vec3 hitPosition, BlockPos drawPosition, float partialTicks, PoseStack matrix) {
        Direction facing = RotationTools.getFacingByTriangle(hitPosition);

        RotationTools.TextureRotationList rotList = new RotationTools.TextureRotationList();
        switch (facing) {
            case SOUTH:
                break;
            case WEST:
                rotList.rotateFromStart();
                break;
            case NORTH:
                rotList.rotateFromStart();
                rotList.rotateFromStart();
                break;
            case EAST:
                rotList.rotateFromStart();
                rotList.rotateFromStart();
                rotList.rotateFromStart();
                break;
        }

        if(arrowImage == null) {
             arrowImage = new ResourceLocation(Libnonymous.MODID, "textures/particles/blockmarker.png");
        }

        Minecraft.getInstance().getTextureManager().bind(arrowImage);

        matrix.pushPose();

        // Shift back from camera
        Camera renderInfo = Minecraft.getInstance().gameRenderer.getMainCamera();
        matrix.translate(-renderInfo.getPosition().x(), -renderInfo.getPosition().y(), -renderInfo.getPosition().z());

        // Shift to actual block position
        matrix.translate(drawPosition.getX(), drawPosition.getY(), drawPosition.getZ());

        // Draw with 50% transparency
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        // Actually draw
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuilder();
        bufferbuilder.begin(7, DefaultVertexFormat.POSITION_TEX);
        //IRenderTypeBuffer buffer = IRenderTypeBuffer.getImpl(bufferbuilder);
        //Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(Blocks.DIAMOND_BLOCK.getDefaultState(), matrix, buffer, 15728880,  OverlayTexture.DEFAULT_LIGHT, EmptyModelData.INSTANCE);

        // TODO: Fix arrows drawing on the ground
        rotList.fillBufferBuilder(bufferbuilder, 0.0005d);

        tessellator.end();

        matrix.popPose();
    }

    public static Direction getFacingForPlayer(Level world, Player player) {
        float blockReachDistance = 6.0F;

        BlockHitResult trace = RaytraceHelper.rayTrace(world, player, blockReachDistance);
        if(trace == null) {
            return null;
        }

        Vec3 hitPosition = trace.getLocation();

        hitPosition = hitPosition.subtract(new Vec3(trace.getBlockPos().getX(), trace.getBlockPos().getY(), trace.getBlockPos().getZ()));
        hitPosition = hitPosition.subtract(0.5d, 0.5d, 0.5d);
        return getFacingByTriangle(hitPosition);
    }

    public static Direction getFacingByTriangle(Vec3 vec) {
        if(vec.z > 0) {
            if(vec.x < 0) {
                // Quadrant 1
                if(Math.abs(vec.x) < Math.abs(vec.z)) {
                    // Bottom
                    return Direction.SOUTH;
                } else {
                    // Left
                    return Direction.WEST;
                }
            } else {
                // Quadrant 2
                if(Math.abs(vec.x) > Math.abs(vec.z)) {
                    // Right
                    return Direction.EAST;
                } else {
                    // Bottom
                    return Direction.SOUTH;
                }
            }
        } else {
            if(vec.x < 0) {
                // Quadrant 3
                if(Math.abs(vec.x) < Math.abs(vec.z)) {
                    // Top
                    return Direction.NORTH;
                } else {
                    // Left
                    return Direction.WEST;
                }

            } else {
                // Quadrant 4
                if(Math.abs(vec.x) > Math.abs(vec.z)) {
                    // Right
                    return Direction.EAST;
                } else {
                    // Top
                    return Direction.NORTH;
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class TextureRotationList extends RotatingList<Tuple<Integer, Integer>> {
        public TextureRotationList() {
            this.add(new Tuple<>(0, 1));
            this.add(new Tuple<>(1, 1));
            this.add(new Tuple<>(1, 0));
            this.add(new Tuple<>(0, 0));
        }

        public void fillBufferBuilder(BufferBuilder buffer, double yLevel) {
            buffer.vertex(0, yLevel, 1).uv(this.get(0).getA(), this.get(0).getB()).endVertex();
            buffer.vertex(1, yLevel, 1).uv(this.get(1).getA(), this.get(1).getB()).endVertex();
            buffer.vertex(1, yLevel, 0).uv(this.get(2).getA(), this.get(2).getB()).endVertex();
            buffer.vertex(0, yLevel, 0).uv(this.get(3).getA(), this.get(3).getB()).endVertex();
        }
    }


    public static class RotatingList<T> extends ArrayList<T> {
        public void rotateFromStart() {
            T firstElement = this.get(0);
            this.remove(0);

            this.add(firstElement);
        }

        public void rotateFromEnd() {
            T lastElement = this.get(this.size()-1);
            this.remove(this.size()-1);
            this.add(0, lastElement);
        }
    }
}
