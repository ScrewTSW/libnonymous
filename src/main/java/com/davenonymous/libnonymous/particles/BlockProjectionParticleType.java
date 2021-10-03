package com.davenonymous.libnonymous.particles;

import com.davenonymous.libnonymous.setup.ModObjects;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;

public class BlockProjectionParticleType extends ParticleType<BlockParticleOption> {
    public BlockProjectionParticleType(boolean alwaysShow) {
        super(alwaysShow, BlockParticleOption.DESERIALIZER);
    }

    public static void spawn(ServerLevel world, BlockPos pos, BlockState state) {
        world.sendParticles(new BlockParticleOption(ModObjects.blockProjectionParticleType, state), pos.getX(), pos.getY(), pos.getZ(), 1, 0 ,0, 0, 0 );
    }

    @Override
    public Codec<BlockParticleOption> codec() {
        return ParticleTypes.BLOCK.codec();
    }
}
