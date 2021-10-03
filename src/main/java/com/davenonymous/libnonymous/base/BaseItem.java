package com.davenonymous.libnonymous.base;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionHand;

import net.minecraft.world.item.Item.Properties;

public class BaseItem extends Item {
    public BaseItem(Properties properties) {
        super(properties);
    }

    public void renderEffectOnHeldItem(Player player, InteractionHand mainHand, float partialTicks) {

    }
}
