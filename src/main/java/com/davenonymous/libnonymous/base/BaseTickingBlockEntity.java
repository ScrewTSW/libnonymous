package com.davenonymous.libnonymous.base;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.TickingBlockEntity;

public class BaseTickingBlockEntity implements TickingBlockEntity {
    private boolean initialized = false;

    @java.lang.Override
    public void tick() {
        if (!this.initialized) {
            initialize();
            this.initialized = true;
        }
    }

    //TODO: Implement isRemoved
    @java.lang.Override
    public boolean isRemoved() {
        return false;
    }

    //TODO: Implement getPos
    @java.lang.Override
    public BlockPos getPos() {
        return null;
    }

    //TODO: Implement getType
    @java.lang.Override
    public java.lang.String getType() {
        return null;
    }

    protected void initialize() {
    }
}
