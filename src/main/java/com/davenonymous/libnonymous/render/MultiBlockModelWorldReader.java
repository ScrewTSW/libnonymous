package com.davenonymous.libnonymous.render;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.data.worldgen.biome.Biomes;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.lighting.LevelLightEngine;

import javax.annotation.Nullable;

public class MultiBlockModelWorldReader implements BiomeManager.NoiseBiomeSource, BlockAndTintGetter {
    private MultiblockBlockModel model;

    private LevelReader blockWorld;
    private BlockPos blockPos;

    public MultiBlockModelWorldReader(MultiblockBlockModel model) {
        this.model = model;
    }

    public MultiBlockModelWorldReader(MultiblockBlockModel model, LevelReader blockWorld, BlockPos blockPos) {
        this.model = model;
        this.blockWorld = blockWorld;
        this.blockPos = blockPos;
    }

    public Biome getBiome(BlockPos pos) {
        return blockWorld == null ? Biomes.PLAINS : blockWorld.getBiome(blockPos);
    }


    public LevelReader getContextWorld() {
        return blockWorld;
    }

    public BlockPos getContextPos() {
        return blockPos;
    }

    @Override
    public float getShade(Direction p_230487_1_, boolean p_230487_2_) {
        return 0;
    }

    @Override
    public LevelLightEngine getLightEngine() {
        // TODO: blockworld might be null, what lightmanager do we use then?
        return blockWorld.getLightEngine();
    }

    @Override
    public int getBlockTint(BlockPos blockPosIn, ColorResolver colorResolverIn) {
        return 0;
    }

    @Override
    public int getBrightness(LightLayer type, BlockPos pos) {
        return blockWorld == null ? type.surrounding : blockWorld.getBrightness(type, blockPos);
    }

    @Nullable
    @Override
    public BlockEntity getBlockEntity(BlockPos pos) {
        return null;
    }

    @Override
    public BlockState getBlockState(BlockPos pos) {
        if(model.blocks.get(pos) != null) {
            return model.blocks.get(pos);
        }
        return Blocks.AIR.defaultBlockState();
    }

    @Override
    public FluidState getFluidState(BlockPos pos) {
        // TODO: ???
        return null;
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return null;
    }
}
