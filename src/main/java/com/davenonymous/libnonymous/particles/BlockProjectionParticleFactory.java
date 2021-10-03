package com.davenonymous.libnonymous.particles;

import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.BlockParticleOption;

import javax.annotation.Nullable;

public class BlockProjectionParticleFactory implements ParticleProvider<BlockParticleOption> {
    @Nullable
    @Override
    public Particle createParticle(BlockParticleOption typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        return new BlockProjectionParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, typeIn);
    }
}
