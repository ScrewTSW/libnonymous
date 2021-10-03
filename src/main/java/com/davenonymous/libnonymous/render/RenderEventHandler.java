package com.davenonymous.libnonymous.render;

import com.davenonymous.libnonymous.base.BaseBlock;
import com.davenonymous.libnonymous.base.BaseItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderEventHandler {

    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void handleRendererForBaseObjects(RenderWorldLastEvent event) {
        if(!Minecraft.renderNames() || minecraft.player == null) {
            return;
        }

        Player player = minecraft.player;
        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);

        if(!mainHand.isEmpty()) {
            if(mainHand.getItem() instanceof BaseItem) {
                BaseItem mainBase = (BaseItem)mainHand.getItem();
                mainBase.renderEffectOnHeldItem(player, InteractionHand.MAIN_HAND, event.getPartialTicks());
            } else if(mainHand.getItem() instanceof BlockItem) {
                Block block = ((BlockItem) mainHand.getItem()).getBlock();
                if(block instanceof BaseBlock) {
                    ((BaseBlock) block).renderEffectOnHeldItem(player, InteractionHand.MAIN_HAND, event.getPartialTicks(), event.getMatrixStack());
                }
            }
        }

        if(!offHand.isEmpty()) {
            if(offHand.getItem() instanceof BaseItem) {
                BaseItem mainBase = (BaseItem)offHand.getItem();
                mainBase.renderEffectOnHeldItem(player, InteractionHand.OFF_HAND, event.getPartialTicks());
            } else if(offHand.getItem() instanceof BlockItem) {
                Block block = ((BlockItem) offHand.getItem()).getBlock();
                if(block instanceof BaseBlock) {
                    ((BaseBlock) block).renderEffectOnHeldItem(player, InteractionHand.OFF_HAND, event.getPartialTicks(), event.getMatrixStack());
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRenderTick(TickEvent.RenderTickEvent event) {
        if(event.phase == TickEvent.RenderTickEvent.Phase.START) {
            RenderTickCounter.renderTicks++;
        }
    }
}
