package com.davenonymous.libnonymous.base;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Hand;

import net.minecraft.item.Item.Properties;

public class BaseItem extends Item {
    public BaseItem(Properties properties) {
        super(properties);
    }

    public void renderEffectOnHeldItem(PlayerEntity player, Hand mainHand, float partialTicks) {

    }
}
