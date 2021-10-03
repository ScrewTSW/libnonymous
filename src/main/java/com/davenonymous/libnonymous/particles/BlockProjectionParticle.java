package com.davenonymous.libnonymous.particles;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.BlockParticleOption;

public class BlockProjectionParticle extends Particle {
    BlockParticleOption data;

    public BlockProjectionParticle(ClientLevel worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, BlockParticleOption data) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        this.xd = 0;
        this.yd = 0;
        this.zd = 0;

        this.data = data;
        this.lifetime = 200;
        this.hasPhysics = false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(VertexConsumer buffer, Camera renderInfo, float partialTicks) {
        // TODO: Fix block particle rendering
        /*
        RenderSystem.pushMatrix();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getInstance().getBlockRendererDispatcher();


        float x = (float)(prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
        float y = (float)(prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
        float z = (float)(prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

        RenderSystem.translated(x, y+0.01d, z);

        RenderSystem.scaled(0.98f, 0.98f, 0.98f);
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 0.2f);
        // Aaaand render
        RenderSystem.enableBlend();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        RenderSystem.disableAlphaTest();
        renderLayer(blockrendererdispatcher, buffer, BlockRenderLayer.SOLID, this.world, data.getBlockState(), this.world.rand);
        RenderSystem.enableAlphaTest();
        renderLayer(blockrendererdispatcher, buffer, BlockRenderLayer.CUTOUT_MIPPED, this.world, data.getBlockState(), this.world.rand);
        renderLayer(blockrendererdispatcher, buffer, BlockRenderLayer.CUTOUT, this.world, data.getBlockState(), this.world.rand);
        RenderSystem.shadeModel(GL11.GL_FLAT);
        renderLayer(blockrendererdispatcher, buffer, BlockRenderLayer.TRANSLUCENT, this.world, data.getBlockState(), this.world.rand);

        tessellator.draw();

        RenderSystem.popMatrix();
        */
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    /*
    private static void renderLayer(BlockRendererDispatcher brd, BufferBuilder buffer, BlockRenderLayer renderLayer, IEnviromentBlockReader treeWorld, BlockState state, Random rand) {
        if (!state.getBlock().canRenderInLayer(state, renderLayer)) {
            return;
        }

        ForgeHooksClient.setRenderLayer(renderLayer);
        try {

            brd.renderBlock(state, new BlockPos(0, 0, 0), treeWorld, buffer, rand, EmptyModelData.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ForgeHooksClient.setRenderLayer(null);

    }
    */
}
