package com.davenonymous.libnonymous.base;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BaseBlock extends Block {
    public BaseBlock(Properties properties) {
        super(properties);
    }

    public void renderEffectOnHeldItem(Player player, InteractionHand mainHand, float partialTicks, PoseStack matrix) {

    }

    @Override
    public void onNeighborChange(BlockState state, LevelReader world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, world, pos, neighbor);

        if(world.isClientSide()) {
            return;
        }

        BlockEntity tileEntity = world.getBlockEntity(pos);
        if(!(tileEntity instanceof BaseTileEntity)) {
            return;
        }

        BaseTileEntity base = (BaseTileEntity) tileEntity;
        int previous = base.getIncomingRedstonePower();
        int now = base.getRedstonePowerFromNeighbors();

        if(now == 0) {
            if(previous > 0) {
                base.redstonePulse();
            }
        } else {
            if(previous != now) {
                base.redstoneChanged(previous, now);
            }
        }

        base.setIncomingRedstonePower(now);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);

        if(!(world.getBlockEntity(pos) instanceof BaseTileEntity)) {
            return;
        }

        BaseTileEntity baseTile = (BaseTileEntity) world.getBlockEntity(pos);
        baseTile.loadFromItem(stack);
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity, boolean horizontalOnly) {
        Direction result = Direction.getNearest((float) (entity.getX() - clickedBlock.getX()), (float) (entity.getY() - clickedBlock.getY()), (float) (entity.getZ() - clickedBlock.getZ()));
        if(horizontalOnly && (result == Direction.UP || result == Direction.DOWN)) {
            return Direction.NORTH;
        }
        return result;
    }
}
