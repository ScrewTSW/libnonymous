package com.davenonymous.libnonymous.setup;

import com.davenonymous.libnonymous.render.RenderEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class ProxyClient implements IProxy {
    
    private static final Minecraft minecraft = Minecraft.getInstance();
    
    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(RenderEventHandler.class);
    }

    @Override
    public World getClientWorld() {
        return minecraft.world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return minecraft.player;
    }
}
