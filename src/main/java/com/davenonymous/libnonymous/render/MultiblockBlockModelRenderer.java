package com.davenonymous.libnonymous.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.AmbientOcclusionStatus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;
import java.util.Set;

public class MultiblockBlockModelRenderer {
    
    private static final Random rand = new Random();
    private static final Minecraft minecraft = Minecraft.getInstance();

    public static void renderModel(MultiblockBlockModel model, PoseStack matrix, MultiBufferSource buffer, int light, int overlay) {
        MultiBlockModelWorldReader modelWorld = new MultiBlockModelWorldReader(model);
        renderModel(model, modelWorld, matrix, buffer, light, overlay);
    }

    public static void renderModel(MultiblockBlockModel model, PoseStack matrix, MultiBufferSource buffer, int light, int overlay, LevelReader worldContext, BlockPos blockContext) {
        MultiBlockModelWorldReader modelWorld = new MultiBlockModelWorldReader(model, worldContext, blockContext);
        renderModel(model, modelWorld, matrix, buffer, light, overlay);
    }

    private static void renderModel(MultiblockBlockModel model, MultiBlockModelWorldReader modelWorld, PoseStack matrix, MultiBufferSource buffer, int light, int overlay) {
        BlockRenderDispatcher brd = Minecraft.getInstance().getBlockRenderer();

        // Aaaand render
        Set<BlockPos> renderPositions = model.getRelevantPositions();
        for (BlockPos pos : renderPositions) {
            BlockState state = model.blocks.get(pos);

            try {
                matrix.pushPose();
                matrix.translate(pos.getX(), pos.getY(), pos.getZ());

                if(modelWorld.getContextWorld() != null && modelWorld.getContextPos() != null) {
                    // TODO: Hacks hack hacks. Clean this up, make ambient occlusion work, use proper world references
                    AmbientOcclusionStatus before = minecraft.options.ambientOcclusion;
                    minecraft.options.ambientOcclusion = AmbientOcclusionStatus.OFF;
                    brd.renderModel(state, modelWorld.getContextPos(), modelWorld.getContextWorld(), matrix, buffer.getBuffer(RenderType.cutout()), false, rand, EmptyModelData.INSTANCE);
                    minecraft.options.ambientOcclusion = before;
                } else {
                    brd.renderBlock(state, matrix, buffer, light, overlay, EmptyModelData.INSTANCE);
                }
                matrix.popPose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
