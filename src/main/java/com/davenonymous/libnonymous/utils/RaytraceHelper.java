package com.davenonymous.libnonymous.utils;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class RaytraceHelper {
    public static BlockHitResult rayTrace(Level world, Entity entity) {
        return rayTrace(world, entity, 16.0f);
    }

    public static BlockHitResult rayTrace(Level world, Entity entity, double blockReachDistance) {
        return rayTrace(world, entity, blockReachDistance, 1.0f);
    }

    @Nullable
    public static BlockHitResult rayTrace(Level world, Entity entity, double blockReachDistance, float partialTicks) {
        return rayTrace(world, entity, blockReachDistance, partialTicks, true);
    }

    @Nullable
    public static BlockHitResult rayTrace(Level world, Entity entity, double blockReachDistance, float partialTicks, boolean outline) {
        Vec3 vec3d = entity.getEyePosition(partialTicks);
        Vec3 vec3d1 = entity.getViewVector(partialTicks);
        Vec3 vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return world.clip(new ClipContext(vec3d, vec3d2, outline ? ClipContext.Block.OUTLINE : ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
    }
}
